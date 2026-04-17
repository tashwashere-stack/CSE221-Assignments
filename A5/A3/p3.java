package A5.A3;
import java.util.Scanner;
public class p3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();

        long mod = 107;

        if(a % mod == 0){
            System.out.println(0);
        }
        else{
            long reducedb = b % (mod - 1);
            if(reducedb == 0){
                reducedb = mod - 1;
            }
            System.out.println(fastExp(a, reducedb, mod));
        }
        
    }

    public static long fastExp(long base, long exp, long mod){
        long result = 1;
        base = base % mod;

        while(exp > 0){
            if(exp % 2 == 1){
                result = (result * base) % mod;
            }
            base = (base * base) % mod;
            exp = exp / 2;
        }
        return result;
    }
    

}
