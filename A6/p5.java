import java.util.*;
import java.io.*;
public class p5 {
    static int [] distance;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        distance = new int [N+1];
        Arrays.fill(distance, -1);

        ArrayList<Integer> adj [] = new ArrayList [N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
       Queue<Integer> q = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < S; i++){
            int source = Integer.parseInt(st.nextToken());
            distance[source] = 0;
            q.add(source);
        }
        while(!q.isEmpty()){
            int u = q.poll();
            for(int v : adj[u]){
                if(distance[v] == -1){ //This means the node is unvisited and we start a bfs from there too 
                    distance[v] = distance[u] + 1;
                    q.add(v);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        ArrayList<Integer>path = new ArrayList<>();
        for(int i = 0; i < Q; i++){
            int destination = Integer.parseInt(st.nextToken());
            path.add(distance[destination]);
        }
        for(int i = 0; i < path.size(); i++){
            pr.print(path.get(i) + " ");
        }
        pr.flush();
        pr.close();
    }
}
