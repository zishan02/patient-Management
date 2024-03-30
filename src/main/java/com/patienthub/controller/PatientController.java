package com.patienthub.controller;


import com.patienthub.model.Patient;
import com.patienthub.model.PatientDetails;
import com.patienthub.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
    @Autowired
    PatientService patientService;

    @PostMapping("/patient")
    public Patient AddPatient(@RequestBody Patient patient){
       return patientService.addPatient(patient);
    }

    @GetMapping("/patients")
    public List<Patient> getAllPatient(){
       return patientService.fetchAll();
    }

    @GetMapping("/patient/{mobile}")
    public List<Patient> fetchPatient(@PathVariable long mobile){
        return patientService.fetchPatientFromMobile(mobile);
    }

    @PutMapping("/patient/{mobile}/{id}")
    public Patient UpdatePatient(@PathVariable long mobile , @PathVariable int id ,@RequestBody PatientDetails patient){
        return patientService.updatePatient(mobile,id,patient);
    }
    @DeleteMapping("/patient/{mobile}/{id}")
    public Patient DeletePatient(@PathVariable long mobile , @PathVariable int id ){
            return patientService.deletePatient(mobile,id);
    }

    @DeleteMapping("/patient/{mobile}")
    public void DeleteMobileRegistration(@PathVariable long mobile ){
         patientService.deleteCompleteRecord(mobile);
    }
}
