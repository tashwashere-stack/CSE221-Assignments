import java.util.*;
import java.io.*;
public class p4 {
    // static ArrayList<Integer>path1;
    // static ArrayList<Integer>path2;
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
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Integer> adj [] = new ArrayList [N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer (br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            adj[u].add(v);
        }
        for(int i = 0; i <= N; i++){
            Collections.sort(adj[i]);
        }
        List<Integer> path1 = bfs(adj, S, K);

        List<Integer> path2 = bfs(adj, K, D);

        if(path1 == null || path2 == null){
            pr.println("-1");
        }
        else{
            pr.println(path1.size() -1 + path2.size() -1);
            // 1. Print all of Path 1
        for (int i = 0; i < path1.size(); i++) {
            pr.print(path1.get(i));
    
            // Always print a space AFTER a path1 node UNLESS 
            // there is no path2 data coming after it.
            if (i < path1.size() - 1 || path2.size() > 1) {
            pr.print(" ");
            }   
        }

        // 2. Print Path 2 (starting from index 1 to avoid repeating node K)
        for (int i = 1; i < path2.size(); i++) {
            pr.print(path2.get(i));
    
            // Only print a space if there's another number coming
        if (i < path2.size() - 1) {
            pr.print(" ");
            }
        }

        }
        // System.err.println("Path 1: " + path1);
        // System.err.println("Path 2: " + path2);

        pr.flush();
        pr.close();
    }
    static List<Integer> bfs(ArrayList<Integer> adj [], int start, int destination){
        boolean visited [] = new boolean [adj.length];
        int parent [] = new int [adj.length];
        int distance [] = new int [adj.length];

        Arrays.fill(parent, -1);
        Arrays.fill(distance, 0);

        visited[start] = true;
        parent[start] = -1;
        distance[start] = 0;

        Queue<Integer> q = new LinkedList<>();
        q.add(start);

        

        while(!q.isEmpty()){
            int u = q.poll();
            if(u == destination){
                break;
            }
            for(int v : adj[u]){
                if(!visited[v]){
                    visited[v] = true;
                    parent[v] = u;
                    distance[v] = distance[u] + 1;
                    q.add(v);
                }
            }
        }
        if(!visited[destination]){
            return null;
        }
        else{
            List<Integer> path = new ArrayList <>();
            int curr = destination;
            while(curr != -1){
                path.add(curr);
                curr = parent[curr];
            }
            Collections.reverse(path);
            return path;
        }
    }
}
