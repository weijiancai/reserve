package cn.itcast.jpa.bean;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;

/**
 * 
 * @author weijiancai
 * @version 1.0.0
 */
@WebService()
public class TestWebService {
  @WebMethod
  public String sayHelloWorldFrom(String from) {
    String result = "Hello, world, from " + from;
    System.out.println(result);
    return result;
  }
  public static void main(String[] argv) {
    Object implementor = new TestWebService ();
    String address = "http://localhost:9000/TestWebService";
    Endpoint.publish(address, implementor);
  }
}