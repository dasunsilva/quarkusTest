package org.dasun.test;

import jakarta.enterprise.context.ApplicationScoped;

public class TestBean {

    @ApplicationScoped
    public String SayHello(){
        return "Hello World";
    }
}
