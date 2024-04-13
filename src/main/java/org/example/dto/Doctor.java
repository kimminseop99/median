package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class Doctor extends Dto{
    public int id;
    public String name;
    public int dpt_id;
    public String loginPw;

    public Doctor(Map<String, Object> row) {
        super(row);
        this.id = (int) row.get("id");
        this.name = (String) row.get("name");
        this.dpt_id = (int) row.get("dpt_id");
        this.loginPw = (String) row.get("loginPw");

    }

    public Doctor(int id, String name, int dpt_id, String loginPw) {
        this.id = id;
        this.name = name;
        this.dpt_id = dpt_id;
        this.loginPw = loginPw;

    }


}
