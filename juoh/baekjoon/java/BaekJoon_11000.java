import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BaekJoon_11000 {
    public static void main(String[] args) throws IOException {
        BaekJoon_11000.solution();
    }

    public static void solution() throws IOException {
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] classTimes = new int[n][2];
        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            classTimes[i][0] = Integer.parseInt(input[0]);
            classTimes[i][1] = Integer.parseInt(input[1]);
        }

        Arrays.sort(classTimes, Comparator.comparing(x -> x[0]));
        priorityQueue.add(classTimes[0][1]);
        for (int i = 1; i < n; i++) {
            int startTime = classTimes[i][0];
            int endTime = classTimes[i][1];
            if(priorityQueue.element() <= startTime) {  // == peek()
                priorityQueue.poll();
            }
            priorityQueue.add(endTime);
        }
        System.out.println(priorityQueue.size());

    }
}
