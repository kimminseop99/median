package org.example.dao;


import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Doctor;
import org.example.dto.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DoctorDao extends Dao {

    private static DBConnection dbConnection;

    public DoctorDao() {
        dbConnection = Container.getDBConnection();
    }

    public static List<Doctor> getForPrintDoctors(int dpt) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM `doctor` "));
        sb.append(String.format("WHERE dpt_id = '%d' ", dpt));

        List<Doctor> doctors = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            // Doctor 객체 생성자를 통해 ID 값을 설정하여 객체 생성
            Doctor doctor = new Doctor((int) row.get("id"), (String) row.get("name"), (int) row.get("dpt_id"), (String) row.get("loginPw"));
            doctors.add(doctor);
        }

        return doctors;
    }

    public static List<Reservation> getDoctorId(int dptId) {

            StringBuilder sb = new StringBuilder();

            sb.append(String.format("SELECT id FROM doctor "));
            sb.append(String.format("WHERE dpt_id = %d", dptId));
            List<Reservation> reservations = new ArrayList<>();
            List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

            for ( Map<String, Object> row : rows ) {
                reservations.add(new Reservation((row)));
            }

            return reservations;

    }

    public int doDoctor(Doctor doctor) {
        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO doctor ");
        sb.append(String.format("(`name`, `dpt_id`, `loginPw`) VALUES ('%s', '%d', '%s')",
                doctor.getName(), doctor.getDptId(), doctor.getLoginPw()));

        return dbConnection.insert(sb.toString());
    }


    public List<Doctor> getDoctors() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM doctor"));

        List<Doctor> doctors = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            int id = (int) row.get("id");
            String name = (String) row.get("name");
            int dptId = (int) row.get("dpt_id");
            String loginPw = (String) row.get("loginPw");
            Doctor doctor = new Doctor(id, name, dptId, loginPw);
            doctors.add(doctor);
        }

        return doctors;
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



}