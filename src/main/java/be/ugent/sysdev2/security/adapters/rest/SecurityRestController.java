package be.ugent.sysdev2.security.adapters.rest;

import be.ugent.sysdev2.security.adapters.messaging.MessageGateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.web.bind.annotation.*;
import be.ugent.sysdev2.security.domain.Emergency;


@RestController
@RequestMapping("/reception")
public class SecurityRestController {
    private MessageGateway mg;
    private SecurityRestController(MessageGateway mg){
        this.mg = mg;
    }
    @PostMapping("/emergency")
    public String sendEmergency(@RequestBody Emergency emergency){
        mg.resolveEmergency(emergency);
        return "Emergency send!";
    }
}
