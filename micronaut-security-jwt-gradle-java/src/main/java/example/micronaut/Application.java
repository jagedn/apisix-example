package example.micronaut;

import io.micronaut.runtime.Micronaut;

public class Application {

    public static void main(String[] args) {
        System.out.println("starting");
        Micronaut.run(Application.class, args);
    }
}