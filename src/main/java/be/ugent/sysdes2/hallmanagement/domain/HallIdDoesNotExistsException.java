package be.ugent.sysdes2.hallmanagement.domain;

public class HallIdDoesNotExistsException extends RuntimeException {
    public HallIdDoesNotExistsException(String message) {
        super(message);
    }
}