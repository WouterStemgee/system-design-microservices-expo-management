package be.ugent.sysdes2.cloakroom.persistence;

import org.springframework.beans.factory.annotation.Autowired;

import be.ugent.sysdes2.cloakroom.domain.CloakroomItem;

public class CloakroomRepository {
    @Autowired
    CloakroomItemRepository cloakroomItemRepository;

    @Autowired
    CloakroomSpaceRepository cloakroomSpaceRepository;

    public CloakroomRepository(CloakroomItemRepository cloakroomItemRepository, CloakroomSpaceRepository cloakroomSpaceRepository) {
        this.cloakroomItemRepository = cloakroomItemRepository;
        this.cloakroomSpaceRepository = cloakroomSpaceRepository;
    }
}

