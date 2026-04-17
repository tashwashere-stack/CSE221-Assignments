package A5;
import java.io.*;
import java.util.*;

public class p3 {

    //Using Fast Input because everytime it gives ungodly runtime error 
    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) return null;
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner();
        PrintWriter pr = new PrintWriter(System.out);

        int N = fs.nextInt();
        int M = fs.nextInt();
        int S = fs.nextInt();
        int D = fs.nextInt();

        // adjacency list
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] u = new int[M];

        for (int i = 0; i < M; i++) {
            u[i] = fs.nextInt();
        }

        for (int i = 0; i < M; i++) {
            int v = fs.nextInt();

            if (u[i] >= 1 && u[i] <= N && v >= 1 && v <= N) {
                adj[u[i]].add(v);
                adj[v].add(u[i]);
            }
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(adj[i]);
        }

        bfs(adj, N, S, D, pr);

        pr.flush();
    }

    static void bfs(ArrayList<Integer>[] adj, int N, int start, int end, PrintWriter pr) {

        Queue<Integer> q = new LinkedList<>();
        boolean[] vis = new boolean[N + 1];
        int[] parent = new int[N + 1];
        int[] dist = new int[N + 1];

        Arrays.fill(parent, -1);
        Arrays.fill(dist, -1);

        vis[start] = true;
        dist[start] = 0;
        parent[start] = -1;

        q.add(start);

        while (!q.isEmpty()) {
            int u = q.poll();

            if (u == end) break;

            for (int v : adj[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    parent[v] = u;
                    dist[v] = dist[u] + 1;
                    q.add(v);
                }
            }
        }

      
        if (!vis[end]) {
            pr.println(-1);
            return;
        }

        pr.println(dist[end]);

        ArrayList<Integer> path = new ArrayList<>();
        for (int cur = end; cur != -1; cur = parent[cur]) {
            path.add(cur);
        }

        Collections.reverse(path);

        for (int i = 0; i < path.size(); i++) {
            pr.print(path.get(i));
            if (i + 1 < path.size()) pr.print(" ");
        }
        pr.println();
    }
}