package playground;

import java.util.Properties;

public class HelloWorld {
    public static void main(String[] args) {
        
        Runtime r = Runtime.getRuntime();
        IO.println(System.console());
        System.getProperty("");
        System.setProperty("", "");
        System.clearProperty("");
        Properties p = new Properties();
        p.setProperty("a", "aaa");
        p.setProperty("b", "bbb");
        System.setProperties(p);
//        Properties p = System.getProperties();
//        for (var key : p.keySet()) {
//            IO.println(key + " : " + p.get(key));
//        }
//        for (var key : System.getenv().keySet()) {
//            IO.println(key + " : " + System.getenv(key));
//        }
//        IO.println(r.availableProcessors());
    }}
