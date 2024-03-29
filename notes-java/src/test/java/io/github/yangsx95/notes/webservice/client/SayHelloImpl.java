
package io.github.yangsx95.notes.webservice.client;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SayHelloImpl", targetNamespace = "http://ws/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SayHelloImpl {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "sayHello", targetNamespace = "http://ws/", className = "ws.SayHello")
    @ResponseWrapper(localName = "sayHelloResponse", targetNamespace = "http://ws/", className = "ws.SayHelloResponse")
    @Action(input = "http://ws/SayHelloImpl/sayHelloRequest", output = "http://ws/SayHelloImpl/sayHelloResponse")
    String sayHello(
            @WebParam(name = "arg0", targetNamespace = "")
                    String arg0);

}
