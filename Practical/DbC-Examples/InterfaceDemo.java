interface PaymentMethod {
    void pay(Money amount);
}

class Money {
    private double amount;
    private String currency;

    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return amount + " " + currency;
    }
}

class UPIPayment implements PaymentMethod {
    @Override
    public void pay(Money amount) {
        System.out.println("Paid using UPI: " + amount);
    }
}

class CardPayment implements PaymentMethod {
    @Override
    public void pay(Money amount) {
        System.out.println("Paid using Card: " + amount);
    }
}

public class InterfaceDemo {
    public static void main(String[] args) {
        PaymentMethod payment = new UPIPayment();
        payment.pay(new Money(5000, "INR"));

        payment = new CardPayment();
        payment.pay(new Money(5000, "INR"));
    }
}