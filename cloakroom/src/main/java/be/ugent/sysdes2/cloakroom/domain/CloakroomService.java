package be.ugent.sysdes2.cloakroom.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.sysdes2.cloakroom.persistence.CloakroomItemRepository;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomSpaceRepository;

@Service
public class CloakroomService {

    @Autowired
    private final CloakroomItemRepository cloakroomItemRepository;

    @Autowired
    private final CloakroomSpaceRepository cloakroomSpaceRepository;
    private final AddCloakroomItemSaga addCloakroomItemSaga;

	public CloakroomService(CloakroomItemRepository cloakroomItemRepository, CloakroomSpaceRepository cloakroomSpaceRepository, AddCloakroomItemSaga saga) {
        this.cloakroomItemRepository = cloakroomItemRepository;
        this.cloakroomSpaceRepository = cloakroomSpaceRepository;
        this.addCloakroomItemSaga = saga;
	}

    public void registerCheckInListener(CloakroomListener listener) {
		this.addCloakroomItemSaga.registerListener(listener);
	}

    public void addCloakroomItem(CloakroomItem item) {
        synchronized (this.addCloakroomItemSaga) {
            addCloakroomItemSaga.startAddCloakroomItemSaga(item);
        }
    }

    public synchronized void removeCloakroomItem(int id) {
        //remove item & update availibility counter
        cloakroomItemRepository.deleteByItemId(id);
        CloakroomSpace cs = cloakroomSpaceRepository.getCloakroomSpace();
        cs.increaseAvailableSpaces();
        cloakroomSpaceRepository.save(cs);
    }
}