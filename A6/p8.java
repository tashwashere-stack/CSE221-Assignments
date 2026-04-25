import java.io.*;
import java.util.*;

public class p8 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);

        int N = Integer.parseInt(br.readLine());
        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        // Graph + indegree
        ArrayList<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            adj.add(new ArrayList<>());
        }

        int[] indegree = new int[26];
        boolean[] used = new boolean[26];

        // Mark used characters
        for (String w : words) {
            for (int i = 0; i < w.length(); i++) {
                used[w.charAt(i) - 'a'] = true;
            }
        }

        // Build graph from adjacent words
        for (int i = 0; i < N - 1; i++) {
            String a = words[i];
            String b = words[i + 1];

            int len = Math.min(a.length(), b.length());
            boolean found = false;

            for (int j = 0; j < len; j++) {
                if (a.charAt(j) != b.charAt(j)) {
                    int u = a.charAt(j) - 'a';
                    int v = b.charAt(j) - 'a';

                    // avoid duplicate edges
                    if (!adj.get(u).contains(v)) {
                        adj.get(u).add(v);
                        indegree[v]++;
                    }

                    found = true;
                    break;
                }
            }

            // prefix invalid case
            if (!found && a.length() > b.length()) {
                pr.println(-1);
                pr.flush();
                return;
            }
        }

        // Min-heap for lexicographically smallest order
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 0; i < 26; i++) {
            if (used[i] && indegree[i] == 0) {
                pq.add(i);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty()) {
            int u = pq.poll();
            result.append((char)(u + 'a'));

            for (int v : adj.get(u)) {
                indegree[v]--;
                if (indegree[v] == 0) {
                    pq.add(v);
                }
            }
        }

        // count how many letters are actually used
        int totalUsed = 0;
        for (int i = 0; i < 26; i++) {
            if (used[i]) totalUsed++;
        }

        // cycle / invalid check
        if (result.length() != totalUsed) {
            pr.println(-1);
        } else {
            pr.println(result.toString());
        }

        pr.flush();
        pr.close();
    }
}