package be.ugent.sysdes2.cloakroom.persistence;

public class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}