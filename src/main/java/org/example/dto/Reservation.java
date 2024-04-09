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
    public int time;
    public int dpt_id;

    public Reservation(int patient_id, String rh, int doctor_id, String name, int time, int dpt_id) {
        this.patient_id = patient_id;
        this.rh = rh;
        this.doctor_id = doctor_id;
        this.name = name;
        this.time = time;
        this.dpt_id = dpt_id;
    }

    public Reservation(Map<String, Object> row) {
        this.rh = (String) row.get("rh");
        Integer doctorIdObj = (Integer) row.get("doctor_id");
        this.doctor_id = (doctorIdObj != null) ? doctorIdObj.intValue() : 0;
        this.name = (String) row.get("name");
        this.time = (int) row.get("time");
        this.dpt_id = (int) row.get("dpt_id");
    }
}