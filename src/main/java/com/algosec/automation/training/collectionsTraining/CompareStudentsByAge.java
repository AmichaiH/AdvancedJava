package main.java.com.algosec.automation.training.collectionsTraining;

import java.util.Comparator;

public class CompareStudentsByAge implements Comparator<Student> {

    @Override
    public int compare(Student right, Student left) {
        return (right).compareTo(left);
    }
}
