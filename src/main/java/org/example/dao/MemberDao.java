package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Member;
import org.example.dto.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MemberDao extends Dao {
    private DBConnection dbConnection;

    public MemberDao() {
        dbConnection = Container.getDBConnection();
    }

    public int join(Member member) { // 회원가입
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

    public Member getMemberByLoginId(String loginId) { // 회원가입 아이디 중복 처리
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

    public Member getMember(int id) { // 번호에 따른 환자 정보
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

    public void StringUpdate(String Info, String changeInfo, int id) { // 문자열 정보 업데이트
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("UPDATE patient "));
        sb.append(String.format("SET `%s` = '%s' ", Info, changeInfo));
        sb.append(String.format("WHERE id = %d ", id));



        dbConnection.update(sb.toString());
    }

    public void IntUpdate(String Info, int changeInfo, int id) { // 숫자 정보 업데이트
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("UPDATE patient "));
        sb.append(String.format("SET `%s` = %d ", Info, changeInfo));
        sb.append(String.format("WHERE id = %d ", id));



        dbConnection.update(sb.toString());
    }

    public void DoubleUpdate(String Info, double changeInfo, int id) { // 실수 정보 업데이트
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("UPDATE patient "));
        sb.append(String.format("SET `%s` = %f ", Info, changeInfo));
        sb.append(String.format("WHERE id = %d ", id));



        dbConnection.update(sb.toString());
    }

    public String getMemberName(int patientId) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT patient.name "));
        sb.append(String.format("FROM patient "));
        sb.append(String.format("WHERE id = %d ", patientId));

        String patientName = "";
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
        for (Map<String, Object> row : rows) {
            patientName = (String) row.get("name");
            break;
        }

        return patientName;
    }

    public List<Member> getAllMember() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM patient"));

        List<Member> memberList = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            memberList.add(new Member((row)));
        }

        return memberList;
    }
}
