package main.java.com.algosec.automation.training.collectionsTraining;

import java.util.Comparator;

public class CompareStudentsByNameLength implements Comparator<Student> {
    @Override
    public int compare(Student right, Student left) {

        int aLen = right.getName().length();
        int bLen = left.getName().length();
        if (aLen < bLen) {
            return -1;
        } else if (aLen > bLen) {
            return 1;
        }

        return 0;
    }
}
