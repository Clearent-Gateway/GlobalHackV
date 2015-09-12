package  com.globalhackv.app.controllers;

import com.globalhackv.app.domain.Citation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/citation")
public class CitationController {



    @RequestMapping(method = RequestMethod.GET)
    public Citation searchCitation(@RequestParam(value ="firstName", required=true) String firstName ,
                         @RequestParam(value ="lastName", required=true) String lastName ,
                         @RequestParam(value ="dateOfBirth", required=false) String dateOfBirth ,
                         @RequestParam(value ="driversLicesnse", required=false) String driversLicesnse ,
                         @RequestParam(value ="citationNumber", required=false) String citationNumber ) {


        Citation citation = new Citation ();

          citation.setLastName(lastName);
          citation.setFirstName(firstName);
          citation.setDateOfBirth(dateOfBirth);
          citation.setDriversLiscense(driversLicesnse);
        if(citationNumber!=null) {
            citation.setCitationNumber(Long.valueOf(citationNumber));
        }
        //  List<Citation> citationResults = searchService.findByCitation(citation);


        return citation;
    }
}