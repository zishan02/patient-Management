package com.patienthub.service;

import com.patienthub.exceptions.MobileNotExistException;
import com.patienthub.exceptions.PatientExistsException;
import com.patienthub.model.Patient;
import com.patienthub.model.PatientDetails;
import com.patienthub.respository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    PatientRepository patientRepository;

    public Patient addPatient(Patient patient){
        long mobile= patient.getMobile();
        List<PatientDetails> patientDetails=patient.getPatientDetailsList();
        String firstName=patientDetails.get(0).getFirstName();
        String secondName=patientDetails.get(0).getLastName();
        Optional<Patient> p=patientRepository.findById(mobile);
        if(!p.isEmpty()){
            List<PatientDetails> retrievedPatients = p.get().getPatientDetailsList();
            retrievedPatients.forEach(pat -> {
                if(pat.getFirstName().equalsIgnoreCase(firstName) && pat.getLastName().equalsIgnoreCase(secondName)){
                    throw  new PatientExistsException();
                }
            });
            p.get().addPatient(patient.getPatientDetailsList().get(0));
            patientRepository.save(p.get());
        }else {
            patientRepository.save(patient);
        }


        return patientRepository.findById(mobile).get();
    }

    public List<Patient> fetchPatientFromMobile(long mobile){
        Optional<Patient> p=patientRepository.findById(mobile);
        if(p.isEmpty()){
            throw new MobileNotExistException();
        }
        return p.stream().toList();
    }

    public Patient updatePatient(long mobile , int id , PatientDetails patient){
        Optional<Patient> p=patientRepository.findById(mobile);
        if(p.isEmpty()){
            throw new MobileNotExistException();
        }
        List<PatientDetails> pd=p.get().getPatientDetailsList().stream().filter(e ->e.getPatientId() == id).collect(Collectors.toList());
        pd.get(0).setFirstName(patient.getFirstName());
        pd.get(0).setLastName(patient.getLastName());
        patientRepository.save(p.get());
        return patientRepository.findById(mobile).get();
    }

    public Patient deletePatient(long mobile , int id ){
        Optional<Patient> p=patientRepository.findById(mobile);
        if(p.isEmpty()){
            throw new MobileNotExistException();
        }
        List<PatientDetails> pd=p.get().getPatientDetailsList().stream().filter(e ->e.getPatientId() != id).collect(Collectors.toList());

        if(p.get().getPatientDetailsList().size()==1){
            this.deleteCompleteRecord(mobile);
        }
        p.get().setPatientDetailsList(pd);

        patientRepository.save(p.get());
        return patientRepository.findById(mobile).get();
    }
    public void deleteCompleteRecord(long mobile){
        Optional<Patient> p=patientRepository.findById(mobile);
        if(p.isEmpty()){
            throw new MobileNotExistException();
        }
        patientRepository.deleteById(mobile);
    }

    public List<Patient> fetchAll(){
      return patientRepository.findAll();
    }
}
