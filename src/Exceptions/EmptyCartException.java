package Exceptions;

public class EmptyCartException extends Exception {
    public EmptyCartException(){
        super("You're shopping cart is empty, it's time for some coupon shopping!");
    }
}
