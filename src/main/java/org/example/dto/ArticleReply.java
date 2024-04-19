package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

public class ArticleReply extends Dto {
    public int articleId;
    public Integer patient_id;
    public Integer doctor_id;
    public Integer admin_id;
    public String body;

    public ArticleReply(int articleId, Integer patient_id, Integer doctor_id, Integer admin_id, String body) {
        this.articleId = articleId;
        this.patient_id = patient_id;
        this.doctor_id = doctor_id;
        this.admin_id = admin_id;
        this.body = body;
    }


    public ArticleReply(Map<String, Object> row) {
        super(row);
        this.articleId = (int) row.get("id");
        this.patient_id = (Integer) row.get("patient_id");
        this.doctor_id = (Integer) row.get("doctor_id");
        this.admin_id = (Integer) row.get("admin_id");
        this.body = (String) row.get("body");
    }
}