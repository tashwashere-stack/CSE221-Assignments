import java.util.*;
import java.io.*;
public class p3 {
    static class Edge{
        int destination;
        int weight;
        public Edge(int destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class pair implements Comparable<pair>{
        int Node;
        long danger;
        public pair(int Node, long danger){
            this.Node = Node;
            this.danger = danger;
        }
        public int compareTo(pair other){
            return Long.compare(this.danger, other.danger);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Edge>adj[] = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Edge e = new Edge(v,w);
            adj[u].add(e);
            adj[v].add(new Edge(u,w));
        }
        //Implement Dijkstra
        long [] dangermap = new long[N+1];
        Arrays.fill(dangermap, Long.MAX_VALUE);
        dangermap[1] = 0;

        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(1,0));

        while(!pq.isEmpty()){
            pair curr = pq.poll();

            if(curr.danger > dangermap[curr.Node]) continue;

            for(Edge e : adj[curr.Node]){
                long pathdanger = Math.max(dangermap[curr.Node], e.weight);
                if(pathdanger < dangermap[e.destination]){
                    dangermap[e.destination] = pathdanger;
                    pq.add(new pair(e.destination, dangermap[e.destination])); //create new map with the optimized danger
                }

            }
        }

        for(int i = 1; i <= N; i++){
            if(dangermap[i] == Long.MAX_VALUE){
                out.print("-1" + " ");
            }
            else{
                out.print(dangermap[i] + " ");
            }
        }
        out.flush();
        out.close();


    }
}
