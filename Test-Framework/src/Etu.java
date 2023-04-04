package model;

import etu1830.annotation.Urls;
import etu1830.framework.ModelView;

public class Etu {
    @Urls(url = "inscrire-etudiant")
    public ModelView Inscrire(){
        ModelView mv = new ModelView();
        mv.setView("emp-list.jsp");
        return mv;
    }   
    
    public void desinscrire(){
        
    }

}
