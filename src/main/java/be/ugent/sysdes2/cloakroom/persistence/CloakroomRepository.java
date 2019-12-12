package be.ugent.sysdes2.cloakroom.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import be.ugent.sysdes2.cloakroom.domain.CloakroomItem;


public interface CloakroomRepository extends MongoRepository<CloakroomItem, String> {

  public List<CloakroomItem> findByBadgeId(int BadgeId);
}