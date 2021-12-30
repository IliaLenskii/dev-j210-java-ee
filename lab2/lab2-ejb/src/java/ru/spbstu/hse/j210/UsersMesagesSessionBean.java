/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spbstu.hse.j210;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author il.lenskii
 */
@Stateful(name = "UsersMesagesSessionBean")
@LocalBean
public class UsersMesagesSessionBean extends AMessages {
    
    private final HashMap<String, ArrayList<String>> usersAsMsg = new HashMap<>();

    @PostConstruct
    public void initialize() {
    }

    @Override
    public String getMessage(String user, int index) throws InvalidParameterException {
        
        ArrayList<String> userList = usersAsMsg.get(user);
        
        if(userList == null)
            return "null";

        if(userList.size() <= index)
            return "null";

        return userList.get(index);
    }

    @Override
    public boolean addMessage(String user, String message) {

        ArrayList<String> userList = usersAsMsg.get(user);

        if(userList == null) {
            
            userList = new ArrayList<>();
            userList.add(message);

            usersAsMsg.put(user, userList);
        } else {

            userList.add(message);
        }

        return true;
    }

    @Override
    public List<String> getMessageList(String user) {
        
        ArrayList<String> userList = usersAsMsg.get(user);
        
        return userList;
    }
}
