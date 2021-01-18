package be.ugent.sysdes2.tracking.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import be.ugent.sysdes2.tracking.domain.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Integer> {
    public List<Task> findByEventId(String eventId);
}