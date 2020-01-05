package main.java.com.algosec.automation.training.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class UseStreams {


    public static void main(String[] args) {
        String[] numbers = {"one", "two", "three"};
        List<String> myArray = Arrays.asList(numbers);

        //traditional example
        for (String s : myArray) {
            if (s.equals("one")) {
                System.out.println("simple print 1:" + s);
            }
            if (s.length() > 3) {
                System.out.println("more than 3:" + s);
            }
        }

        //simple stream example
        myArray.stream().forEach(s -> System.out.println(s));

        //streams can have intermidate operations or terminal operations
//        filter for example
        Stream<String> stream1 = myArray.stream().filter(s -> s.equals("one"));
        myArray.stream().filter(s -> s.equals("one")).forEach(System.out::println);
//        using predicates in filters
        Stream.of("ab", "abc", "abcde", "tt", "longstream")
                .filter(isUpToThree())
                .forEach(s -> System.out.println("short strings(up to 3 chars):" + s));

        //you don't need to create a collection in order to stream - Stream.of
        Stream.of(1, 2, 3).filter(s -> s == 1).forEach(System.out::println);
        //type of streams (primitive streams) -
        IntStream.range(1, 4)
                .forEach(System.out::println);

        IntStream.range(1, 10).filter(s -> s == 5).forEach(System.out::println);

//        primitive streams have builtin functions - for example - average() or sum():
        Arrays.stream(new int[]{1, 2, 3})
                .map(n -> 2 * n + 7)
                .average()
                .ifPresent(System.out::println);

        //intermidiate operations happen only when a terminal operation exists in the end:
//        //this will print noting:
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

       //this will do the job:
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //look at the output - what is the order of actions? do we first do all the filtering or do we pass each intermidiate to the terminal action?

        //this behavior helps us to reduce operations - see here:
        //due to this behavior here - we only run map twice - we finish after the first match of A in the terminal
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .anyMatch(s -> {
                    System.out.println("anyMatch: " + s);
                    return s.startsWith("A");
                });

        //so if the order matters - we should think a lot before writing streams... how can we reduce the operations here?
        Stream.of("d2", "a2", "b1", "b3", "c")
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("A");
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //simply switch the order of map (and change the filter condition to lower case)and filter and save by that the map calls
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return s.startsWith("a");
                })
                .map(s -> {
                    System.out.println("map: " + s);
                    return s.toUpperCase();
                })
                .forEach(s -> System.out.println("forEach: " + s));

        //a steam is closed after the terminal operation:
        Stream<String> openStream = Stream.of("ab", "abc", "abcde", "tt", "longstream").filter(s->s.equals("abc"));
        openStream.forEach(s->System.out.println("first print" + s));
        //however trying to use it again will trigger an exception
        openStream.forEach(System.out::println);

        //we can overcome this by using a suplier
    Supplier<Stream<String>> suplier = ()-> Stream.of("ab", "abc", "abcde", "tt", "longstream").filter(s->s.equals("longstream"));
        suplier.get().forEach(s->System.out.println("First print:" + s));
        suplier.get().forEach(s->System.out.println("Second print:" + s));


        //collection - how can I create a collection from a stream
        List<SteamExampleObject> steamExampleObjects = Arrays.asList(
                new SteamExampleObject(1, 2),
                new SteamExampleObject(1, 5),
                new SteamExampleObject(3,4),
                new SteamExampleObject(5,6)
        );

        //create a list
        List<SteamExampleObject> filteredSteamExampleObject = steamExampleObjects.stream().filter(p->p.getFirstNum() > 2).collect(Collectors.toList());
        filteredSteamExampleObject.stream().forEach(s->System.out.println("Filtered collection:" + s));

        Map<Integer, SteamExampleObject> mapFromStream = steamExampleObjects.stream().collect(Collectors.toMap(SteamExampleObject::getFirstNum, Function.identity()));
        for(int key:mapFromStream.keySet()){
            System.out.println("Key" + key + " value:" + mapFromStream.get(key));
        }

        //some more collect features - groupingby:
        Map<Integer, List<SteamExampleObject>> groupedExamples = steamExampleObjects.stream().collect(Collectors.groupingBy(SteamExampleObject::getFirstNum));
        for(Map.Entry<Integer, List<SteamExampleObject>> entry:groupedExamples.entrySet()){
            System.out.println("grouped list for " + entry.getKey() + ":" + entry.getValue().toString());
        }

        //or even average - mind the annoying intellij feature of not reconizing unless it's suitable varialbe
        Double averageSecondNum = steamExampleObjects.stream().collect(Collectors.averagingInt(SteamExampleObject::getSecondNum));
        System.out.println("average second num:" + averageSecondNum);


        //parallel stream
//        //stream uses the ForkJoinPool:
            ForkJoinPool commonPool = ForkJoinPool.commonPool();
            System.out.println("Max # of threads:" + commonPool.getParallelism());

        //it will use up to 5 threads (configurable)
        //simple example - you can see here the main thread of the program is also utilized:
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).parallelStream()
                .map(p -> p.toString())
                .forEach(p -> System.out.println(p.toString() + " the thread name is " + Thread.currentThread().getName()));

        //as the main thread of the program is utilized we won't proceed with the program till the parallel stream ends:
        System.out.println("Finished the job");

        //so what are the possible problems with the parallel stream?

        //if I have another parallel stream in the program - they will both use the same ForkJoinCommonPool
        //If one is blocking (for example network actions) - the other parallel stream will suffer

        //so as we still don't have a real good way to define the Pool we use in the parallel stream -
        //we need a trick to force a stream to use a certain pool.
        ForkJoinPool privatePool = new ForkJoinPool(3);//3 is number of threads
        try {
            privatePool.submit(()->
                    Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9 ,10, 11, 12).parallelStream()
                            .map(p->p.toString())
                            .forEach(p->System.out.println(p.toString() + " the thread name is " + Thread.currentThread().getName()))).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Finished the job");

        //what's new in the prints? we don't use the main program thread.. still - this is a blocking operation
    }


    public static Predicate<String> isUpToThree() {
        return p -> p.length() < 4;
    }


}
