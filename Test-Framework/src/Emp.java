package model.societe;

import etu1830.annotation.Urls;

public class Emp {
    public void embaucher(){}

    @Urls(url = "fire-employee")
    public String virer(){
        return "emp-list.jsp";
    }
}
