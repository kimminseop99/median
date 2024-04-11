package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.util.Util;

import java.util.Date;
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
        Object idObject = row.get("id");
        Object regDateObject = row.get("regDate");

        if (idObject != null && regDateObject != null) {
            this.id = (int) idObject;
            this.regDate = String.valueOf(regDateObject);
        } else {
            // 예외 처리 또는 기본값 설정
            this.id = 0;
            this.regDate = Util.getNowDateStr();
        }
    }
}