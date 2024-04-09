package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class Doctor extends Dto{
    private int id;
    private String name;
    private int dpt_id;
    private String loginPw;

    public Doctor() {
        // 기본 생성자
    }

    // Lombok의 @AllArgsConstructor를 사용하여 생성자를 자동 생성하므로 따로 생성자 구현은 필요하지 않음

    public Doctor(Map<String, Object> row) {
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


    public int getDptId() {
        return dpt_id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setDpt_id(int dptId) {
        this.dpt_id = dptId;
    }
    public void setLoginPw(String loginPw) {
        this.loginPw = loginPw;
    }
    public String getLoginPw() {
        return loginPw;
    }

}