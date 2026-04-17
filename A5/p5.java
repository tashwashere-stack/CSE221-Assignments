package A5;
import java.util.*;
import java.io.*;
public class p5 {
    static int [] parent, size;

    static void makeSet(int n){
        parent = new int[n+1];
        size = new int[n+1];
        for(int i = 1; i <= n; i++){
            //every node is its own parent at the start
            parent[i] = i;
            size[i] = 1;
        }
    }

    static int find(int u){
        if(parent[u] != u){
            //recursively finds the root
            parent[u] = find(parent[u]);
        }
        return parent[u];
    }
    
    static void union(int u, int v){
        int rootU = find(u);
        int rootV = find(v);
        if(rootU != rootV){
            if(size[rootU] < size[rootV]){
                parent[rootU] = rootV;
                size[rootV] += size[rootU];
            } else {
                parent[rootV] = rootU;
                size[rootU] += size[rootV];
            }
        }
    }
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
        int Q = Integer.parseInt(st.nextToken());

        makeSet(N);
        
        for(int i = 0; i < M; i++){
            // String line1 = br.readLine();
            st = new StringTokenizer(br.readLine());
            // if(line1 == null || line1.trim().isEmpty()) continue;
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u, v);
        }

        for(int i = 0; i < Q; i++){
            // String line2 = br.readLine();
            st = new StringTokenizer(br.readLine());
            // if(line2 == null || line2.trim().isEmpty()) continue;
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            if(find(u) == find(v)){
                pr.println("YES");
            } else {
                pr.println("NO");
            }
        }
        pr.flush();
        pr.close();
    }
}
