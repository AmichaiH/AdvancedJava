package main.java.com.algosec.automation.training.builder;

public class Person {
    public static class PersonBuilder {
        public PersonBuilder(String name) {
            this.name = name;
        }

        public PersonBuilder age(int age) {
            this.age = age;
            return this;
        }

        public PersonBuilder domain(String domain) {
            this.domain = domain;
            return this;
        }

        public PersonBuilder id(int id) {
            this.id = id;
            return this;
        }

        public Person build() {
            Person personToBuild = new Person();//calling Person() is allowed only here as it is accessible to inner class
            personToBuild.age = this.age;
            personToBuild.id = this.id;
            personToBuild.name = this.name;
            personToBuild.domain = this.domain;
            return personToBuild;
        }

        private int age;
        private String domain;
        private String name;
        private int id;
    }

    private int age;
    private String domain;
    private String name;
    private int id;

    private Person() {
    }//make the constructor private so the will be no ability to call it directly when initializing Person

    @Override
    public String toString(){
        return "name: " + name + " age: " + age + ", domain: " + domain + ", id: " + id;
    }

}
