import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main_3055 {

    static class Info{
        int r;
        int c;
        int cnt;

        public Info(int r,int c,int cnt){
            this.r = r;
            this.c = c;
            this.cnt = cnt;
        }
    }

    static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokens;
    static StringBuffer sb = new StringBuffer();
    static int R,C;
    static int dr,dc,sr,sc;
    static int nextR,nextC;
    static Info info;
    static ArrayList[] maps;
    static int[][] visited;
    static boolean[][] hedgeHogVisited;
    static int[] dx = {1,-1,0,0};
    static int[] dy = {0,0,1,-1};
    static Queue<Info> queue = new LinkedList<>();
    static Queue<Info> waterQueue = new LinkedList<>();
    static String notArrived = "KAKTUS";
    static int result = -1;

    public static void main(String[] args) throws IOException {

        //1. input
        tokens = new StringTokenizer(input.readLine());
        R = Integer.parseInt(tokens.nextToken());
        C = Integer.parseInt(tokens.nextToken());

        //1-1. map input
        maps = new ArrayList[R];
        visited = new int[R][C];
        hedgeHogVisited = new boolean[R][C];
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                visited[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int i=0;i<R;i++) {
            maps[i] = new ArrayList<Character>();
            String str = input.readLine();
            for(int j=0;j<C;j++) {
                maps[i].add(str.charAt(j));
                // save D, S
                if(maps[i].get(j).equals('D')){
                    dr = i;
                    dc = j;
                }
                if(maps[i].get(j).equals('S')){
                    sr = i;
                    sc = j;
                }
                // add water
                if(maps[i].get(j).equals('*')){
                    waterQueue.add(new Info(i,j,0));
                    visited[i][j]=0;
                }
            }
        }

        //2. water bfs
        while(!waterQueue.isEmpty()){
            info = waterQueue.poll();
            for(int i=0;i<4;i++){
                nextR = info.r+dx[i];
                nextC = info.c+dy[i];
                if(0<=nextR && nextR<R && 0<=nextC && nextC<C){
                    if(maps[nextR].get(nextC).equals('.') || maps[nextR].get(nextC).equals('S')){
                        if(visited[nextR][nextC]==Integer.MAX_VALUE){
                            waterQueue.add(new Info(nextR,nextC,info.cnt+1));
                            visited[nextR][nextC]=info.cnt+1;
                        }
                    }
                }
            }
        }

        //3-0. set queue
        queue.add(new Info(sr,sc,0));
        hedgeHogVisited[sr][sc]=true;
        //3. bfs
        while(!queue.isEmpty()){
            info = queue.poll();
            for(int i=0;i<4;i++){
                nextR = info.r+dx[i];
                nextC = info.c+dy[i];
                if(0<=nextR && nextR<R && 0<=nextC && nextC<C && !hedgeHogVisited[nextR][nextC]){
                    if(maps[nextR].get(nextC).equals('.')){
                        if(visited[nextR][nextC]>info.cnt+1){ // 물(2) > 두더지(1)+1
                            queue.add(new Info(nextR,nextC,info.cnt+1));
                            hedgeHogVisited[nextR][nextC]=true;
                        }
                    }
                    else if(maps[nextR].get(nextC).equals('D')){
                        result = info.cnt+1;
                        queue.clear();
                        break;
                    }
                }
            }
        }

        //4. answer
        if(result==-1){
            System.out.println(notArrived);
        }
        else{
            System.out.println(result);
        }
    }

}
