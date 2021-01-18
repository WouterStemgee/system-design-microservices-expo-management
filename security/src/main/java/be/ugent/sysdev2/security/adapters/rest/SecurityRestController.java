package be.ugent.sysdev2.security.adapters.rest;

import be.ugent.sysdev2.security.adapters.messaging.MessageGateway;
import be.ugent.sysdev2.security.domain.type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.web.bind.annotation.*;
import be.ugent.sysdev2.security.domain.Emergency;

@CrossOrigin
@RestController
@RequestMapping("security")
public class SecurityRestController {
    private MessageGateway mg;

    @Autowired
    public SecurityRestController(MessageGateway mg){
        this.mg = mg;
    }

    @PostMapping("/emergency")
    public String sendEmergency(@RequestParam("type") type emergencytype,@RequestParam("severity") int severity,@RequestParam("source") String source ){
        Emergency emergency = new Emergency(emergencytype,severity,source);
        mg.resolveEmergency(emergency);
        return "Emergency send!";
    }


}
