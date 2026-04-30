import java.util.*;
import java.io.*;
public class p5 {
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
        long weight;
        int lastparity; //parity of the last added edge in a path
        public pair(int node, long weight, int parity){
            this.node = node;
            this.weight = weight;
            this.lastparity = parity;
        }
        public int compareTo(pair other){
            return Long.compare(this.weight, other.weight);
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
        ArrayList<Edge> adj [] = new ArrayList [N+1];
        int uArr [] = new int [M+1];
        int vArr [] = new int [M+1];
        int wArr [] = new int [M+1];

        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        StringTokenizer stU = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) uArr[i] = Integer.parseInt(stU.nextToken());

        StringTokenizer stV = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) vArr[i] = Integer.parseInt(stV.nextToken());

        StringTokenizer stW = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) wArr[i] = Integer.parseInt(stW.nextToken());

        for(int i = 0; i < M; i++){
            adj[uArr[i]].add(new Edge(vArr[i], wArr[i]));
        }

        long dist[][] = new long [N+1][2];
        for (int i = 1; i <= N; i++) Arrays.fill(dist[i], Long.MAX_VALUE);
        dist[1][1] = 0;
        dist[1][0] = 0;

        PriorityQueue<pair>pq = new PriorityQueue<>();
        pq.add(new pair(1, 0, 0));
         pq.add(new pair(1, 0, 1));
        while(!pq.isEmpty()){
            pair curr = pq.poll();

            if(curr.weight > dist[curr.node][curr.lastparity]){
                continue;
            }

            for(Edge e : adj[curr.node]){
                int edgeparity = (int)e.weight % 2;
                if(edgeparity != curr.lastparity){
                    long newcost = curr.weight + e.weight;
                
                    if(newcost < dist[e.destination][edgeparity]){
                        dist[e.destination][edgeparity] = newcost;
                        pq.add(new pair(e.destination, dist[e.destination][edgeparity], edgeparity));
                    }
                }
            }
        }
        long result = Math.min(dist[N][0], dist[N][1]);
        if(result == Long.MAX_VALUE){
            out.print(-1);
        }
        else{
            out.print(result);
        }

        out.flush();
        out.close();

    }
}
