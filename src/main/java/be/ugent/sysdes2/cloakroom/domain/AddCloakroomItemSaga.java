package be.ugent.sysdes2.cloakroom.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.ugent.sysdes2.cloakroom.persistence.CloakroomItemRepository;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomSpaceRepository;

@Service
public class AddCloakroomItemSaga {
    private static Logger logger = LoggerFactory.getLogger(AddCloakroomItemSaga.class);

    private final List<CloakroomListener> listeners;

    @Autowired
    CloakroomItemRepository cloakroomItemRepository;

    @Autowired
    CloakroomSpaceRepository cloakroomSpaceRepository;

	public AddCloakroomItemSaga(CloakroomItemRepository cloakroomItemRepository, CloakroomSpaceRepository cloakroomSpaceRepository) {
        this.cloakroomItemRepository = cloakroomItemRepository;
        this.cloakroomSpaceRepository = cloakroomSpaceRepository;
		this.listeners = new ArrayList<CloakroomListener>();
	}

    public void registerListener(CloakroomListener listener) {
		this.listeners.add(listener);
    }
    
    public void startAddCloakroomItemSaga(CloakroomItem cloakroomItem) {
        logger.info("AddCloakroomItemSaga started");
        //check if cloakroom is not full
        int availableSpaces = cloakroomSpaceRepository.getAvailableSpace();
        if(availableSpaces > 0) {
            //request a balance update
            float price = cloakroomSpaceRepository.getPricePerSpace();
            // TODO: api call to update balance
        } else {
            logger.info("Cloakroom has no more space: {}/{}. ItemId {}, BadgeId failed to add", cloakroomSpaceRepository.getAvailableSpace(), cloakroomSpaceRepository.getTotalSpaces(), cloakroomItem.getItemId(), cloakroomItem.getBadgeId());
            this.listeners.forEach(l -> l.onItemAddFail(cloakroomItem, CloakroomReason.NO_MORE_SPACE));
        }
    }

    public void onBalanceUpdate(CloakroomItem cloakroomItem) {
        // add the item in the db
        logger.info("ItemId {}, BadgeId {} added", cloakroomItem.getItemId(), cloakroomItem.getBadgeId());
        cloakroomItemRepository.save(cloakroomItem);
        this.listeners.forEach(l -> l.onItemAddSuccess(cloakroomItem));
    }

    public void onBalanceUpdateFail(CloakroomItem cloakroomItem) {
        logger.info("ItemId {}, BadgeId {} failed to add. INSUFFICIENT_BALANCE", cloakroomItem.getItemId(), cloakroomItem.getBadgeId());
        this.listeners.forEach(l -> l.onItemAddFail(cloakroomItem, CloakroomReason.INSUFFICIENT_BALANCE));
    }

}