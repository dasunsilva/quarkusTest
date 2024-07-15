package org.dasun.test;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TestBean {

    public String SayHello(){
        return "Hello World";
    }
}
