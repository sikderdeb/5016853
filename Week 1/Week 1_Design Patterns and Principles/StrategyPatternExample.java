interface PaymentStrategy{
    public void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy{
    private String cardNumber;
    CreditCardPayment(String cardNumber){
        this.cardNumber = cardNumber;
    }
    public void pay(int amount){
        System.out.println("Paid " + amount + " using credit card number: " +cardNumber);
    }
}

class PayPalPayment implements PaymentStrategy{
    private String paypalNumber;
    PayPalPayment(String paypalNumber){
        this.paypalNumber = paypalNumber;
    }
    public void pay(int amount){
        System.out.println("Paid " + amount + " using paypal number: " +paypalNumber);
    }
}

class PaymentContext{
    private PaymentStrategy p_stratergy;
    PaymentContext(PaymentStrategy p_stratergy){
        this.p_stratergy = p_stratergy;
    }
    public void checkout(int amount){
        p_stratergy.pay(amount);
    }
}

class StrategyPatternTest{
    public static void main(String[] args) {
        String creditCardNumber = "12345";
        PaymentStrategy credit = new CreditCardPayment(creditCardNumber);
        String payPalNumber = "12345";
        PaymentStrategy pal= new PayPalPayment(payPalNumber);
        PaymentContext context = new PaymentContext(credit);
        context.checkout(500);
        context = new PaymentContext(pal);
        context.checkout(1000);
    }
}