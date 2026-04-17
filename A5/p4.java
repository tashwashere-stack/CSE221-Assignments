package A5;
import java.io.*;
import java.util.*;

public class p4 {

    static class FastScanner {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String next() throws IOException {
            while (st == null || !st.hasMoreTokens()) {
                String line = br.readLine();
                if (line == null) throw new EOFException(); 
                st = new StringTokenizer(line);
            }
            return st.nextToken();
        }

        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner sc = new FastScanner();
        PrintWriter pr = new PrintWriter(System.out);

        int N = sc.nextInt();
        int M = sc.nextInt();
        int S = sc.nextInt();
        int D = sc.nextInt();
        int K = sc.nextInt();

       
        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

   
        for (int i = 0; i < M; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();

          
            if (u < 1 || u > N || v < 1 || v > N) {
                throw new RuntimeException("Invalid edge: " + u + " " + v);
            }

            adj[u].add(v);
        }

       
        List<Integer> first = bfs(adj, N, S, K);
        List<Integer> second = bfs(adj, N, K, D);

        if (first == null || second == null) {
            pr.println(-1);
            pr.flush();
            return;
        }

       
        List<Integer> path = new ArrayList<>(first);
        path.remove(path.size() - 1);
        path.addAll(second);

        pr.println(path.size() - 1);

        for (int i = 0; i < path.size(); i++) {
            pr.print(path.get(i));
            if (i + 1 < path.size()) pr.print(" ");
        }
        pr.println();

        pr.flush();
    }


    static List<Integer> bfs(ArrayList<Integer>[] adj, int N, int start, int end) {

        if (start < 1 || start > N || end < 1 || end > N) return null;

        if (start == end) {
            List<Integer> single = new ArrayList<>();
            single.add(start);
            return single;
        }

        Queue<Integer> q = new ArrayDeque<>();
        boolean[] vis = new boolean[N + 1];
        int[] parent = new int[N + 1];

        Arrays.fill(parent, -1);

        vis[start] = true;
        q.add(start);

        while (!q.isEmpty()) {
            int u = q.poll();

            if (u == end) break;

            for (int v : adj[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    parent[v] = u;
                    q.add(v);
                }
            }
        }

        if (!vis[end]) return null;


        List<Integer> path = new ArrayList<>();
        for (int cur = end; cur != -1; cur = parent[cur]) {
            path.add(cur);
        }
        Collections.reverse(path);

        return path;
    }
}