<%@ page import="java.util.Vector, model.societe.Emp, java.sql.Date" %>

<%
    Vector<Emp> liste = (Vector<Emp>)request.getAttribute("liste");
    out.println("Vous avez effectue la saisie suivante:");
    for(Emp emp : liste){
        out.println("EMPLOYE: " + emp.getNom());
        out.println("AGE : " + emp.getAge());
        out.println("Date embauche: "+ emp.getEmbauche().toString());
    }
%>