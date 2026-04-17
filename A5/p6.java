package A5;
import java.io.*;
import java.util.*;
public class p6 {
    static int [] subtreeSize;
    static boolean [] visited;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        ArrayList <Integer> [] adj = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 1; i <= N-1; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        visited = new boolean[N + 1];
        subtreeSize = new int[N + 1];
        calculatesubtree(adj, R);

        int Q = Integer.parseInt(br.readLine());
        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            pr.println(subtreeSize[u]);
        }
        pr.flush();
        pr.close();
    }
    static void calculatesubtree(ArrayList<Integer> [] adj, int R){
        subtreeSize[R] = 1;
        visited[R] = true;

        for(int v : adj[R]){
            if(!visited[v]){
                visited[v] = true;
                calculatesubtree(adj, v);
                subtreeSize[R] += subtreeSize[v];
            }
        }
    }

}