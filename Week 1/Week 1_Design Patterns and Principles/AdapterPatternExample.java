interface PaymentProcessor{
    public void processPayment(int amount);
}

class GooglePay{
    public void makePayment(int amount){
        System.out.println("Rs. "+amount+" payment made with Google Pay");
    }
}

class Paytm{
    public void executePayment(int amount){
        System.out.println("Rs. "+amount+" payment made with Paytm");
    }
}

class GooglePayAdapter implements PaymentProcessor{
    GooglePay pay;
    GooglePayAdapter(GooglePay pay){
        this.pay = pay;
    }
    public void processPayment(int amount){
        pay.makePayment(amount);
    }
}

class PaytmAdapter implements PaymentProcessor{
    Paytm paytm;
    PaytmAdapter(Paytm paytm){
        this.paytm = paytm;
    }
    public void processPayment(int amount){
        paytm.executePayment(amount);
    }
}

class AdapterPatternTest{
    public static void main(String[] args) {
        GooglePay pay = new GooglePay();
        Paytm paytm = new Paytm();
        PaymentProcessor payAdapter = new GooglePayAdapter(pay);
        PaymentProcessor paytmAdapter = new PaytmAdapter(paytm);
        payAdapter.processPayment(1000);
        paytmAdapter.processPayment(2000);
    }
}

