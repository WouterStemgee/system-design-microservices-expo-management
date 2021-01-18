package be.ugent.sysdes2.hallmanagement.domain;

public class DateNotAvailableException extends RuntimeException {
    public DateNotAvailableException(String message) {
        super(message);
    }
}