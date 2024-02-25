package af.gov.mcitp.gatewayservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/test")
    
    public String index(){
        return "test value";
    }
    
}
