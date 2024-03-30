package com.patienthub.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Patient {
    @OneToMany(cascade = CascadeType.ALL)
    private List<PatientDetails> patientDetailsList;
    @Id
    private long mobile;

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    public List<PatientDetails> getPatientDetailsList() {
        return patientDetailsList;
    }

    public void setPatientDetailsList(List<PatientDetails> patientDetailsList) {
        this.patientDetailsList = patientDetailsList;
    }
    public void addPatient(PatientDetails pd) {
            if(patientDetailsList!=null){
                patientDetailsList.add(pd);
            }
    }
}
