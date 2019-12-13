package be.ugent.sysdes2.cloakroom.adapters.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import be.ugent.sysdes2.cloakroom.domain.CloakroomItem;
import be.ugent.sysdes2.cloakroom.domain.CloakroomListener;
import be.ugent.sysdes2.cloakroom.domain.CloakroomReason;
import be.ugent.sysdes2.cloakroom.domain.CloakroomService;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomItemRepository;
import be.ugent.sysdes2.cloakroom.persistence.CloakroomSpaceRepository;

@RestController
@RequestMapping("/cloakroom")
public class CloakroomRestController implements CloakroomListener {
    private CloakroomItemRepository cloakroomItemRepository;
    private CloakroomSpaceRepository cloakroomSpaceRepository;
    private CloakroomService cloakroomService;

    private final Map<Integer, DeferredResult<CloakroomItem>> deferredResults;

    @Autowired
    private CloakroomRestController(CloakroomSpaceRepository cloakroomSpaceRepository,
            CloakroomItemRepository cloakroomItemRepository, CloakroomService cloakroomService) {
        this.cloakroomItemRepository = cloakroomItemRepository;
        this.cloakroomSpaceRepository = cloakroomSpaceRepository;
        this.cloakroomService = cloakroomService;
        this.deferredResults = new HashMap<>(10);
    }

    @PostConstruct
	private void registerListener() {
		cloakroomService.registerCheckInListener(this);
    }
    
    @GetMapping("/get_cloakroom_items")
    public Iterable<CloakroomItem> getAllCloakroomItems(){
		return this.cloakroomItemRepository.findAll();
	}

    @GetMapping("/get_cloakroom_items/{id}")
    public Iterable<CloakroomItem> getCloakroomItemByBadgeId(@PathVariable("id") String id) {
        try {
            int badgeId = Integer.parseInt(id);
            return this.cloakroomItemRepository.findByBadgeId(badgeId);
        }
        catch(NumberFormatException e) {
            return new ArrayList<>();
        }
        
	}

    @DeleteMapping("/remove_cloakroom_item/{id}")
    public String removeCloakroomItem(@PathVariable("id") String id) {
        try {
            int itemId = Integer.parseInt(id);
            if(cloakroomItemRepository.findByItemId(itemId) != null) {
                cloakroomService.removeCloakroomItem(id);
                return "success";
            } else {
                return "No item found";
            }         
        }
        catch(NumberFormatException e) {
            return "No item found";
        }
        
	}

    @GetMapping("/add_cloakroom_item")
    public DeferredResult<CloakroomItem> handleAddCloakroomItem(@RequestParam("itemId") int itemId,
            @RequestParam("badgeId") int badgeId) {
        System.out.println("Received add item REQUEST");
        DeferredResult<CloakroomItem> deferredResult = new DeferredResult<>(10000l);

        deferredResult.onTimeout(() -> {
            deferredResult.setErrorResult("Request timedout occurred");
        });

        this.deferredResults.put(itemId, deferredResult);

        System.out.println("Calling service...");
        this.cloakroomService.addCloakroomItem(new CloakroomItem(itemId, badgeId));

        return deferredResult;
    }

    private void performResponse(CloakroomItem response) {
        DeferredResult<CloakroomItem> deferredResult = this.deferredResults.remove(response.getItemId());
        if (deferredResult != null && !deferredResult.isSetOrExpired()) {
            System.out.println("Setting result");
            deferredResult.setResult(response);
        } else {
            System.out.println("defereredResult: " + deferredResult);
        }
    }

    private void performFailResponse(CloakroomItem response, CloakroomReason cloakroomReason) {
        DeferredResult<CloakroomItem> deferredResult = this.deferredResults.remove(response.getItemId());
        if (deferredResult != null && !deferredResult.isSetOrExpired()) {
            System.out.println("Setting fail result");
            if(cloakroomReason == CloakroomReason.INSUFFICIENT_BALANCE) {
                deferredResult.setErrorResult("Insufficient Balance");
            } else if(cloakroomReason == CloakroomReason.ITEM_ALREADY_STORED) {
                deferredResult.setErrorResult("Item already exists");
            } else {
                deferredResult.setErrorResult("No available spaces");
            }
            
        } else {
            System.out.println("defereredResult: " + deferredResult);
        }
    }

    @Override
    public void onItemAddSuccess(CloakroomItem cloakroomItem) {
        this.performResponse(cloakroomItem);

    }

    @Override
    public void onItemAddFail(CloakroomItem cloakroomItem, CloakroomReason cloakroomReason) {
        this.performFailResponse(cloakroomItem,cloakroomReason);
    }
}