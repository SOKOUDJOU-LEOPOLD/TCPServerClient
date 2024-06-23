import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ParallelSort {
    public static void main(String[] args) {
        int[] array = {50, 10, 5, 1, 20, 30, 45, 60, 70, 80,
                11, 12, 13, 14, 15, 16, 17, 18, 19, 40,
                21, 22, 23, 24, 25, 26, 27, 28, 29, 90,
                31, 32, 33, 34, 35, 36, 37, 38, 39, 100,
                41, 42, 43, 44, 75, 76, 77, 78, 79, 81,
                51, 52, 53, 54, 55, 56, 57, 58, 59, 61,
                71, 72, 73, 74, 46, 47, 48, 49, 2, 3,
                63, 64, 65, 66, 67, 68, 69, 70, 82, 83,
                91, 92, 93, 94, 95, 96, 97, 98, 99, 4,
                85, 86, 87, 88, 89, 6, 7, 8, 9, 62}; // Example array

        // Serial sorting
        Long t0 = System.currentTimeMillis();
        Arrays.sort(array);
        Long t2 = System.currentTimeMillis();
        Long tSerial = t2 - t0;
        System.out.println("Sorted array in serial mode: " + Arrays.toString(array));
        System.out.println("Elapsed time for serial mode is: " + tSerial);

        // Parallel sorting
        int numThreads = 1; // Set the number of threads
        Long t3 = System.currentTimeMillis();
        parallelSort(array, numThreads);
        Long t4 = System.currentTimeMillis();
        Long tParallel = t4 - t3;
        System.out.println("Sorted array in parallel mode: " + Arrays.toString(array));
        System.out.println("Elapsed time for parallel mode is: " + tParallel);

        // Calculate Speed Up
        double speedUp = (double) tSerial / tParallel;
        System.out.println("Speed Up: " + speedUp);

        // Calculate Efficiency
        double efficiency = speedUp / numThreads;
        System.out.println("Efficiency: " + efficiency);
    }

    private static void parallelSort(int[] array, int numThreads) {
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int chunkSize = (int) Math.ceil((double) array.length / numThreads);

        for (int i = 0; i < numThreads; i++) {
            int start = i * chunkSize;
            int end = Math.min((i + 1) * chunkSize, array.length);

            executor.submit(() -> Arrays.sort(array, start, end));
        }

        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
