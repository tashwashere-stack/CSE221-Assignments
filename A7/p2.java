import java.util.*;
import java.io.*;
public class p2 {
    public static class Edge{
        int destination;
        int weight;
        public Edge(int destination, int weight){
            this.destination = destination;
            this.weight = weight;
        }
    }

    public static class pair implements Comparable <pair>{
        int Node;
        long dist;
        public pair(int Node, long dist){
            this.Node = Node;
            this.dist = dist;
        }
        public int compareTo(pair other){
            return Long.compare(this.dist, other.dist);
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
        int T = Integer.parseInt(st.nextToken());

        ArrayList<Edge> adj [] = new ArrayList[N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            Edge e= new Edge(v,w);
            adj[u].add(e);
        }
        long distAlice [] = dijkstra(S, adj, N);
        long distBob [] = dijkstra(T, adj, N);

        long min = Long.MAX_VALUE; //the minimum time
        int best = -1; //the node with minimum time 

        for(int i = 1; i <= N; i++){
            if(distAlice[i] != Long.MAX_VALUE && distBob[i] != Long.MAX_VALUE){
                long currtime = Math.max(distAlice[i], distBob[i]);
                if(currtime < min){
                    min = currtime;
                    best = i;
                }
                else if(currtime == min){
                    if(best == -1 /* Unvisited Still, the one we have now is the best*/ || i < best /*This is smaller than the one we curently have*/){
                        best = i;
                    }
                }
            }
        }

        if(best == -1){
            out.print("-1");
        }
        else{
        out.print(min + " " + best);
        }

         out.flush();
    }
    public static long[] dijkstra(int source, ArrayList<Edge> adj[], int N){
        long [] distance = new long[N+1];
        Arrays.fill(distance, Long.MAX_VALUE);

        distance[source] = 0;
        PriorityQueue<pair> pq = new PriorityQueue<>();
        pq.add(new pair(source, 0));
        while(!pq.isEmpty()){
            pair curr = pq.poll();
            if(curr.dist < distance[curr.Node]){
                continue;
            }
            for(Edge e : adj[curr.Node]){
                if(distance[curr.Node] + e.weight < distance[e.destination]){
                    distance[e.destination] = distance[curr.Node] + e.weight;
                    pq.add(new pair(e.destination, distance[e.destination]));
                }
            }
        }


        return distance;
    }
}
