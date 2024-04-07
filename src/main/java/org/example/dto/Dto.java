package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.util.Util;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Dto {
    public int id;
    public String regDate;

    public Dto() {
        this(0);
    }

    public Dto(int id) {
        this(id, Util.getNowDateStr());
    }

    public Dto(Map<String, Object> row) {
        this((int) row.get("id"), ((LocalDateTime) row.get("regDate")).toString());
    }

}