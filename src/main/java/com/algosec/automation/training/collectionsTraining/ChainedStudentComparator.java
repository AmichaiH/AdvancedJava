package main.java.com.algosec.automation.training.collectionsTraining;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ChainedStudentComparator implements Comparator<Student> {
    public ChainedStudentComparator(Comparator<Student>...comparators){
        this.comparatorsList = Arrays.asList(comparators);
    }

    @Override
    public int compare(Student right, Student left) {
        for(Comparator<Student> comparator : comparatorsList){
            int result = comparator.compare(right, left);
            if(result != 0){
                return result;
            }
        }
        return 0;
    }

    private List<Comparator<Student>> comparatorsList;
}
