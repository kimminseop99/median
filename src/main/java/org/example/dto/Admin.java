package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class Admin extends Dto {

    public String loginId;
    public String loginPw;
    public String name;


    public Admin(Map<String, Object> row) {
        super(row);
        this.name = (String) row.get("name");
        this.loginId = (String) row.get("loginId");
        this.loginPw = (String) row.get("loginPw");

    }

    public Admin(String name, String loginId, String loginPw) {
        this.name = name;

        this.loginId = loginId;
        this.loginPw = loginPw;
    }

}