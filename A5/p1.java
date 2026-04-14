import java.util.*;
import java.io.*;

public class p1 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter pr = new PrintWriter(System.out);

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //Using the Adjacency List
        //Where N is the number of Nodes
        //An array of ArrayLists
        ArrayList<Integer>[] adj = new ArrayList [N+1];
        for(int i = 1; i <= N; i++){
            //Initialize each ArrayList in the Array
            adj[i] = new ArrayList<Integer>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            //Edge u-v
            adj[u].add(v);
            //Edge v-u 
            adj[v].add(u);
        }
        for(int i = 1; i <= N; i++){
            //Collections.sort sorts the ArrayList in ascending order
            //So the output matches with the expected output
            Collections.sort(adj[i]);
        }
        bfs(adj, 1, N, pr);
        pr.close();
    }

    public static void bfs(ArrayList<Integer>[] adj, int start, int end, PrintWriter pr){
        //QUeue for BFS
        Queue<Integer> q = new LinkedList<>();
        //Array to store visited data
        boolean [] visited = new boolean[adj.length];
        visited[start] = true;
        q.add(start);
        while(!q.isEmpty()){
            int u = q.poll();
            pr.print(u + " ");

            for(int v : adj[u]){
                if(!visited[v]){
                    visited[v] = true;
                    q.add(v);
                }
            }
        }    
    }

}
