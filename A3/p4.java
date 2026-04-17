package A3;
import java.util.*;
import java.io.*;
public class p4 {
    static final long MOD = 1000000007L;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        int Q = Integer.parseInt(st.nextToken());

        for(int i = 0; i < Q; i++){
            // long [][] A = new long [2][2];

            st = new StringTokenizer(br.readLine());

            // for(int j = 0; j < 2; j++){
            //     for(int k = 0; k < 2; k++){
            //         A[j][k] = Long.parseLong(st.nextToken()) % MOD;
            //     }
            // }

            // A[0][0] = Long.parseLong(st.nextToken()) % MOD;
            // A[0][1] = Long.parseLong(st.nextToken()) % MOD;
            // A[1][0] = Long.parseLong(st.nextToken()) % MOD;
            // A[1][1] = Long.parseLong(st.nextToken()) % MOD;

            long a00 = Long.parseLong(st.nextToken()) % MOD;
            long a01 = Long.parseLong(st.nextToken()) % MOD;
            long a10 = Long.parseLong(st.nextToken()) % MOD;
            long a11 = Long.parseLong(st.nextToken()) % MOD;

            st = new StringTokenizer(br.readLine());

            Long X = Long.parseLong(st.nextToken());

            // long [][] res = 
            // {
            //     {1, 0},
            //     {0, 1}
            // };

            long res00 = 1;
            long res01 = 0;
            long res10 = 0;
            long res11 = 1;

            while(X > 0){
                // if(X % 2 == 1){
                //     res = multiply(A, res);
                // }
                // A = multiply(A, A);
                // X = X/2;

                if(X % 2 == 1){
                    //Multiplies A and res
                    long temp00 = (res00 * a00 + res01 * a10) % MOD;
                    long temp01 = (res00 * a01 + res01 * a11) % MOD;
                    long temp10 = (res10 * a00 + res11 * a10) % MOD;
                    long temp11 = (res10 * a01 + res11 * a11) % MOD;
                    res00 = temp00; res01 = temp01; res10 = temp10; res11 = temp11;
                }

                //Squares the A matrix

                long aa00 = (a00 * a00 + a01 * a10) % MOD;
                long aa01 = (a00 * a01 + a01 * a11) % MOD;
                long aa10 = (a10 * a00 + a11 * a10) % MOD;
                long aa11 = (a10 * a01 + a11 * a11) % MOD;
                a00 = aa00; a01 = aa01; a10 = aa10; a11 = aa11;

                X = X/2;
            }

            out.println(res00 + " " + res01);
            out.println(res10 + " " + res11);
           
        }
        out.flush();
        out.close();
    }

        //  public static long [] [] multiply(long [] [] B, long [] [] C){
        //     long [] [] D = new long [2] [2];

        //     // for(int i = 0; i < 2; i++){
        //     //     for(int j = 0; j < 2; j++){
        //     //         long out = (B[i][0] * C[0][j]) % MOD;
        //     //         out = (out + ((B[i][1] * C[1][j])%MOD)) % MOD;

        //     //         D[i][j] = out;
        //     //     }
        //     // }

        //     D[0][0] = (((B[0][0]*C[0][0])%MOD)+((B[0][1]*C[1][0])%MOD))%MOD;
        //     D[0][1] = (((B[0][0]*C[0][1])%MOD)+((B[0][1]*C[1][1])%MOD))%MOD;
        //     D[1][0] = (((B[1][0]*C[0][0])%MOD)+((B[1][1]*C[1][0])%MOD))%MOD;
        //     D[1][1] = (((B[1][0]*C[0][1])%MOD)+((B[1][1]*C[1][1])%MOD))%MOD;


        //     return D;
        // }
}
