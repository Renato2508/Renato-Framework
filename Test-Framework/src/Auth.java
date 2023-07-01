package model.utils;

import etu1830.annotation.*;
import etu1830.framework.ModelView;

@Scop(isSingle = true)
public class Auth{
    String profile;

    @Urls(url = "login")
    public ModelView Login(){
        System.out.println("--> Method invocation: LOGIN ; Profile: "+this.profile);
        ModelView mv = new ModelView();
        mv.setView("index.jsp");
        mv.addSessionItem("connectedProfileKey", this.profile);
        return mv;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    
}