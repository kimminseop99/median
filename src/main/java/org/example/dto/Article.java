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


    public Article(int patient_id, String title, String body, int hit) {
        this.patient_id = patient_id;
        this.title = title;
        this.body = body;
        this.hit = hit;
    }

    public Article(int patient_id, String title, String body) {

        this(patient_id, title, body, 0);
    }

    public Article(Map<String, Object> row) {
        super(row);
        this.title = (String) row.get("title");
        this.body = (String) row.get("body");
        this.patient_id = (int) row.get("patient_id");
        this.hit = (int) row.get("hit");
    }


    public void increaseHit() {
        hit++;
    }
}