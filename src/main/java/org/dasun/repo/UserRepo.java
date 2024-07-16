package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.dasun.model.User;

import java.util.List;

@ApplicationScoped
public class UserRepo implements PanacheRepository<User> {

    public List<User> getUserList() {
        return listAll();
    }

    public User findByID(Long id){
        return findById(id);
    }

    @Transactional
    public String addUser(User user) {
        try {
            persist(user);
            return "User is added succesfully";
        }catch (Exception e){
            return "User is not added. " + e.getMessage();
        }
    }

    @Transactional
    public String updateUser(User user, Long id){
        User newUser = findById(id);
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setAccountNumber(user.getAccountNumber());

        try{
            persist(newUser);
            return "User is updated succesfully";
        }catch (Exception e){
            return "User is not updated. " + e.getMessage();
        }
    }

    @Transactional
    public String deleteUser(Long id) {
        User tempUser = findByID(id);
        try {
            delete(tempUser);
            return "User is deleted succesfully";
        }catch (Exception e){
            return "User delete failed. " + e.getMessage();
        }
    }
}
