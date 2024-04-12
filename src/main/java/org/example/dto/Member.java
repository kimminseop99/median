package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter

public class Member extends Dto {
    public String name;
    public int age;
    public String phone;
    public String rrn;
    public double height;
    public double weight;
    public String ud;
    public String loginId;
    public String loginPw;



    public Member(Map<String, Object> row) {
        super(row);
        this.name = (String) row.get("name");
        this.age = (int) row.get("age");
        this.phone = (String) row.get("phone");
        this.rrn = (String) row.get("rrn");
        this.height = ((BigDecimal)row.get("height")).doubleValue();
        this.weight = ((BigDecimal)row.get("weight")).doubleValue();
        this.ud = (String) row.get("ud");
        this.loginId = (String) row.get("loginId");
        this.loginPw = (String) row.get("loginPw");

    }

    public Member(String name, int age, String phone, String rrn, double height, double weight, String ud, String loginId, String loginPw) {
        this.name = name;
        this.age = age;
        this.phone = phone;
        this.rrn = rrn;
        this.height = height;
        this.weight = weight;
        this.ud = ud;
        this.loginId = loginId;
        this.loginPw = loginPw;
    }

}