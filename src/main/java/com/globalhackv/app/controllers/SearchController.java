package com.globalhackv.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
pubilic class SearchController {
    @RequestMapping("/citation")
    public String search(@RequestParam(value ="firstName", required=true) firstName ,
                         @RequestParam(value ="lastName", required=true) lastName ,
                         @RequestParam(value ="dateOfBirth", required=false) dateOfBirth ,
                         @RequestParam(value ="driversLiscense", required=false) driversLiscense ,
                         @RequestParam(value ="citationNumber", required=false) citationNumber ,
                         @RequestParam(value ="startDate", required=false) startDate ,
                         @RequestParam(value ="endDate", required=false) endDate ) {

        return "/";
    }
}