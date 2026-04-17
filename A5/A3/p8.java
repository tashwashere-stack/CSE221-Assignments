package A5.A3;
import java.io.*;
import java.util.*;
public class p8{
    static int pre = 0;
    static int post;
    static HashMap<Integer, Integer> inordermap = new HashMap<>();
    public static void main (String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int N = Integer.parseInt(st.nextToken());
        int [] in_order = new int [N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            in_order[i] = Integer.parseInt(st.nextToken());
            //stores the value - index
            inordermap.put(in_order[i], i);
        }
        int [] post_order = new int [N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            post_order[i] = Integer.parseInt(st.nextToken());
        }
        //Point pointer to the last element
        post = N - 1;
        pre = N - 1;
        int [] pre_order = new int [N];
        pre_order(post_order, in_order, pre_order, 0, N-1);
        for (int i = 0; i < N; i++){
            out.print(pre_order[i] + " ");
        }
        out.flush();
    }

    public static void pre_order(int [] post_order, int [] in_order, int [] pre_order, int start, int end){
        if (start > end){
            return;
        }
        // //Get root from post order and move pointer forward
        // int root = post_order[post--];
        // pre_order(post_order, in_order, pre_order, inordermap.get(root)+1, end);
        // pre_order(post_order, in_order, pre_order, start, inordermap.get(root)-1);
        // // pre_order[pre++] = root;
        // pre_order[post+1] = root;

        int root = post_order[post--];
        int mid = inordermap.get(root);
        pre_order(post_order, in_order, pre_order, mid+1, end);
        pre_order(post_order, in_order, pre_order, start, mid-1);
        pre_order[pre--] = root;
    }

}