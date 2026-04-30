import java.io.*;
import java.util.*;
public class p1 {
    public static class Edge{
        int weight;
        int destination;
        public Edge(int destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }
    }
    public static class Pair implements Comparable<Pair>{
        int Node;
        long dist;
        public Pair(int Node, long dist){
            this.Node = Node;
            this.dist = dist;
        }
        public int compareTo(Pair other){
            return Long.compare(this.dist, other.dist);
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
        int S = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        ArrayList<Edge> adj [] = new ArrayList [N+1];
        for(int i= 0; i <= N; i++){
            adj[i] = new ArrayList<>();
        }

        int[] uArr = new int[M];
        int[] vArr = new int[M];
        int[] wArr = new int[M];

        StringTokenizer stU = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) uArr[i] = Integer.parseInt(stU.nextToken());

        StringTokenizer stV = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) vArr[i] = Integer.parseInt(stV.nextToken());

        StringTokenizer stW = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++) wArr[i] = Integer.parseInt(stW.nextToken());


        for(int i = 0; i < M; i++) {
            adj[uArr[i]].add(new Edge(vArr[i], wArr[i]));
        }

        long [] dist = new long [N+1];
        int [] parent = new int [N+1]; //Need this only because we have to print the path
        Arrays.fill(dist, Long.MAX_VALUE);
        dist[S] = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(S,0));

        while(!pq.isEmpty()){
            Pair curr = pq.poll(); //Always calls the node witht he shortest distance as it is a mean heap structure
            if(curr.dist > dist[curr.Node]){ 
                continue; //We already have the best version stored
            }
            for(Edge e : adj[curr.Node]){ 
                if(dist[curr.Node] /*(the shortest distance from the source to current node ) */ + e.weight /* the direct cost of reaching the neighbour */ < dist[e.destination] /*Our best known record */ ){
                    dist[e.destination] = dist[curr.Node] + e.weight; //update the distance array
                    parent[e.destination] = curr.Node; //update trhe parent of the neighbour node and add the current node as a parent 
                    pq.add(new Pair(e.destination, dist[e.destination])); //Create a new pair of 
                }
            }
        }

        if(dist[D] == Long.MAX_VALUE){
            pr.println("-1");
        }
        else{
            pr.println(dist[D]);
            List<Integer> path = new ArrayList<>();
            int curr = D;
            while(curr != 0){
                path.add(curr);
                curr = parent[curr];
            }
            Collections.reverse(path);
            for(int i = 0 ; i < path.size(); i++){
                pr.print(path.get(i) + " ");
            }
        }

        pr.flush();
    }
}
