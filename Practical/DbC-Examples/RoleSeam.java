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
        System.out.println("UPI payment: " + amount);
    }
}

class CardPayment implements PaymentMethod {
    @Override
    public void pay(Money amount) {
        System.out.println("Card payment: " + amount);
    }
}

class BookingService {
    private final PaymentMethod paymentMethod;

    public BookingService(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void confirmBooking(Money amount) {
        paymentMethod.pay(amount);
        System.out.println("Booking confirmed");
    }
}

public class RoleSeam {
    public static void main(String[] args) {
        PaymentMethod payment = new UPIPayment();
        BookingService service = new BookingService(payment);
        service.confirmBooking(new Money(10000, "INR"));
    }
}