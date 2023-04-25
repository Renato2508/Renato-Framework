package etu1830.framework.servlet;

import etu1830.framework.Mapping;
import etu1830.framework.ModelView;
import etu1830.utils.Utils;
import java.util.Enumeration;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;

public class FrontServlet extends HttpServlet {

    HashMap<String, Mapping> mappingUrls;   // liste des methodes annotees avec leurs classes
                                            // <l'annotation et le Mapping correspondant>
    String context;

    public void init() throws ServletException {
        try{
            this.context = this.getInitParameter("context");
            String p = "";
            this.mappingUrls =  Utils.getUrlsAnnotedMethods(Utils.getClasses( null  , p ));
        }catch( Exception e ){
            e.printStackTrace();
        }
    }

    protected void processRequest(HttpServletRequest req,
    HttpServletResponse res) throws IOException, ServletException{
        try{
            PrintWriter out = res.getWriter();
            out.println("BIENVENUE");
            String url = req.getRequestURL().toString();

            // recuperation de l'attr URL de l'annotation Urls
            String slug = Utils.getPathFromURL(url, this.context);
            //out.println("URL: "+ url);
            //out.println("slug: "+slug);

            //liste des annotations disponibles, classes et methodes correspondantes 
            for(Map.Entry<String, Mapping> entry : this.mappingUrls.entrySet()){
                out.println("Annotation: "+ entry.getKey()+"\tMethode : "+entry.getValue().getMethod()+"\tClasse: "+entry.getValue().getClassName());
            }

            // recuperation du mapping correspondant a l'URL donnee
            // pour avoir le nom de classe et de methode a invoquer
            // et quelle est la classe d'appartenance

            Mapping map = this.mappingUrls.get(slug);
            if(map == null) out.println("URL non atteignable car Annotation inconnue");
            else{
                Object instance = FrontServlet.getInstance(map);
                Class classe = instance.getClass();
                Method[] methods = classe.getDeclaredMethods();
        
                // donner aux attributs de l'instance leur valeurs
                FrontServlet.setAttributes(instance, req, classe, methods);

                // recherche et invocation
                // de la methode du mapping
                // si elle est existante
                Method methode = null;
                for(Method courant: methods){
                    if (courant.getName().equals(map.getMethod())) methode = courant;
                }
                
                Object result = methode.invoke(classe.cast(instance));  
                
                // Cas de passage de donnees vers une vue
                if(result instanceof ModelView == true){
                    ModelView mv =  (ModelView)result;

                    // ajout de toutes les donnees a
                    // a passer vers la vue
                    for(Map.Entry<String, Object> entree : mv.getData().entrySet()){
                        req.setAttribute(entree.getKey(), entree.getValue());
                    }

                    RequestDispatcher dispat = req.getRequestDispatcher(mv.getView());
                    dispat.forward(req,res);  
                }
            }    
        }catch( Exception e ){
            e.printStackTrace();
        }
        
    }

    public static void setAttributes(Object instance, HttpServletRequest req, Class classe, Method[] methods){
        // nom du parametre courant
        String paramName;
        // valeurs possibles du parametre
        String[] paramValues;
        
        // liste de tous les noms de parametres de la requete
        Enumeration<String> parameterNames = req.getParameterNames();

        // parcours de la liste de noms de parametres
        while (parameterNames.hasMoreElements()) {
            paramName = parameterNames.nextElement();
            System.out.println("Parameter Name: " + paramName);
            paramValues = req.getParameterValues(paramName);

            // recherhce du setter correspondant
            for(Method courant : methods){
                if(courant.getName().compareToIgnoreCase("set"+paramName) == 0){
                    try{
                        // caster l'argument vers le type 
                        // attendu par le setter
                        Class<?> [] types = courant.getParameterTypes(); 
                        try{
                            // pour les types autres que String
                            courant.invoke(classe.cast(instance), types[0].getDeclaredMethod("valueOf", String.class).invoke(null, paramValues[0])); 
                        } 
                        catch(Exception e){
                            //e.printStackTrace();
                            courant.invoke(classe.cast(instance), paramValues[0]); 
                        }                     
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
        
    }
    
    public static Object getInstance(Mapping map) throws Exception{
        try{
            Class classe = Class.forName(map.getClassName());
            return classe.cast(classe.newInstance());
        }
        catch(Exception e){
            throw e;
        }
        
    }
    
    
    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
        throws IOException, ServletException{
        processRequest(req, res);
    }

    public void doPost(HttpServletRequest req,
                      HttpServletResponse res)
        throws IOException, ServletException{
        processRequest(req, res);
    }
}

