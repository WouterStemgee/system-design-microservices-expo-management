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
    private final CloakroomItemRepository cloakroomItemRepository;

    @Autowired
    private final CloakroomSpaceRepository cloakroomSpaceRepository;

    @Autowired
    private NextSequenceService nextSequenceService;

	public AddCloakroomItemSaga(CloakroomItemRepository cloakroomItemRepository, CloakroomSpaceRepository cloakroomSpaceRepository, NextSequenceService nextSequenceService) {
        this.cloakroomItemRepository = cloakroomItemRepository;
        this.cloakroomSpaceRepository = cloakroomSpaceRepository;
        this.listeners = new ArrayList<CloakroomListener>();
        this.nextSequenceService = nextSequenceService;
	}

    public void registerListener(CloakroomListener listener) {
		this.listeners.add(listener);
    }
    
    public void startAddCloakroomItemSaga(CloakroomItem cloakroomItem) {
        logger.info("AddCloakroomItemSaga started");
        //check if cloakroom is not full
        CloakroomSpace cs = cloakroomSpaceRepository.getCloakroomSpace();
        if(cs.getAvailableSpaces() > 0) {
            //check if item does not exist
            //if(!cloakroomItemExists(cloakroomItem)) {
                 //update available space
                cs.decreaseAvailableSpaces();
                cloakroomSpaceRepository.save(cs);

                //request a balance update
                float price = cs.getPricePerSpace();
                // TODO: api call to update balance. What if api call failed or timeout?
                this.onBalanceUpdate(cloakroomItem);
            // } else {
            //     logger.info("Item already exists: {}/{}. ItemId {}, BadgeId failed to add", cs.getAvailableSpaces(), cs.getTotalSpaces(), cloakroomItem.getItemId(), cloakroomItem.getBadgeId());
            //     this.listeners.forEach(l -> l.onItemAddFail(cloakroomItem, CloakroomReason.ITEM_ALREADY_STORED));
            // }
        } else {
            logger.info("Cloakroom has no more space: {}/{}. ItemId {}, BadgeId {} failed to add", cs.getAvailableSpaces(), cs.getTotalSpaces(), cloakroomItem.getItemId(), cloakroomItem.getBadgeId());
            this.listeners.forEach(l -> l.onItemAddFail(cloakroomItem, CloakroomReason.NO_MORE_SPACE));
        }
    }

    private Boolean cloakroomItemExists(CloakroomItem cloakroomItem) {
        return cloakroomItemRepository.findByItemId(cloakroomItem.getItemId()) != null;
    }

    public void onBalanceUpdate(CloakroomItem cloakroomItem) {
        // add the item in the db
        cloakroomItem.setItemId(nextSequenceService.getNextSequence("customSequences"));
        logger.info("ItemId {}, BadgeId {} added", cloakroomItem.getItemId(), cloakroomItem.getBadgeId());
        cloakroomItemRepository.save(cloakroomItem);
        this.listeners.forEach(l -> l.onItemAddSuccess(cloakroomItem));
    }

    public void onBalanceUpdateFail(CloakroomItem cloakroomItem) {
        logger.info("ItemId {}, BadgeId {} failed to add. INSUFFICIENT_BALANCE", cloakroomItem.getItemId(), cloakroomItem.getBadgeId());

        //make space available again
        CloakroomSpace cs = cloakroomSpaceRepository.getCloakroomSpace();
        cs.increaseAvailableSpaces();
        cloakroomSpaceRepository.save(cs);

        this.listeners.forEach(l -> l.onItemAddFail(cloakroomItem, CloakroomReason.INSUFFICIENT_BALANCE));
    }

}