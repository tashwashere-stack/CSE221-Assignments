import java.util.*;
import java.io.*;

public class p7 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);

        String line = br.readLine();
        if(line == null){
            return;
        }

        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        String word1 = st.nextToken();
        String word2 = st.nextToken();

        String[] words = new String[N];

        // store all words
        for(int i = 0; i < N; i++){
            words[i] = br.readLine();
        }

        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < 26; i++){
            adj.add(new ArrayList<>());
        }

        for(int i = 0; i < N; i++){
            int c = words[i].charAt(0) - 'A';
            adj.get(c).add(i);
        }

        // find indices of word1 and word2
        int start = -1, target = -1;
        for(int i = 0; i < N; i++){
            if(words[i].equals(word1)) start = i;
            if(words[i].equals(word2)) target = i;
        }

        if(bfs(start, target, words, adj)){
            pr.println("YES");
        } else {
            pr.println("NO");
        }

        pr.flush();
    }

    static boolean bfs(int start, int target, String[] words, ArrayList<ArrayList<Integer>> adj){
        boolean[] visited = new boolean[words.length];
        Queue<Integer> q = new ArrayDeque<>();

        q.add(start);
        visited[start] = true;

        while(!q.isEmpty()){
            int u = q.poll();

            if(u == target){
                return true;
            }

            char last = words[u].charAt(words[u].length() - 1);
            int bucket = last - 'A';

            for(int v : adj.get(bucket)){
                if(!visited[v]){
                    visited[v] = true;
                    q.add(v);
                }
            }
           adj.get(bucket).clear();
        }

        return false;
    }
}