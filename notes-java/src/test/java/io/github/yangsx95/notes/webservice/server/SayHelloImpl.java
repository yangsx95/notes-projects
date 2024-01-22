package io.github.yangsx95.notes.webservice.server;

import javax.jws.WebService;

@WebService
public class SayHelloImpl implements ISayHello {
    
    @Override
    public String sayHello(String name) {
        return "你好，" + name;
    }
}
