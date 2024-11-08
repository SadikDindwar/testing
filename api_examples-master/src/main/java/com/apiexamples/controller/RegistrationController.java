package com.apiexamples.controller;


import com.apiexamples.entity.Registration;
import com.apiexamples.payload.RegDto;
import com.apiexamples.payload.RegistrationDto;
import com.apiexamples.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration") //v1 because it is version 1
public class RegistrationController {
    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //http://localhost:8080/api/v1/registration


    @PostMapping
    public ResponseEntity<?> addRegistration( @Valid @RequestBody RegistrationDto registrationDto,
                                                            BindingResult result
                                                            ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage() , HttpStatus.OK);
        }
       RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        
        return new ResponseEntity<>(regDto , HttpStatus.CREATED);
    }

    //http://localhost:8080/api/v1/registration?id=
    @DeleteMapping
    public ResponseEntity<String> deleteRegistrationById(@RequestParam long id){
        registrationService.deleteRegistrationById(id);
        return new ResponseEntity<>("Registration Deleted" , HttpStatus.OK );
    }

    //http://localhost:8080/api/v1/registration?id=
    @PutMapping
    public ResponseEntity<?> updateRegistration(
            @RequestParam long id ,
           @Valid @RequestBody RegistrationDto registrationDto,
            BindingResult result
            ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage() , HttpStatus.OK);
        }
       RegistrationDto dto= registrationService.updateRegistration(id , registrationDto);
        return new ResponseEntity<>(dto , HttpStatus.OK);

    }


    // fetching all registrations and implementing pagination and sorting in ascending or descending order based on different columns.

    //http://localhost:8080/api/v1/registration?pageNo=0&pageSize=5&sortBy=email&sortDir=asc
    @GetMapping
    public ResponseEntity<RegDto> getAllRegistrations(
            @RequestParam(name="pageNo" , defaultValue = "0" , required = false) int pageNo,
            @RequestParam(name="pageSize" , defaultValue = "5" , required = false) int pageSize,
            @RequestParam(name="sortBy" , defaultValue = "name" , required = false) String sortBy,
            @RequestParam(name="sortDir" , defaultValue = "asc" , required = false) String sortDir
    ){
        RegDto dtos=registrationService.getAllRegistrations(pageNo, pageSize , sortBy, sortDir);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/byid")
    public ResponseEntity<RegistrationDto> getRegistrationById(@RequestParam long id){
       RegistrationDto dto= registrationService.getRegistrationById(id);
       return new ResponseEntity<>(dto , HttpStatus.OK);
    }


}
