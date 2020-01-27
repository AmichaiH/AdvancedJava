package main.java.com.algosec.automation.training.streams;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;

public class ParallelStreams {
    public static void simpleParallelExample() {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        System.out.println("=== Not Parallel ===");
        long startTime = Instant.now().toEpochMilli();

        intList.forEach(System.out::println);

        long endTime = Instant.now().toEpochMilli();
        long timeElapsed = endTime - startTime;

        System.out.println("Execution time in milliseconds: " + timeElapsed);

        System.out.println("=== Parallel ===");

        startTime = Instant.now().toEpochMilli();

        intList.parallelStream().forEach(System.out::println);

        endTime = Instant.now().toEpochMilli();
        timeElapsed = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + timeElapsed);
    }

    public static void showMaxThreads() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println("Max # of threads:" + commonPool.getParallelism());
    }

    public static void showRunningThread(){
           Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12).parallelStream()
                .map(p -> p.toString())
                .forEach(p -> System.out.println(p.toString() + " the thread name is " + Thread.currentThread().getName()));

           System.out.println("\nThe program proceeds only now...");
    }

    public static void utilizeForkJoin(){
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
    }
}
