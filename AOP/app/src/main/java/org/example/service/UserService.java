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
}
