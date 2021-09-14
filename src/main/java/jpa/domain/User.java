package jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedQuery(name="User.allUser",query="SELECT u FROM User u")
@Inheritance(strategy=
        InheritanceType.SINGLE_TABLE)
public class User implements Serializable {

    private Long id;
    private String name;
    private String mail;
    private String password;


    public User() {
    }

    public User(String name, String mail, String password) {
        this.name = name;
        this.mail = mail;
        this.password = password;
    }


    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
