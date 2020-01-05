package main.java.com.algosec.automation.training.collectionsTraining;

public class Student implements Comparable<Student>{
    public Student(int age, String name){
        this.age = age;
        this.name = name;
    }

    @Override
    public int compareTo(Student other) {
        if (other.getAge() > this.age) {
            return -1;
        } else if (((Student)other).getAge() < this.age) {
            return 1;
        }
        return 0;
    }

    public int getAge(){
        return this.age;
    }

    public String getName(){
        return this.name;
    }

    @Override
    public String toString() {
        return "Name " + name + " Age " + age;
    }

    private int age;
    private String name;
}
