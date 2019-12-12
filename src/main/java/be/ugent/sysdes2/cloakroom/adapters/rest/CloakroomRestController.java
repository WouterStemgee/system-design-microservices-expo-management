package be.ugent.sysdes2.cloakroom.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import be.ugent.sysdes2.cloakroom.persistence.CloakroomItemRepository;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomSpaceRepository;

@RestController
@RequestMapping("/cloakroom")
public class CloakroomRestController {
    private CloakroomItemRepository cloakroomItemRepository;
    private CloakroomSpaceRepository cloakroomSpaceRepository;

    @Autowired
    private CloakroomRestController(CloakroomSpaceRepository cloakroomSpaceRepository, CloakroomItemRepository cloakroomItemRepository) {
        this.cloakroomItemRepository = cloakroomItemRepository;
        this.cloakroomSpaceRepository = cloakroomSpaceRepository;
    }

    
}