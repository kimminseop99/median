package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Map;

@Getter
@Setter

public class Reservation extends Dto {
    public int patient_id;
    public String rh;
    public int doctor_id;
    public String name;
    public int dpt_id;
    public Time doctor_time;

    public Reservation(int patient_id, String rh, int doctor_id, String name, int dpt_id, Time doctor_time) {
        this.patient_id = patient_id;
        this.rh = rh;
        this.doctor_id = doctor_id;
        this.name = name;
        this.dpt_id = dpt_id;
        this.doctor_time = doctor_time;
    }

    public Reservation(Map<String, Object> row) {
        super(row);

        Integer patientIdObj = (Integer) row.get("patient_id");
        this.patient_id = (patientIdObj != null) ? patientIdObj.intValue() : 0;


        this.rh = (String) row.get("rh");

        Integer doctorIdObj = (Integer) row.get("doctor_id");
        this.doctor_id = (doctorIdObj != null) ? doctorIdObj.intValue() : 0;

        this.name = (String) row.get("name");

        Integer dptIdObj = (Integer) row.get("dpt_id");
        this.dpt_id = (dptIdObj != null) ? dptIdObj.intValue() : 0;

        this.doctor_time = (Time) row.get("doctor_time");
    }





}