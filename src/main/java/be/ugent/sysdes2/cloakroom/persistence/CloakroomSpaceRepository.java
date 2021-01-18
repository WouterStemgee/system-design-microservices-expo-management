package be.ugent.sysdes2.cloakroom.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import be.ugent.sysdes2.cloakroom.domain.CloakroomSpace;


public interface CloakroomSpaceRepository extends MongoRepository<CloakroomSpace, String> {

    @Query(value="{ '_id' : 1 }")
    public CloakroomSpace getCloakroomSpace();

    // @Query(value="{ '_id' : 1 }", fields="{ 'availableSpace' : 1}")
    // public int getAvailableSpace();

    // @Query(value="{ '_id' : 1 }", fields="{ 'totalSpaces' : 1}")
    // public int getTotalSpaces();

    // @Query(value="{ '_id' : 1 }", fields="{ 'pricePerSpace' : 1}")
    // public float getPricePerSpace();

}