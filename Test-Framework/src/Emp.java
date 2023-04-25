package model.societe;

import etu1830.annotation.Urls;
import etu1830.framework.ModelView;

import java.sql.Date;
import java.util.Vector;

public class Emp {
    String nom;
    Integer age;
    Date embauche;

    // _ _ _ Constructors _ _ _

    
    public Emp(){

    }
    
    // _ _ _ Methods _ _ _

    public void embaucher(){}

    @Urls(url = "getAllEmp")
    public ModelView getAll(){
        ModelView mv = new ModelView();
        
        // vue destinatrice
        mv.setView("emp-list.jsp");

        //elements de donnees a passer a la vue
        Vector<Emp> liste = new Vector<Emp>();
        Emp e = new Emp(),
            e2 =  new Emp(),
            e3 =  new Emp();
            
        e.setNom("Lili");
        e2.setNom("Nato");
        e3.setNom("Miki");
        liste.add(e);
        liste.add(e2);
        liste.add(e3);

        // ajout de la liste aux donnees a passer
        mv.addItem("liste", liste);

        return mv;
    }

    @Urls(url = "showOneEmp")
    public ModelView listeEmpSaisi(){
        ModelView mv = new ModelView();
        Vector<Emp> liste = new Vector<Emp>();
        liste.add(this);
        mv.addItem("liste", liste);
    
        System.out.println("Nom:\t"+ this.getNom());
        System.out.println("Age:\t"+ this.getAge());
        System.out.println("Date embauche: "+ this.getEmbauche().toString());

        mv.setView("emp-list.jsp");
        return mv;
    }

    //_ _ _ GET SET _ _ _

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAge(String age){
        this.age = Integer.valueOf(age);
    }

    public Date getEmbauche() {
        return embauche;
    }

    public void setEmbauche(Date embauche) {
        this.embauche = embauche;
    }

}
