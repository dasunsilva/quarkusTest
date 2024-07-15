package org.dasun.repo;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.dasun.model.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@ApplicationScoped
public class UserRepo implements PanacheRepository<User> {

    @Inject
    UserRepo userRepo;

    private final List<User> userList = new ArrayList<>();

    public User getUser(String id){
        for(User user: userList){
            String userID = Long.toString(user.getId());
            if(userID.equals(id)){
                return user;
            }
        }
        return null;
    }

    public User findByID(String id){
        return find("id", id).firstResult();
    }


    @Transactional
    public String addUser(User user) {
        try {
            userRepo.persist(user);
            return "User is added succesfully";
        }catch (Exception e){
            return "User is not added. " + e.getMessage();
        }
    }

    @Transactional
    public String updateUser(User user, String id){
        User userIndex = null;
        for(User tempUser: userList){
            String userID = Long.toString(tempUser.getId());
            if(userID.equals(id)){
                userIndex = tempUser;
                break;
            }
        }

        int index = userList.indexOf(userIndex);
        userList.remove(index);
        userList.add(user);
        return "User update successful!";
    }

    public String deleteUser(String id){
        for(User tempUser: userList){
            String userID = Long.toString(tempUser.getId());
            if(userID.equals(id)){
                userList.remove(tempUser);
                return "User Deleted Successfully";
            }
        }
        return "User Delete failed!";
    }
}
