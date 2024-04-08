package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Article extends Dto {
    public String title;
    public String body;
    public int hit;
    public int patient_id;
    public int doctor_id;

    public Article(int patient_id, int doctor_id, String title, String body, int hit) {
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.title = title;
        this.body = body;
        this.hit = hit;
    }

    public Article(int memberId, int boardId, String title, String body) {

        this(memberId, boardId, title, body, 0);
    }

    public Article(Map<String, Object> row) {
        super(row);
        this.title = (String) row.get("title");
        this.body = (String) row.get("body");
        this.patient_id = (int) row.get("patient_id");
        this.doctor_id = (int) row.get("doctor_id");
        this.hit = (int) row.get("hit");
    }

    public void increaseHit() {
        hit++;
    }
}