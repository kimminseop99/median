package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Member extends Dto {
    public int age;
    public String phone;
    public String rrn;
    public double height;
    public double weight;
    public String ud;
    public String medicalHistory;
    public String loginId;
    public String loginPw;
    public int doctor_id;
    public String name;

    public Member(Map<String, Object> row) {
        super(row);
        this.age = (int) row.get("age");
        this.phone = (String) row.get("phone");
        this.rrn = (String) row.get("rrn");
        this.height = (double) row.get("height");
        this.weight = (double) row.get("weight");
        this.ud = (String) row.get("ud");
        this.medicalHistory = (String) row.get("medicalHistory");
        this.loginId = (String) row.get("loginId");
        this.loginPw = (String) row.get("loginPw");
        this.name = (String) row.get("name");
    }

    public Member(String loginId, String loginPw, int age, String phone, String rrn, double height, double weight, String ud,String medicalHistory, int doctor_id, String name) {
        this.age = age;
        this.phone = phone;
        this.rrn = rrn;
        this.height = height;
        this.weight = weight;
        this.ud = ud;
        this.medicalHistory = medicalHistory;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.doctor_id = doctor_id;
        this.name = name;
    }

    public Member(String loginId, String loginPw, int age, String phone, String rrn, double height, double weight, String ud,String name){
        this(loginId, loginPw, age, phone, rrn, height, weight, ud,"null",0, name);
    }
    public int getId() {
        return id;
    }
}