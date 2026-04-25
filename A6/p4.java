import java.util.*;
import java.io.*;
public class p4 {
    static int [] distance;
    static int [] parent;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        ArrayList<Integer> [] adj = new ArrayList [N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 1; i < N; i++){
            st = new StringTokenizer(br.readLine());  
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
            adj[v].add(u);
        }
        //If we start from an arbitrary node in a tree and get to the farthest point, that is one end point of the tree diameter
        int nodeA = bfs(1, N, false, adj);
        int nodeB = bfs(nodeA, N, true, adj); //The end point
        List<Integer>path = new ArrayList<>();
        int curr = nodeB;
        while(curr != -1){
            path.add(curr);
            curr = parent[curr];
        }
        out.println(path.size()-1);
        out.println(path.get(path.size() -1) + " " + path.get(0));

        out.flush();
        out.close();
    }
    static int bfs(int start, int N, boolean record, ArrayList<Integer> [] adj){
        distance = new int [N + 1];
        parent = new int [N+1];
        Arrays.fill(distance, -1);
        Arrays.fill(parent, -1);

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        distance[start] = 0;

        int endpoint = start;

        while(!q.isEmpty()){
            int u = q.poll();
            //distance of the current node msut be greater than the distance of the farthest point
            if(distance[u] > distance[endpoint]) endpoint = u;

            for(int v : adj[u]){
                if(distance[v] == -1){
                    distance[v] = distance[u] + 1;
                    //When finding the endpoint for the second layer, we need to record the path, thats what we are doing here
                    if(record) parent[v] = u;
                    q.add(v);
                }
            }
        }
        return endpoint;

    }
}
