package A5;
import java.io.*;
import java.util.*;

public class p2 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        int[] u = new int[M];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            u[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int v = Integer.parseInt(st.nextToken());
            adj[u[i]].add(v);
            adj[v].add(u[i]);
        }

        for (int i = 1; i <= N; i++) { 
            Collections.sort(adj[i]);
        }

        int[] vis = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            if (vis[i] == 0) {
                dfs(adj, i, vis, pr);
            }
        }

        pr.flush();
    }

    static void dfs(ArrayList<Integer>[] adj, int start, int[] vis, PrintWriter pr) {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int u = stack.pop();

            if (vis[u] == 1) continue;

            vis[u] = 1;
            pr.print(u + " ");

            List<Integer> list = adj[u];
            for (int i = list.size() - 1; i >= 0; i--) {
                int v = list.get(i);
                if (vis[v] == 0) stack.push(v);
            }
        }
    }
}