/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spbstu.hse.j210;

import java.security.InvalidParameterException;
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

    @PostConstruct
    public void initialize() {

        System.out.println(">> ok");
    }

    @Override
    public String getMessage(String user, int index) throws InvalidParameterException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addMessage(String user, String message) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getMessageList(String user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
