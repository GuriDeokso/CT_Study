package syheo.programmers;

import java.io.IOException;

public class Main_13314{
    
    public static void main(String[] args) throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append("100\n");
        for(int i=1; i <= 100; i++) {
            for(int j=1; j <= 100; j++) {
                if(i==j) sb.append("0");
                else if(i==100 || j==100) sb.append("0");
                else sb.append("10000");
                if(j!=100) sb.append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
    }

}