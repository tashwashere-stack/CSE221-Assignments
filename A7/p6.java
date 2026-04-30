import java.util.*;
import java.io.*;
public class p6 {
    static class Edge{
        int destination;
        long weight;
        public Edge(int destination, long weight){
            this.destination = destination;
            this.weight = weight;
        }
    }
    static class pair implements Comparable <pair>{
        int node;
        long distance;
        public pair(int node, long distance){
            this.node = node;
            this.distance = distance;
        }
        public int compareTo(pair other){
            return Long.compare(this.distance, other.distance);
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
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<Edge> adj [] = new ArrayList [N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList <>();
        }

        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            adj[u].add(new Edge(v,w));
            adj[v].add(new Edge(u,w));
        }

        long [] [] dist = new long [N+1][2];
        for(int i = 1; i <= N; i++){
            Arrays.fill(dist[i], Long.MAX_VALUE);
        }
        dist[S][0] = 0;
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(S,dist[S][0]));
        while(!pq.isEmpty()){
            pair curr = pq.poll();

            if(curr.distance > dist[curr.node][1]){
                continue;
            }
            for(Edge e : adj[curr.node]){
                long newcost = curr.distance + e.weight;
                if(newcost < dist[e.destination][0]){
                    dist[e.destination][1] = dist[e.destination][0];
                    dist[e.destination][0] = newcost;
                    pq.add(new pair(e.destination, dist[e.destination][0]));
                    pq.add(new pair(e.destination, dist[e.destination][1]));
                }
                else if(newcost > dist[e.destination][0] && newcost < dist[e.destination][1]){
                    dist[e.destination][1] = newcost;
                    pq.add(new pair(e.destination, dist[e.destination][1]));
                }
            }
        }
        long answer = dist[D][1];
        if(answer == Long.MAX_VALUE){
            out.print(-1);
        }
        else{
            out.print(answer);
        }

        out.flush();
        out.close();
    }
}
