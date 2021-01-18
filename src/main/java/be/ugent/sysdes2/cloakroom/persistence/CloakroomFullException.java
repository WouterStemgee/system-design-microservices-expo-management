package be.ugent.sysdes2.cloakroom.persistence;

public class CloakroomFullException extends Exception {
    public CloakroomFullException(String message) {
        super(message);
    }
}