import java.util.*;
import java.io.*;
public class p4 {
    static class pair implements Comparable <pair>{
        int Node;
        long weight;
        public pair(int Node, long weight){
            this.Node = Node;
            this.weight = weight;
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
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        long [] NodeWeights = new long[N+1];
        ArrayList<Integer> adj[] = new ArrayList [N+1];
        for(int i = 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            NodeWeights[i] = Long.parseLong(st.nextToken());
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            adj[u].add(v);
        }
        PriorityQueue<pair>pq = new PriorityQueue <>();
        long [] dist = new long [N+1];
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[S] = NodeWeights[S];
        pq.add(new pair(S, dist[S]));
        while(!pq.isEmpty()){
            pair curr = pq.poll();
            if(curr.weight < dist[curr.Node]) continue;

            for(int v : adj[curr.Node]){ //We did not need an edge and stored all the integer values
                long newcost = curr.weight /*My cost till now */ + NodeWeights[v]; /*Neighbour's cost */
                if(newcost < dist[v]){
                    dist[v] = newcost;
                    pq.add(new pair(v, dist[v]));
                }

            }
        }
        if(dist[D] == Long.MAX_VALUE){
            out.print("-1");
        }
        else{
            out.print(dist[D]); //print the total cost of the path - the cost to reach from source to distance
        }
        out.flush();
        out.close();


    }
}
