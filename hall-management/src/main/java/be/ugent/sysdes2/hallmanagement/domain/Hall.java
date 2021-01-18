package be.ugent.sysdes2.hallmanagement.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Hall {

    @Id
    private int hallId;
    private int capacity;
    private int area;

    public Hall() {}

    public Hall(int hallId, int capacity, int area) {
        this.hallId = hallId;
        this.capacity = capacity;
        this.area = area;
    }

    public int getHallId() {
        return hallId;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getArea() {
        return area;
    }

}