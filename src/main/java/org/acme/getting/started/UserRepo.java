package org.acme.getting.started;

import javax.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class UserRepo implements PanacheRepository<User>{
    public User findByName(String name){
        return find("name",name).firstResult();
    }
}
