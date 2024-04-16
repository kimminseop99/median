package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class ArticleReply extends Dto {
    public int patient_id;
    public int articleId;
    public String body;

    public ArticleReply(Map<String, Object> row) {
        super(row);
        this.articleId = (int) row.get("id");
        this.patient_id = (int) row.get("patient_id");
        this.body = (String) row.get("body");
    }
}