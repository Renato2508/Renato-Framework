package model.societe;

import etu1830.annotation.Urls;
import etu1830.framework.ModelView;

public class Emp {
    public void embaucher(){}

    @Urls(url = "fire-employee")
    public ModelView virer(){
        ModelView mv = new ModelView();
        mv.setView("emp-list.jsp");
        return mv;
    }
}
