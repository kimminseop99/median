package org.example.dao;


import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Doctor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoctorDao extends Dao {

    private static DBConnection dbConnection;

    public DoctorDao() {
        dbConnection = Container.getDBConnection();
    }

    public static List<Doctor> getForPrintDoctors(int dpt) { // 특정 과에 속한 모든 의사 정보
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM `doctor` "));
        sb.append(String.format("WHERE dpt_id = '%d' ", dpt));

        List<Doctor> doctors = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            // Doctor 객체 생성자를 통해 ID 값을 설정하여 객체 생성
            Doctor doctor = new Doctor((String) row.get("name"), (int) row.get("dpt_id"), (String) row.get("loginPw"));
            doctors.add(doctor);
        }

        return doctors;
    }

    public static List<Integer> getDoctorId(int dptId) { // 특정 과에 속한 의사들의 의사 번호

        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT id FROM doctor "));
        sb.append(String.format("WHERE dpt_id = %d", dptId));

        List<Integer> doctorId = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            doctorId.add((int) row.get("id"));
        }

        return doctorId;

    }

    public static void StringUpdate(String Info, String changeInfo, int id) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("UPDATE doctor "));
        sb.append(String.format("SET `%s` = '%s' ", Info, changeInfo));
        sb.append(String.format("WHERE id = %d ", id));


        dbConnection.update(sb.toString());
    }

    public static String getDoctorName(int doctorId) { // 의사 id로 의사 이름 출력
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT doctor.name "));
        sb.append(String.format("FROM doctor "));
        sb.append(String.format("WHERE id = %d ", doctorId));

        String doctorName = "";
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
        for (Map<String, Object> row : rows) {
            doctorName = (String) row.get("name");
            break;
        }

        return doctorName;
    }

    public static String getDptName(int dptId) { // 과 id로 과 이름 가져오기
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT dpt.name "));
        sb.append(String.format("FROM dpt "));
        sb.append(String.format("WHERE id = %d ", dptId));

        String dptName = "";
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());
        for (Map<String, Object> row : rows) {
            dptName = (String) row.get("name");
            break;
        }

        return dptName;
    }

    public static List<Doctor> getAllDoctor() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM doctor"));

        List<Doctor> doctorList = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            doctorList.add(new Doctor((row)));
        }

        return doctorList;
    }

    public static int deleteDoctor(int doctorNum) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("DELETE FROM doctor "));
        sb.append(String.format("WHERE id = %d ", doctorNum));

        return dbConnection.delete(sb.toString());
    }

    public static int join(Doctor doctor) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO doctor "));
        sb.append(String.format("SET `name` = '%s', ", doctor.name));
        sb.append(String.format("dpt_id = %d, ", doctor.dpt_id));
        sb.append(String.format("loginPw = '%s', ", doctor.loginPw));
        sb.append(String.format("regDate = NOW() "));

        return dbConnection.insert(sb.toString());
    }
    public static List<Integer> getAllDoctorId() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT id FROM doctor ORDER BY id ASC "));

        List<Integer> doctorId = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            doctorId.add((int) row.get("id"));
        }

        return doctorId;
    }

    public static int getJoinDoctorId() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT id FROM doctor ORDER BY id DESC LIMIT 1 "));

        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        // 결과가 없으면 -1 반환
        if (rows.isEmpty()) {
            return -1;
        }

        // 첫 번째 행의 ID 값을 반환
        return (int) rows.get(0).get("id");
    }

    public static void createDoctorTime(int doctorId) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO doctor_time (doctor_id, TIME) VALUES ");

        // 의사와 그에 상응하는 시간을 정의
        String[] times = {"09:10", "10:10", "11:10", "13:10", "14:10", "15:10", "16:10", "17:10", "18:10"};

        // VALUES 부분에 들어갈 값을 빌더에 추가
        for (int i = 0; i < times.length; i++) {
            sb.append(String.format("(%d, '%s')", doctorId, times[i]));

            // 마지막 요소가 아니라면 쉼표와 공백을 추가하여 다음 요소와 구분
            if (i < times.length - 1) {
                sb.append(", ");
            }
        }

        dbConnection.insert(sb.toString());

    }

    public static void deleteDoctorTime(int doctorNum) { // 해당 의사의 시간을 모두 삭제하는 쿼리 생성
        StringBuilder sb = new StringBuilder();


        sb.append("DELETE FROM doctor_time ");
        sb.append(String.format("WHERE doctor_id = %d", doctorNum));

        dbConnection.delete(sb.toString());
    }

    public static void deleteDoctorReservation(int doctorNum) { // 해당 의사의 예약을 모두 삭제하는 쿼리 생성
        StringBuilder sb = new StringBuilder();


        sb.append("DELETE FROM reservation ");
        sb.append(String.format("WHERE doctor_id = %d", doctorNum));

        dbConnection.delete(sb.toString());
    }

    public Doctor getDoctor(String name) {
        StringBuilder sb = new StringBuilder();

        sb.append("SELECT * ");
        sb.append("FROM doctor ");
        sb.append(String.format("WHERE `name` = '%s' ", name));

        Map<String, Object> row = dbConnection.selectRow(sb.toString());

        if (row.isEmpty()) {
            return null;
        }

        return new Doctor(row);
    }


    public Doctor getDoctorByLoginId(int id) { // 특정 id를 가진 의사의 정보
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM doctor "));
        sb.append(String.format("WHERE id = '%d' ", id));

        Map<String, Object> row = dbConnection.selectRow((sb.toString()));

        if (row.isEmpty()) {
            return null;
        }

        return new Doctor(row);
    }
}