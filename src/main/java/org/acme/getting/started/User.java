package org.acme.getting.started;

import javax.persistence.*;

@Entity
@Cacheable
public class User {
    @Id
    @GeneratedValue
    Long id;

    @Column(length = 50)
    String name;
    @Column(length = 50)
    String gender;
    
    public User(){}
    public User(Long id,String name,String gender){
        this.id=id;
        this.name=name;
        this.gender=gender;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getGender() {
        return gender;
    }

}
