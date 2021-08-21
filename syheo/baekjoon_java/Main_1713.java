package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * solved.ac
 * 백준
 * 1713
 * 후보 추천하기
 * 실버 2
 **/

public class Main_1713 {

    static class Person implements Comparable<Person>{
        int order;
        int cnt;
        public Person(int order,int cnt){
            this.order = order;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Person o) {
            if(this.cnt < o.cnt){
                return 1;
            }
            else if(this.cnt == o.cnt){
                if(this.order>o.order){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else{
                return -1;
            }

        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int N, K; // 사진틀 수 , 추천 수
    static PriorityQueue<Person> queue = new PriorityQueue<Person>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        tokens = new StringTokenizer(br.readLine());

        for (int i = 0; i < K; i++) {
            queue.add(new Person(i,Integer.parseInt(tokens.nextToken())));
            if(queue.size()>3){
                Person pollPerson = queue.poll();
            }
        }

    }
}
