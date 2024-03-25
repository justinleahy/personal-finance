package com.justinleahy.personalfinance;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonalFinanceController {

    @RequestMapping(path = "/greeting", method = RequestMethod.GET)
    public ResponseEntity<String> greeting() {
        return new ResponseEntity<>("{ \"response\":\"Hello, World!\" }", HttpStatus.OK);
    }

}
