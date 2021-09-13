package jpa.domain;

import javax.persistence.Entity;

@Entity
public class Worker extends User {
    private String profession;

    public Worker(String name, String mail, String password, String profession) {
        super(name, mail, password);
        this.profession = profession;
    }

    public Worker() {

    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
