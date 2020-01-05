package main.java.com.algosec.automation.training.collectionsTraining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UseCollections {
    public void UseCollections(){
        //arraylist
        list = new ArrayList<>();
        list.add(1);
        list.add(2);

        //using asList
        asList = Arrays.asList(1, 2, 3, 4);

        System.out.println(asList.toString());

        students = new ArrayList<>();
        students.add(new Student(5, "Avi"));

        students = Arrays.asList(
                new Student(7, "Moshe"),
                new Student(9, "Avi")
        );

    }



    List<Integer> list;
    List<Integer> asList;
    List<Student> students;

}
