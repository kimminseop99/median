package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Case extends Dto {
    private String name;
    private String code;

    public Case(Map<String, Object> row) {
        super(row);
        this.name = (String) row.get("name");
        this.code = (String) row.get("code");
    }
}