package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class Reservation extends Dto {
    public int patient_id;
    public String rh;
    public int doctor_id;
    public String name;

    public Reservation(int patient_id, String rh, int doctor_id, String name) {
        this.patient_id = patient_id;
        this.rh = rh;
        this.doctor_id = doctor_id;
        this.name = name;
    }

    public Reservation(Map<String, Object> row) {
        this.patient_id = (int) row.get("patient_id");
        this.rh = (String) row.get("rh");
        this.doctor_id = (int) row.get("doctor_id");
        this.name = (String) row.get("name");
    }
}