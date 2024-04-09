package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.util.Util;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Map;

@Getter
@Setter
public class Dto {
    private int id;
    private LocalDateTime regDate;

    public Dto() {
        this(0);
    }

    public Dto(int id) {
        this(id, Util.getNowDateStr());
    }

    public Dto(int id, LocalDateTime regDate) {
        this.id = id;
        this.regDate = regDate;
    }

    public Dto(Map<String, Object> row) {
        this((int) row.get("id"), (Date) row.get("regDate"));
    }

    public Dto(int id, Date regDate) {
        this.id = id;
        this.regDate = regDate.toLocalDateTime();
    }
}
