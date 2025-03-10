
package org.example;

import org.example.proxies.classes.Man;
import org.example.proxies.classes.Person;
import org.example.proxies.classes.PersonInvocationHandler;

import java.lang.reflect.Proxy;

public class App {

    public static void main(String[] args) {
        Man mohan = new Man("Mohan", 22, "Delhi", "India");
        ClassLoader mohanClassLoader = mohan.getClass().getClassLoader();
        Class[] interfaces = mohan.getClass().getInterfaces();
        Person proxyMohan = (Person) Proxy.newProxyInstance(mohanClassLoader, interfaces, new PersonInvocationHandler(mohan));
        proxyMohan.introduce(mohan.getName());
    }
}
