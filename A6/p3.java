import java.util.*;
import java.io.*;
public class p3 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        String line = br.readLine();
        if(line == null){
            return;
        }
        StringTokenizer st = new StringTokenizer(line);
        int N = Integer.parseInt(st.nextToken());
        int [] [] grid = new int [N + 1][N + 1];
        for(int [] row : grid){
            Arrays.fill(row, -1);
            //Fills the grid with -1 at the start
        }
        st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());

        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());

        //Check if we are already at the desdtination
        if(x1 == x2 && y1 == y2){
            out.println(0);
            out.flush();
            return;
        }
        //Move log
        int [] dx = {2, 2, 1, -1, -2, -2, 1, -1};
        int [] dy = {1, -1, 2, 2, 1, -1, -2, -2};

        Queue<int []> q = new LinkedList<>();
        //We are turning the starting poisition into an object, because the queue cannot hold two integers at once
        q.add(new int[]{x1, y1});
        grid[x1][y1] = 0; //Denote starting position as 0

        while(!q.isEmpty()){
            int [] curr = q.poll();
            int cx = curr[0]; //this is our x
            int cy = curr[1]; //this is our y

            //Now looping through 8 possible moves
            for(int i = 0; i < 8; i++){
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                if(nx >= 1 && nx <= N && ny >= 1 && ny <= N && grid[nx][ny] == -1){
                    grid[nx][ny] = grid[cx][cy] + 1;
                    q.add(new int [] {nx, ny});
                    //Updates the amount of moves it took to reach the new square 
                }
                if(nx == x2 && ny == y2){
                    out.println(grid[nx][ny]);
                    out.flush();
                    return;
                }
            }
        }
        out.println("-1");
        out.flush();
        out.close();
    }
}
