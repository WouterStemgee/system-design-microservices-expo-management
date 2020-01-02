package be.ugent.sysdes2.cloakroom.persistence;

public class ItemAlreadyExistsException extends Exception {
    public ItemAlreadyExistsException(String message) {
        super(message);
    }
}