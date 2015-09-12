package  com.globalhackv.app.controllers;

import com.globalhackv.app.domain.Citation;
import com.globalhackv.app.domain.CitationViolationResponse;
import com.globalhackv.app.domain.Violation;
import com.globalhackv.app.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/citation")
public class CitationController {

    @Autowired
    public SearchService searchService;

    @RequestMapping(method = RequestMethod.GET)
    public List<CitationViolationResponse> searchCitation(@RequestParam(value ="firstName", required=true) String firstName ,
                         @RequestParam(value ="lastName", required=true) String lastName ,
                         @RequestParam(value ="dateOfBirth", required=false) String dateOfBirth ,
                         @RequestParam(value ="driversLicesnse", required=false) String driversLicesnse ,
                         @RequestParam(value ="citationNumber", required=false) Long citationNumber ) {


         List<Citation> citations = new ArrayList<Citation>();

        Citation citation = new Citation ();

          citation.setLastName(lastName);
          citation.setFirstName(firstName);
          citation.setDateOfBirth(dateOfBirth);
          citation.setDriversLicense(driversLicesnse);

         if(citationNumber!=null) {
             citation.setCitationNumber(citationNumber);
         }

        citations.add(citation);
        List<Citation> citationResults = searchService.findByCitation(citation);

         List<CitationViolationResponse> citationViolations = new ArrayList<CitationViolationResponse>();


        for(Citation cit : citationResults) {
            CitationViolationResponse citationViolationResponse = new CitationViolationResponse();
            List<Violation> violations = searchService.findViolation(cit.getCitationNumber());
            BigDecimal currentTotal = citationViolationResponse.getTotalAmount();
             for(Violation violation: violations){



                     if(violation.getFineAmount()!=null){
                       currentTotal=    currentTotal.add(violation.getFineAmount());
                     }
                     if(violation.getCourtCosts() !=null){
                         currentTotal=   currentTotal.add(violation.getCourtCosts());
                     }

                     citationViolationResponse.setTotalAmount(currentTotal);


             }
            citationViolationResponse.setCitation(cit);
            citationViolationResponse.setViolations(violations);
            citationViolations.add(citationViolationResponse);
        }

        return citationViolations;
    }
}