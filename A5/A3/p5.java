package A5.A3;
import java.io.*;
import java.util.*;
public class p5 {
    static long MOD;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        long Q = Long.parseLong(st.nextToken());

        for(int i = 0; i < Q; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());
            MOD = Long.parseLong(st.nextToken());

            //Constant Matrix 

            // long [] [] const_matrix = {
            //     {a, a},
            //     {0, 1}
            // };

            
            long cm00 = a % MOD;
            long cm01 = a % MOD;
            long cm10 = 0 % MOD;
            long cm11 = 1 % MOD;

            //Primary Matrix

            // {
            //     {Sk}, 
            //     {1},
            // }

            // When no terms are added, Sk = 0
            // {
            //     {0}, 
            //     {1},
            // }

            long pm0 = 0;
            long pm1 = 1;



            //Raising the constant matrix to n
            //Identity

            long I00 = 1;
            long I01 = 0;
            long I10 = 0;
            long I11 = 1;

            while(n > 0){

                if(n % 2 ==1){
                    long temp00 = ((I00 * cm00) + (I01 * cm10)) % MOD;
                    long temp01 = ((I00 * cm01) + (I01 * cm11)) % MOD;
                    long temp10 = ((I10 * cm00) + (I11 * cm10)) % MOD;
                    long temp11 = ((I10 * cm01) + (I11 * cm11)) % MOD;

                    //After the first multiplication, the outputs need to be stored in the 
                    //Identity matrix, so when we multiply with the cm matrix in the next step
                    //We get the next multiple and not the cm matrix back

                    I00 = temp00; I01 = temp01; I10 = temp10; I11 = temp11;
                }

                //Square the constant matrix

                //The cm matrix becomes the new squared output, ccm (cm^2), when squared again,
                //It becomes (cm^4) = (ccm^2)
                //Thus, we need to modify the original cm matrix, so it becomes ccm X ccm
                //And not cm X ccm

                long ccm00 = (cm00 * cm00 + cm01 * cm10) % MOD;
                long ccm01 = (cm00 * cm01 + cm01 * cm11) % MOD;
                long ccm10 = (cm10 * cm00 + cm11 * cm10) % MOD;
                long ccm11 = (cm10 * cm01 + cm11 * cm11) % MOD;
                cm00 = ccm00; cm01 = ccm01; cm10 = ccm10; cm11 = ccm11;

                n = n/2;
            }

            out.println(I01);
        }
        out.flush();
    }
    
}
