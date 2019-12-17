package be.ugent.sysdes2.ticket.adapters.rest;

import be.ugent.sysdes2.ticket.domain.Detail;
import be.ugent.sysdes2.ticket.persistence.DetailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("ticket")
public class DetailRestController {

    private final DetailRepository detailRepository;

    private static Logger logger = LoggerFactory.getLogger(DetailRestController.class);

    @Autowired
    public DetailRestController(DetailRepository detailRepository) {
        this.detailRepository = detailRepository;
    }

    @GetMapping("{eventId}/availability")
    public ResponseEntity<?> getAvailability(@PathVariable("eventId") int eventId) {
        logger.info("Get event availability with id " + eventId);
        Detail detail = this.detailRepository.findById(eventId).orElse(null);
        if(detail != null){
            return new ResponseEntity<>(detail, HttpStatus.OK);
        }
        return new ResponseEntity<>("Event with id " + eventId + " does not exist.", HttpStatus.NOT_FOUND);    }
}
