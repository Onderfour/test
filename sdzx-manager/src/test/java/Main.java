import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        if(isPrime(number)){
            System.out.println(number+"是质数");
        }else{
            System.out.println(number+"不是质数");
        }

    }
    public static boolean isPrime(int number){
        if(number<=1){
            return false;
        }
        for(int i = 2;i<=Math.sqrt(number);i++){
            if(number%i==0){
                return false;
            }
        }
        return true;
    }
}