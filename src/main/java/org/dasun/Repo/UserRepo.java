package org.dasun.Repo;

import org.dasun.Model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepo {
    private List<User> userList = new ArrayList<>();

    public List<User> getUserList() {
        System.out.println(userList);
        return userList;
    }

    public User getUser(String id){
        for(User user: userList){
            String userID = Integer.toString(user.getId());
            if(userID.equals(id)){
                return user;
            }
        }
        return null;
    }

    public String addUser(User user) {
        try {
            userList.add(user);
            return "User is added succesfully";
        }catch (Exception e){
            return "User is not added. " + e.getMessage();
        }
    }

    public String updateUser(User user, String id){
        User userIndex = null;
        for(User tempUser: userList){
            String userID = Integer.toString(tempUser.getId());
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
            String userID = Integer.toString(tempUser.getId());
            if(userID.equals(id)){
                userList.remove(tempUser);
                return "User Deleted Successfully";
            }
        }
        return "User Delete failed!";
    }
}
