package be.ugent.sysdes2.badge.persistence;

import be.ugent.sysdes2.badge.domain.Badge;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends CrudRepository<Badge, Integer> {
}
