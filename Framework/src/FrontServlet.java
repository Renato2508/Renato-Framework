package etu1830.framework.servlet;

import etu1830.framework.Mapping;
import etu1830.framework.ModelView;
import etu1830.utils.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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
            String slug = Utils.getPathFromURL(url, this.context);
            out.println("URL: "+ url);
            out.println("slug: "+slug);

            for(Map.Entry<String, Mapping> entry : this.mappingUrls.entrySet()){
                out.println("Annotation: "+ entry.getKey()+"\tMethode : "+entry.getValue().getMethod()+"\tClasse: "+entry.getValue().getClassName());
            }

            
            Mapping map = this.mappingUrls.get(slug);
            if(map == null) out.println("Annotation inconnue");
            else{
                ModelView mv = Utils.getModelView(map);
                System.out.println("VUE:" + mv.getView());
                RequestDispatcher dispat = req.getRequestDispatcher(mv.getView());
            dispat.forward(req,res);  

            }
                



        
            
        }catch( Exception e ){
            e.printStackTrace();
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
