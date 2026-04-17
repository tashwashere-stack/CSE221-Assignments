package A5.A3;
import java.util.*;
import java.io.*;
public class p1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer (br.readLine());
        PrintWriter out = new PrintWriter (new BufferedWriter(new OutputStreamWriter(System.out)));

        int n = Integer.parseInt(st.nextToken());
        int arr[] = new int [n];
        st = new StringTokenizer (br.readLine());

        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        out.println(mergesort(arr, 0, n-1));
        for(int i = 0; i < n; i++){
            if(i == n-1){
                out.print(arr[i]);
                break;
            }
            out.print(arr[i] + " ");
        }
        out.println();
        out.flush();
        out.close();
    }

    public static long mergesort(int [] arr, int low, int high){
        long inversions = 0;
        if(low < high){
            int mid = low + (high-low)/2;

            inversions += mergesort(arr, low, mid);
            inversions += mergesort(arr, mid+1, high);
            inversions += merge(arr, low, mid, high);
        }

    return inversions;
    }

    public static long merge(int [] arr, int low, int mid, int high){

        int n1 = mid - low + 1;
        int n2 = high - mid;

        int [] left = new int [n1];
        int [] right = new int [n2];

        for(int i = 0 ; i < n1; i++){
            left[i] = arr[low+i];
        }

        for(int j = 0; j < n2; j++){
            right[j] = arr[mid + 1 + j];
        }

        int i= 0, j = 0, k = low;

        long inversions = 0;

        while(i < n1 && j < n2){
            if(left[i] <= right[j]){
                arr[k] = left[i];
                i++;
            } 
            else {
                arr[k] = right[j];
                inversions += (n1 - i);
                j++;
            }
            k++;  
        }
        while(i < n1){
            arr[k] = left[i];
            i++;
            k++;
        }
        while(j < n2){
            arr[k] = right[j];
            j++;
            k++;
        }



        return inversions;

    }

}
