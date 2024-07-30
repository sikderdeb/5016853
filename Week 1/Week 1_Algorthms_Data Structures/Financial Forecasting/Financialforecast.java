import java.util.Scanner;

public class Financialforecast{
    public static float forecast(int principal,float rate,int time){
        if(time==0)
            return principal;
        return((1+rate)*forecast(principal, rate, time-1));
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.print("Enter present value: ");
        int principal=sc.nextInt();
        System.out.print("Enter growth rate as percentage: ");
        float rate = (sc.nextFloat())/100.0f;
        System.out.print("Enter number of periods: ");
        int time = sc.nextInt();
        
        float predicted_amt=forecast(principal, rate, time);
        System.out.println("Future Value will be " + predicted_amt);
        sc.close();
    }
}