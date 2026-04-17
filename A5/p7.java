package A5;
import java.util.*;
import java.io.*;
public class p7 {
    static int [] visited;
    static boolean hasCycle = false;
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
        ArrayList<Integer> [] adj = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }
        visited = new int[N+1];

        for(int i = 1; i <= N; i++){
            if(visited[i] == 0){
                if(dfs(adj, i)){
                    hasCycle = true;
                    break;
                }
            }
        }
        if(hasCycle){
            pr.println("YES");
        } else {
            pr.println("NO");
        }
        pr.close();
    }
    static boolean dfs(ArrayList<Integer> [] adj, int u){
        visited[u] = 1; //visiting
        for(int v : adj[u]){
            if(visited[v] == 0){
                if(dfs(adj, v)){
                    return true;
                }
            } else if(visited[v] == 1){
                return true; 
            }
        }
        visited[u] = 2; //visited
        return false;   
    }
}
