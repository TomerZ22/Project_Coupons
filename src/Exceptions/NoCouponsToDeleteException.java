package Exceptions;

public class NoCouponsToDeleteException extends Exception{

    public NoCouponsToDeleteException() {
        super("There are no coupons related to your company's id");
    }
}
