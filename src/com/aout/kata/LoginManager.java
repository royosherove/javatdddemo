package com.aout.kata;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LoginManager {
    private List<User> users = new ArrayList<User>();

    public void addUser(String user, String pass) {
        users.add(new User(user, pass));
    }

    public Boolean isLoginOK(String user, String pass) {
        for (Iterator<User> i = users.iterator(); i.hasNext(); ) {
            User item = i.next();
            if (item.username == user && item.password == pass) {
                return true;
            }
        }
        return false;
    }
}

