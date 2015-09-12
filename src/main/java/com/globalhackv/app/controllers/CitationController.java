package  com.globalhackv.app.controllers;

import com.globalhackv.app.domain.Citation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/citation")
public class CitationController {

    public String searchCitation(@RequestParam(value ="firstName", required=true) String firstName ,
                         @RequestParam(value ="lastName", required=true) String lastName ,
                         @RequestParam(value ="dateOfBirth", required=false) String dateOfBirth ,
                         @RequestParam(value ="driversLicesnse", required=false) String driversLicesnse ,
                         @RequestParam(value ="citationNumber", required=false) String citationNumber ,
                         @RequestParam(value ="startDate", required=false) String startDate ,
                         @RequestParam(value ="endDate", required=false) String endDate ) {


        Citation citation = new Citation ();

          citation.setLastName(lastName);
          citation.setFirstName(firstName);
        return "/";
    }
}