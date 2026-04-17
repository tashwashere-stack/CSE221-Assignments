package A5;
import java.util.*;
import java.io.*;
public class p8 {
    static boolean [] [] visited;
    static int [] dr = {1, -1, 0, 0};
    static int [] dc = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pr = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int R = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        char [] [] grid = new char[R+1][H+1];
        visited = new boolean[R+1][H+1];
        for(int i = 1; i <= R; i++){
            String inputline = br.readLine();
            for(int j = 1; j <= H; j++){
                //The inputs are given as 0,1,2 and the column reads 1,2,3
                grid[i][j] = inputline.charAt(j-1);
            }
        }
        int max = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < H; j++){
                if(grid[i][j] != '#' && !visited[i][j]){
                    int count = dfs(grid, i, j);
                    max = Math.max(max, count);
                }       

            }
        }
        pr.println(max);
        pr.close();
    }
    static int dfs(char [] [] grid, int r, int c){
        visited[r][c] = true;
        int count = 0;
        if(grid[r][c] == 'D'){
            count++;
        }
        for(int i = 0; i < 4; i++){
            int nr = r + dr[i];
            int nc = c + dc[i];
            //CHeck if the index is valid
            if(nr >= 1 && nr < grid.length && nc >= 1 && nc < grid[0].length && grid[nr][nc] != '#' && !visited[nr][nc]){
                count += dfs(grid, nr, nc);
            }
        }
        return count;
    }
}
