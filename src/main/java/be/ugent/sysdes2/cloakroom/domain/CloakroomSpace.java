package be.ugent.sysdes2.cloakroom.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CloakroomSpace {

    @Id
    private final int _id = 1;
    private int availableSpaces;
    private int totalSpaces;
    private float pricePerSpace;

    private CloakroomSpace() {
    }

    public CloakroomSpace(int availableSpaces, int totalSpaces, float pricePerSpace) {
        this.availableSpaces = availableSpaces;
        this.totalSpaces = totalSpaces;
        this.pricePerSpace = pricePerSpace;
    }

    public int getAvailableSpaces() {
        return availableSpaces;
    }

    public int getTotalSpaces() {
        return totalSpaces;
    }

    public float getPricePerSpace() {
        return pricePerSpace;
    }

}
