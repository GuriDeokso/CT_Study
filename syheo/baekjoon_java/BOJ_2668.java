package baekjoon_java;

import java.io.*;
import java.util.*;

public class BOJ_2668 {

    static ArrayList<Integer> nums;
    static ArrayList<Boolean> visited;

    static Pair dfs(int i,HashSet first,HashSet second){
        if(visited.get(i)){
            return new Pair(first,second);
        }
        first.add(i);
        second.add(nums.get(i));
        visited.set(i,true);

        return new Pair(first,second);
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        nums = new ArrayList<>(N+1);
        visited = new ArrayList<>(N+1);
        HashSet<Integer> answer = new HashSet<>();
        
        for(int i=1;i<=N;i++){
            nums.set(i,sc.nextInt());
            if(nums.get(i).equals(i)){
                answer.add(i);
                visited.set(i,true);
            }
        }
        for(int i=1;i<=N;i++){
            if(!nums.get(i).equals(i)){
                int first = 
            }
        }



    }
    static class Pair {
        private HashSet first;
        private HashSet second;
    
        Pair(HashSet first, HashSet y) {
            this.first = first;
            this.second = second;
        }
    
        public HashSet getFirst(){
            return first;
        }
    
        public HashSet getSecond(){
            return second;
        }
    }
}