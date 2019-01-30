import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ParallelStreamTest {
    public static void main(String[] args) {

        // Parallel Streams
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        long t0 = System.nanoTime();
        System.out.println(values.stream().sorted().count());
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort toook: %d ms", millis));
        // sequential sort took 472 ms

        t0 = System.nanoTime();
        System.out.println(values.parallelStream().sorted().count());
        t1 = System.nanoTime();
        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort toook: %d ms", millis));
        // parallel sort took 472 ms

        System.out.println("no of processors = " + Runtime.getRuntime().availableProcessors());
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        System.out.println(commonPool.getParallelism()); //1
    }
}