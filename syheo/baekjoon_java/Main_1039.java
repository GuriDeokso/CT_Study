package baekjoon_java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * solved.ac
 * 백준
 * 1039
 * 교환
 * 골드 3
 * 아이디어 :
 * bfs 를 통해 swap 한 int[]을 넘김
 * bfs 에서 방문 체크는 hashset<String> 을 이용하여 int[]+count 를 통해 검사한다.
 *
 * 너무 지저분하다. queue에 넣어주는 값을 new String()을 통해 해주고
 * Integer.parseInt(String)을 통해서 값을 도출하는게 더 깔끔하고 메모리도 절약할 듯.
 */

public class Main_1039 {

    static class Info{
        int[] nums;
        int cnt;

        public Info(int[] nums,int cnt) {
            this.nums = nums;
            this.cnt = cnt;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static int[] nList;
    static Queue<Info> queue = new LinkedList<>();
    static HashSet<String> visited = new HashSet<>();

    static String N;
    static int K;
    static int M; // 자릿 수
    static int result = -1; // 결과
    static int n=0;

    public static void main(String[] args) throws IOException {

        // 1. input
        tokens = new StringTokenizer(br.readLine());
        N = tokens.nextToken();
        K = Integer.parseInt(tokens.nextToken());

        // 2. n to arrayList
        M = N.length();
        nList = new int[M];
        for (int i = 0; i < M; i++) {
            nList[i]=N.charAt(i)-'0';
        }


        // 3. bfs
        queue.add(new Info(nList,0));
        while(!queue.isEmpty()){
            //System.out.println(n++);
            Info info = queue.poll();
            if(info.cnt == K){
                break;
            }
            for (int i = 0; i < M-1; i++) {
                for(int j = i+1; j< M; j++){
                    if(i!=0 || info.nums[j]!=0){
                        int[] tmpList = new int[info.nums.length];
                        for (int y = 0; y < info.nums.length; y++) {
                            tmpList[y] = info.nums[y];
                        }
                        int tmp = tmpList[j];
                        tmpList[j] = tmpList[i];
                        tmpList[i] = tmp;
                        if(info.cnt==K-1){
                            int rst = 0;
                            for(int x = 0; x <M; x++){
                                rst+= tmpList[x]*(int)Math.pow(10,M-x-1);
                            }
                            result = Math.max(rst,result);
                        }
                        if(!visited.contains(Arrays.toString(tmpList)+"@"+info.cnt)){
                            queue.add(new Info(tmpList,info.cnt+1));
                            visited.add(Arrays.toString(tmpList)+"@"+info.cnt);
                            //System.out.println(Arrays.toString(tmpList)+"@"+info.cnt);
                        }

                    }

                }

            }
        } // while
        System.out.println(result);
    }
}
