package com.patienthub.controller;

import com.patienthub.model.Patient;
import com.patienthub.model.PatientDetails;
import com.patienthub.respository.PatientRepository;
import com.patienthub.service.PatientService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootApplication(scanBasePackages={
        "com.patienthub"})
@SpringBootTest(classes={PatientController.class , PatientService.class})
public class PatientControllerTests {




    @Autowired
    PatientService patientService;

    @Mock
    PatientRepository patientRepository;


    @Test
    void AddPatientTest() {
        PatientController pc=new PatientController();
        Patient p=new Patient();
        PatientDetails pd=new PatientDetails();
        pd.setPatientId(1);
        pd.setFirstName("Test1");
        pd.setLastName("Test2");
        long val=989475098;
        p.setMobile(val);
        p.setPatientDetailsList(Arrays.asList(pd));
        Patient returnVal=patientService.addPatient(p);
        Assert.isInstanceOf(Patient.class,returnVal);
    }


    @Test
    void GetAllPatient() {
        List<Patient> patient=patientService.fetchAll();
        Assertions.assertEquals(1,patient.size());
    }
}
