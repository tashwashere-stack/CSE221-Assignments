package A3;
import java.io.*;
import java.util.*;
public class p6 {
    public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    
    int N = Integer.parseInt(st.nextToken());
    long [] arr = new long [N];

    st = new StringTokenizer(br.readLine());

    for(int i = 0; i < N; i++){
        arr[i] = Long.parseLong(st.nextToken());
    }

    optimumnum_finder(arr, 0, N-1, out);

    //flush in the main method
    out.flush();
    }

    public static void optimumnum_finder(long [] arr, int low, int high, PrintWriter out){
        if(low <= high){
            int mid = low + (high - low) / 2 ;

            //Print here as storing in an array will take time 

            out.print(arr[mid] + " ");

            //Codeforces ignores the trailing space

            //recursion to find the others

            optimumnum_finder(arr, low, mid -1 , out); //push left
            optimumnum_finder(arr, mid + 1, high, out); //push right
        }

    }
}
