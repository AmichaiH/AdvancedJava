package main.java.com.algosec.automation.training.builder;

public class UseBuilder {
    public static void useBuilder(){
        Person myPerson = new Person.PersonBuilder("Amichai")
                .age(5)
                .id(7)
                .domain("IL")
                .build();

        System.out.println("Using builder when initializing all params:");
        System.out.println(myPerson.toString());

        Person personWithOutInitOfDomain = new Person.PersonBuilder("Limor")
                .age(5)
                .id(7)
                .build();

        System.out.println();
        System.out.println("Using builder when initializing part of the params:");
        System.out.println(personWithOutInitOfDomain.toString());
    }
}
