package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Doctor extends Dto{
    public String name;
    public int dpt_id;
    public String loginPw;

    public Doctor(Map<String, Object> row) {
        super(row);
        this.name = (String) row.get("name");
        this.dpt_id = (int) row.get("dpt_id");
        this.loginPw = (String) row.get("loginPw");

    }




}
