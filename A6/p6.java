import java.util.*;
import java.io.*;
public class p6 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int S = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int F = Integer.parseInt(st.nextToken());

        //A boolean array to store forbidden combiantions
        boolean [] forbidden = new boolean [10000]; //0000 to 9999
        for(int i = 0; i < F; i++){
            st = new StringTokenizer(br.readLine());
            forbidden[Integer.parseInt(st.nextToken())] = true;
        }
        if(forbidden[S] || forbidden[C]){
            pr.println(-1);
            pr.flush();
            return;
        }
        pr.println(bfs(S,C,forbidden));
        pr.flush();
    }
    static int bfs(int start, int end, boolean[] forbidden){
    int [] distance = new int[10000];
    Arrays.fill(distance, -1);

    Queue<Integer> q = new ArrayDeque<>();

    q.add(start);
    distance[start] = 0;

    while(!q.isEmpty()){
        int curr = q.poll();

        for(int i = 0; i < 4; i++){
            int power10 = (int) Math.pow(10,i);
            int digit = (curr / power10) % 10;

            int[] change = {1, -1};
            for(int move : change){
                int nextdigit = (digit + move + 10) % 10;
                int nextnumber = curr - (digit * power10) + (nextdigit * power10);

                if(!forbidden[nextnumber] && distance[nextnumber] == -1){
                    distance[nextnumber] = distance[curr] + 1;
                    q.add(nextnumber);
                }
            }
        }
    }

    return distance[end];
}
}
