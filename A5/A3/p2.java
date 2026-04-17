package A5.A3;
import java.io.*;
import java.util.*;
public class p2{
    static long count = 0;
    public static void main (String [] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int N = Integer.parseInt(st.nextToken());
        long [] arr = new long [N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++){
            arr[i] = Long.parseLong(st.nextToken());
        }
        mergesort(arr, 0 , N-1);
        out.println(count);
        out.flush();
    }

    public static void mergesort(long [] arr, int start, int end){
        if (start >= end){
            return;
        }
        int mid = start + (end - start) / 2;
        mergesort(arr, start, mid);
        mergesort(arr, mid+1, end);

        //counting logic

        long [] squaresortright = new long [end - mid];
        for (int i = 0; i < squaresortright.length; i++){
            squaresortright[i] = arr[mid + 1 + i];
        }
        SquaredValueSort(squaresortright);



        // int i = mid + 1;
        // for (int j = start; j <= mid; j++){
        //     while (i <= end && arr[j] > (arr[i] * arr[i])){
        //         i++;
        //     }
        //     count += (i - (mid + 1));
        // }

        int i = 0;
        for (int j = start; j <= mid; j++){
            while (i < squaresortright.length && arr[j] > (squaresortright[i] * squaresortright[i])){
                i++;
            }
            count += i;
        }

        //merge logic 
        long [] merged_arr = new long [end - start + 1];
        int left = start;
        int right = mid + 1;
        int index = 0;
        while (left <= mid && right <= end){
            if (arr[left] <= arr[right]){
                merged_arr[index++] = arr[left++];
            } else {
                merged_arr[index++] = arr[right++];
            }
        }
        while (left <= mid){
            merged_arr[index++] = arr[left++];
        }
        while (right <= end){
            merged_arr[index++] = arr[right++];
        }
        //copy final array 
        for (int j = 0; j < merged_arr.length; j++){
            arr[start + j] = merged_arr[j];
        }
    }

    public static void SquaredValueSort(long[]arr){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < 0){
                arr[i] = Math.abs(arr[i]);
            } 
        }
        //sort the subpart
        Arrays.sort(arr);
    }
}