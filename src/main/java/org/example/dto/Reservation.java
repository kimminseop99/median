package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Reservation extends Dto {
    public int patient_id;
    public String rh;
    public int doctor_id;

    public Reservation(Map<String, Object> row) {
        super(row);
        this.patient_id = (int) row.get("patient_id");
        this.rh = (String) row.get("rh");
        this.doctor_id = (int) row.get("doctor_id");
    }
}