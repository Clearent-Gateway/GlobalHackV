import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
pubilic class SearchController {
    @RequestMapping("/citation")
    public String search(@RequestParam(value ="first_name", required=true) first_name ,
                         @RequestParam(value ="last_name", required=true) last_name ,
                         @RequestParam(value ="date_of_birth", required=false) date_of_birth ,
                         @RequestParam(value ="drivers_liscense", required=false) drivers_liscense ,
                         @RequestParam(value ="citation_number", required=false) citation_number ,
                         @RequestParam(value ="start_date", required=false) start_date ,
                         @RequestParam(value ="end_date", required=false) end_date ) {

        return "/";
    }
}