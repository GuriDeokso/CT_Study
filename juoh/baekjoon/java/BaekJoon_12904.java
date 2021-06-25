import java.io.*;

public class BaekJoon_12904 {
    public static void main(String[] args) throws IOException {
        BaekJoon_12904.solution();
    }

    public static void solution() throws IOException {
        // https://yangbox.tistory.com/58
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer S = new StringBuffer(br.readLine());
        StringBuffer T = new StringBuffer(br.readLine());

        while(S.length() < T.length()) {
            if(T.charAt(T.length() - 1) == 'A') {
                T.deleteCharAt(T.length() - 1);
            }
            else if(T.charAt(T.length() - 1) == 'B') {
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }
        // 자바 스트링 비교 equals >> 값비교 / == >> 주소값 비교
        System.out.println((S.toString().equals(T.toString())) ? 1 : 0);
    }
}
