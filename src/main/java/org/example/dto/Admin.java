package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Admin extends Dto {
    public String name;
    public String loginId;
    public String loginPw;

    public Admin(Map<String, Object> row) {
        super(row);
        this.name = (String) row.get("name");
        this.loginId = (String) row.get("loginId");
        this.loginPw = (String) row.get("loginPw");

    }


}