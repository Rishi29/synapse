package com.healthbyte.controller;

import com.healthbyte.exception.VisitorNotFoundException;
import com.healthbyte.model.Visitor;
import com.healthbyte.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/healthbyte")
public class VisitorController {

    @Autowired
    private VisitorService visitorService;
    private final Logger logger = LoggerFactory.getLogger(VisitorController.class);

    @GetMapping("/hello")
    public String hello(){
        return "Hi Rishi";
    }

    @GetMapping("/v1/visitors/{id}")
    public ResponseEntity<Visitor> findVisitorbyId(@PathVariable Integer id){
        Visitor visitor = null;
        try {
            visitor = visitorService.getVisitorById(id);
            return new ResponseEntity<Visitor>(visitor, HttpStatus.OK);
        } catch (VisitorNotFoundException e) {
            e.printStackTrace();
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/v1/visitors")
    public List<Visitor> findAllVisitor(){

        return visitorService.findAllVisitor();

    }

    @PostMapping("/v1/visitors")
    public ResponseEntity<Visitor> saveVisitor(@RequestBody Visitor visitor){
        System.out.println("Hi you are in service");
        return new ResponseEntity<Visitor> (visitorService.saveVisitor(visitor), HttpStatus.OK) ;
    }

    @DeleteMapping("/v1/visitors/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteVisitor(@PathVariable Integer id){
        Map<String, Boolean> response = new HashMap<>();
    try {
        response = (Map<String, Boolean>) visitorService.deleteVisitor(id);
    }catch(VisitorNotFoundException ex){
        logger.info(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<Map<String,Boolean>>(response, HttpStatus.OK);
    }

    @PutMapping("/v1/visitors/{id}")
    public ResponseEntity<Visitor> updateVistor(@PathVariable Integer id, @RequestBody Visitor visitorDetails){
        try{
            Visitor updatedVisitor = visitorService.updateVisitor(id, visitorDetails);
            return new ResponseEntity<>(updatedVisitor,HttpStatus.OK);
        }catch(VisitorNotFoundException ex){
            logger.info("Exception occurred "+ ex.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void visitorNotFoundHandler(VisitorNotFoundException ex){

    }



}
