package model;

import etu1830.annotation.Urls;

public class Etu {
    @Urls(url = "inscrire-etudiant")
    public String Inscrire(){
        return "emp-list.jsp";    
    }   
    
    public void desinscrire(){
        
    }

}
