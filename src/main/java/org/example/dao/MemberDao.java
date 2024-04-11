package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Member;

import java.util.Map;

public class MemberDao extends Dao {
    private DBConnection dbConnection;

    public MemberDao() {
        dbConnection = Container.getDBConnection();
    }

    public int join(Member member) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO patient "));
        sb.append(String.format("SET `name` = '%s', ", member.name));
        sb.append(String.format("regDate = NOW(), "));
        sb.append(String.format("age = '%d', ", member.age));
        sb.append(String.format("phone = '%s', ", member.phone));
        sb.append(String.format("rrn = '%s', ", member.rrn));
        sb.append(String.format("height = '%f', ", member.height));
        sb.append(String.format("weight = '%f', ", member.weight));
        sb.append(String.format("ud = '%s', ", member.ud));
        sb.append(String.format("loginId = '%s', ", member.loginId));
        sb.append(String.format("loginPw = '%s' ", member.loginPw));


        return dbConnection.insert(sb.toString());
    }

    public Member getMemberByLoginId(String loginId) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM patient "));
        sb.append(String.format("WHERE loginId = '%s' ", loginId));

        Map<String, Object> row = dbConnection.selectRow((sb.toString()));

        if ( row.isEmpty() ) {
            return null;
        }

        return new Member(row);
    }

    public Member getMember(int id) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM patient "));
        sb.append(String.format("WHERE id = %d ", id));

        Map<String, Object> row = dbConnection.selectRow((sb.toString()));

        if ( row.isEmpty() ) {
            return null;
        }

        return new Member(row);
    }
}