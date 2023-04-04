package model2;

import etu1830.annotation.Urls;
import etu1830.framework.ModelView;

public class Empe {
    @Urls(url = "emp-myMethod")
    public ModelView maMethode(){
        ModelView mv = new ModelView();
        mv.setView("emp-list.jsp");
        return mv;
    }
}
