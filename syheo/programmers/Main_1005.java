package syheo.programmers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_1005{

    static class Item{
        int building;
        int cnt;
        Item(int building,int cnt){
            this.building = building;
            this.cnt = cnt;
        } 
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        //input
        int T = Integer.parseInt(br.readLine());
        for(int test = 0; test<T; test++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //건물 갯수
            int K = Integer.parseInt(st.nextToken()); //건설 순서 규칙 갯수

            int[] buildings = new int[N+1];
            int[] indegrees = new int[N+1];
            ArrayList<ArrayList<Integer>> relations = new ArrayList<ArrayList<Integer>>();
            int W = 0;

            for(int i=0;i<=N;i++){
                relations.add(new ArrayList<Integer>());
            }
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<N;i++){
                buildings[i+1]=Integer.parseInt(st.nextToken());
            }
            for(int i=0;i<K;i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                indegrees[b]+=1;
                relations.get(a).add(b);
            }
            W = Integer.parseInt(br.readLine());

            //위상 정렬
            Queue<Item> q = new LinkedList<Item>();
            for(int i=1;i<=N;i++){
                if(indegrees[i]==0){
                    q.offer(new Item(i,0));
                }
            }
            
            ArrayList<ArrayList<Integer>> sums = new ArrayList<ArrayList<Integer>>();
            for(int i=0;i<=N;i++){
                sums.add(new ArrayList<Integer>());
            }

            while(!q.isEmpty()){
                Item info = q.poll();
                buildings[info.building]+= (!sums.get(info.building).isEmpty())? Collections.max(sums.get(info.building)):0;
                if(info.building == W){
                    break;
                }
                for(int i:relations.get(info.building)){
                    indegrees[i]-=1;
                    sums.get(i).add(buildings[info.building]);
                    if(indegrees[i]==0){
                        q.offer(new Item(i,info.cnt+1));
                    }
                }
            }
            
            System.out.println(buildings[W]);
        }

    }

}