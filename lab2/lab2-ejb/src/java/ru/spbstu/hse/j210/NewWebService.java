/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.spbstu.hse.j210;

import java.security.InvalidParameterException;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author il.lenskii
 */
@WebService(serviceName = "NewWebService")
@Stateless()
public class NewWebService implements IDemo {

    @EJB
    private UsersMesagesSessionBean ejbRef;

    @WebMethod(operationName = "getMessage")
    public String getMessage(@WebParam(name = "user") String user, @WebParam(name = "index") int index) throws InvalidParameterException {

        return ejbRef.getMessage(user, index);
    }
    
    @WebMethod(operationName = "add")
    public boolean add(@WebParam(name = "user") String user, @WebParam(name = "message") String message) {

        return ejbRef.addMessage(user, message);
    }

    @WebMethod(operationName = "getAllMessage")
    public List<String> getAllMessage(@WebParam(name = "user") String user) {

        return ejbRef.getMessageList(user);
    }
}
