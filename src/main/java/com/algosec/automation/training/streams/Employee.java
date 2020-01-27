package main.java.com.algosec.automation.training.streams;

public class Employee implements Comparable<Employee>{
    private int age;
    private String name;
    private int salary;

    public Employee(String name, int age, int salary)
    {
        this.name= name;
        this.age = age;
        this.salary = salary;
    }


    public int compareTo(Employee otherEmployee) {
        if(this.salary > otherEmployee.salary) {
            if (this.age > otherEmployee.age) {
                return 1;
            }
            else
                return -1;
        }
        else if(this.salary < otherEmployee.salary) {
            return -1;
        }
        return 0;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
