package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.Collections;

class BaekJoon_2212 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int[] sensors = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0 ; i<N; i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(solution(N, K, sensors));

    }

    public static int solution(int N, int K, int[] sensors) {
        Arrays.sort(sensors);
        ArrayList<Integer> difference = new ArrayList<>();
        for(int i=0; i<N-1; i++) {
            difference.add(sensors[i+1] - sensors[i]);
        }
        Collections.sort(difference, Collections.reverseOrder());
        int totalLength = sensors[sensors.length - 1] - sensors[0];
        int sumValue = 0;
        for(int i=0; i<K-1; i++) {
            if (difference.isEmpty())
                break;
            sumValue += difference.get(i);
        }
        return totalLength - sumValue;

    }

}
