package A5.A3;
import java.io.*;
import java.util.*;
public class p7{
    static int pre = 0;
    static int post = 0;
    static HashMap<Integer, Integer> inordermap = new HashMap<>();
    public static void main (String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int N = Integer.parseInt(st.nextToken());

        int [] in_order = new int [N];
        int [] pre_order = new int [N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            in_order[i] = Integer.parseInt(st.nextToken());
            //stores the value - index 
            inordermap.put(in_order[i], i);
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            pre_order[i] = Integer.parseInt(st.nextToken());
        }

        int [] post_order = new int [N];
        post_order(pre_order, in_order, post_order, 0, N-1);

        for (int i = 0; i < N; i++){
            out.print(post_order[i] + " ");
        }
        out.flush();
    }

    public static void post_order(int [] pre_order, int [] in_order, int [] post_order, int start, int end){
        if (start > end){
            return;
        }
        //takes values from the pre order array
        int root = pre_order[pre++];
        post_order(pre_order, in_order, post_order, start, inordermap.get(root)-1);
        post_order(pre_order, in_order, post_order, inordermap.get(root)+1, end);
        //assigns values in the post order array
        post_order[post++] = root;
    }

}