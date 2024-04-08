package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class Doctor {
    public String name;
    public int dpt_id;
    public String loginPw;

    public Doctor(String name, int dpt_id, String loginPw) {
        this.name = name;
        this.dpt_id = dpt_id;
        this.loginPw = loginPw;
    }

}