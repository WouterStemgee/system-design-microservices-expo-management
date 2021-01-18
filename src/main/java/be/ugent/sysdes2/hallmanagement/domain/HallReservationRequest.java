package be.ugent.sysdes2.hallmanagement.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

public class HallReservationRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private List<Integer> halls;

    public HallReservationRequest(LocalDate startDate, LocalDate endDate, List<Integer> hallIds) {
        this.halls = hallIds;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public List<Integer> getHallIds() {
        return halls;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<HallReservation> getHallReservations() {
        List<HallReservation> hs = new ArrayList<>();
        for(int id : halls) {
            hs.add(new HallReservation(id,startDate,endDate));
        }

        return hs;
    }
}