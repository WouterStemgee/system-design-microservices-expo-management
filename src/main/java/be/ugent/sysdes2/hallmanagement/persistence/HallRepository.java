package be.ugent.sysdes2.hallmanagement.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.sysdes2.hallmanagement.domain.Hall;

@Repository
public interface HallRepository extends CrudRepository<Hall, Integer>  {
    public Hall findByHallId(int hallId);
}