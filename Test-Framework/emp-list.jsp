<%@ page import="java.util.Vector, model.societe.Emp, java.sql.Date" %>

<%
    Vector<Emp> liste = (Vector<Emp>)request.getAttribute("liste");
    out.println(request.getAttribute("connectedProfile")+" Vous avez effectue la saisie suivante:"+"<br>");
    
    for(Emp emp : liste){
        try{
            out.println("EMPLOYE: " + emp.getNom()+"<br>");
        }catch(Exception e1){}

        try{
            out.println("AGE : " + emp.getAge()+"<br>");
        }catch(Exception e2){}

        try{
            out.println("Date embauche: "+ emp.getEmbauche()+"<br>");
        }catch(Exception e2){}

        try{
            out.println("Note: "+ emp.getNote()+"<br>");
        }catch(Exception e3){}  
        
        try{
            out.println("Status: "+emp.getStatus()+"<br>");
        }catch(Exception e4){}

        try{
            out.println("Prime: "+emp.getPrime()+"<br>");
        }catch(Exception e5){}

        try{
            out.println("FICHIER: "+ emp.getFile().getFileName() + " ===>>> " + emp.getFile().getBytes());
        }catch(Exception e6){
            //e6.printStackTrace();
        }
        
        try{
            out.println("FICHIER: "+ emp.getFile());
        }catch(Exception e6){
            //e6.printStackTrace();
        }
        
        out.println("\nREMARQUES: "+session.getAttribute("rq"));
        
    }
%>