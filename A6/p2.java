import java.util.*;
import java.io.*;
public class p2 {
    static int [] colour;
    static int robotcount = 0;
    static int humancount = 0;
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
        ArrayList<Integer> graph [] = new ArrayList [N+1];
        for(int i = 0; i <= N; i++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            graph[u].add(v);
            graph[v].add(u);
        }
        colour = new int [N+1];  
        int maxcount = 0;
        for(int i = 1; i <= N; i++){
            if(colour[i] == 0){
                robotcount = 0;
                humancount = 0;
                //1 for humans
                //2 for robots
                //We randomly chose 1 to trigger the dfs
                dfs(i, graph, 1);
                maxcount += Math.max(robotcount, humancount);
            }
        }
        out.println(maxcount);

        out.flush();
        out.close();
    }

    static void dfs(int u, ArrayList<Integer> graph [], int col){
        //start the traversal and set the colour to 1 as visiting
        colour[u] = col;
        if(col == 1){
            humancount++;
        }
        else{
            robotcount++;
        }

        for(int v : graph[u]){
            if(colour[v] == 0){
                int neighbour_colour;
                if(col == 1){
                    neighbour_colour = 2;
                }
                else{
                    neighbour_colour = 1;
                }
                dfs(v, graph, neighbour_colour);
            }
        }
    }
}
