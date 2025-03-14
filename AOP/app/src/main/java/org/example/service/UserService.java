package org.example.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class UserService {
    @Getter
    @Setter
    @AllArgsConstructor
    public class User {
        private String name;
        private int age;
        private String address;
    }

    private User user;

    public UserService() {
        User user = new User("Iftikar Hussain", 22, "India, Assam");
    }

    public void logIn() {
        System.out.println("Logged user in");
    }

    public void logOut() throws Exception {
        System.out.println("Logging user out");
        throw new Exception("Unable to logout the user");
    }
}
