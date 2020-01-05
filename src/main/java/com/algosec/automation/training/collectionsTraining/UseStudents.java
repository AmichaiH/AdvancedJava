package main.java.com.algosec.automation.training.collectionsTraining;

import javax.rmi.CORBA.StubDelegate;
import javax.swing.plaf.synth.SynthTableUI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseStudents {
    public static void UseStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(5, "Amichai"));
        students.add(new Student(7, "Michal"));
        students.add(new Student(3, "Moshe"));

        System.out.println(students.toString());
        Collections.sort(students);
        System.out.println(students.toString());

        //sort by name
        students.sort(new CompareStudentsByNameLength());
        students.sort(new CompareStudentsByAge());
        System.out.println(students.toString());

        List<Student> otherStudents = new ArrayList<>();
        otherStudents.add(new Student(5, "Avi"));
        otherStudents.add(new Student(7, "Yuli"));
        otherStudents.add(new Student(3, "Dudu"));

        otherStudents.sort(new ChainedStudentComparator(
                new CompareStudentsByNameLength(),
                new CompareStudentsByAge()
        ));

        System.out.println(otherStudents.toString());
    }
}
