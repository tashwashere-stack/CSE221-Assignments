import java.util.*;
import java.io.*;
public class p1{
    public static void main(String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int T = Integer.parseInt(st.nextToken());
        for(int i = 0; i < T; i++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            //Initialzie the graph
            ArrayList<Integer> [] adj = new ArrayList [N+1];
            for(int j = 0; j <= N; j++){
                adj[j] = new ArrayList<Integer>();
            }
            int [] indegree_map = new int [N+1];
            for(int j = 0; j < M; j++){
                st = new StringTokenizer(br.readLine());
                int u = Integer.parseInt(st.nextToken());
                int v = Integer.parseInt(st.nextToken());
                adj[u].add(v);
                //Increase v beacuse it has an incoming edge
                indegree_map[v]++;
            }
            //Topological sort using Kahn's algorithm
            Queue<Integer> q = new LinkedList<Integer>();
            for(int j = 1; j <= N; j++){
                if(indegree_map[j] == 0){
                    //Add the element to the queue if it has no incoming edges
                    //As they are the doable ones
                    q.add(j);
                }
            }
            ArrayList<Integer> top_sort = new ArrayList<Integer>();
            while(!q.isEmpty()){
                int curr = q.poll();
                //Add to the sorted order
                top_sort.add(curr);

                for(int neighbor : adj[curr]){
                    //Delete the edge from curr to neighbour
                    indegree_map[neighbor]--;
                    //If the neighbour has 0 edges, add it to the queue as it becomes doable
                    if(indegree_map[neighbor] == 0){
                        q.add(neighbor);
                    }
                }
            }
            if(top_sort.size() < N){
                    out.println("-1");
                    //Because we found a cycle
                }
                else{
                    for(int j = 0; j < top_sort.size(); j++){
                        out.print(top_sort.get(j) + " ");
                    }
                }

        }
        out.flush();
        out.close();
    }
} 
