package be.ugent.sysdes2.cloakroom.persistence;

public class ItemDoesNotExistsException extends Exception {
    public ItemDoesNotExistsException(String message) {
        super(message);
    }

    public ItemDoesNotExistsException() {
        super();
    }
}