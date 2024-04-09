package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.util.Util;

import java.util.Map;

@Getter
@Setter

public class Dpt extends Dto {
    public int id;
    public String name;
    public String phone;

    public Dpt(Map<String, Object> row) {

        this.name = (String) row.get("name");
        this.phone = (String) row.get("phone");
    }

    public Dpt(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public String getName() {
        return name;
    }

}