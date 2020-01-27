package main.java.com.algosec.automation.training.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UseStreams {
    public static void useStreams() {
        IntStream
                .range(1, 10)
                .forEach(System.out::print);

        System.out.println();

        IntStream
                .range(1, 10)
                .skip(5)
                .forEach(x -> System.out.println(x));

        System.out.println();

        System.out.println(
                IntStream
                        .range(1, 5)
                        .sum());

        System.out.println();

        List<String> unSoryed = new ArrayList<>(Arrays.asList("Michal", "Eva", "Dror"));

        unSoryed.stream()
                .sorted()
                .findFirst()
                .ifPresent(System.out::print);

        System.out.println();

        Arrays.stream(new int[]{2, 4, 6, 8, 10})
                .map(x -> x * x)
                .average()
                .ifPresent(System.out::println);

        System.out.println();


        Stream<String> rows = null;
        try {
            Path filePath = Paths.get("C:\\cygwin64\\home\\Amichai.Herman\\git\\AdvancedJava\\src\\main\\java\\com\\algosec\\automation\\training\\streams\\data.txt");
            rows = Files.lines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


        rows
                .map(x -> x.split(","))
                .filter(x -> x.length >= 3)
                .filter(x -> Integer.parseInt(x[1]) > 15)
                .forEach(x -> System.out.println(x[0] + "  " + x[1] + "  " + x[2]));
        rows.close();
    }

    public static void orderInStreams() {
        //look at the order of print - we combine between actions
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        System.out.println();

        //this behavior helps us reducing actions significantly
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });
    }
}
