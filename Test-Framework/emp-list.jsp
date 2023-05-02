<%@ page import="java.util.Vector, model.societe.Emp, java.sql.Date" %>

<%
    Vector<Emp> liste = (Vector<Emp>)request.getAttribute("liste");
    out.println("Vous avez effectue la saisie suivante:");
    for(Emp emp : liste){
        try{
            out.println("EMPLOYE: " + emp.getNom());
        }catch(Exception e1){}

        try{
            out.println("AGE : " + emp.getAge());
        }catch(Exception e2){}

        try{
            out.println("Date embauche: "+ emp.getEmbauche().toString());
        }catch(Exception e2){}

        try{
            out.println("Note: "+ emp.getNote());
        }catch(Exception e3){}   
        
        
        
    }
%>