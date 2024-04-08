package org.example.dao;


import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Article;
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

        sb.append(String.format("SELECT D.* "));
        sb.append(String.format("FROM `doctor` AS D "));
        sb.append(String.format("WHERE dpt_id = '%d' ", dpt));

        List<Doctor> doctors = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            doctors.add(new Doctor((row)));
        }

        return doctors;
    }

    public int doDoctor(Doctor doctor) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO doctor "));
        sb.append(String.format("SET `name` = '%s', ", doctor.name));
        sb.append(String.format("dpt_id = '%d', ", doctor.id));
        sb.append(String.format("loginPw = '%s' ", doctor.loginPw));

        return dbConnection.insert(sb.toString());
    }

    public List<Doctor> getDoctors() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM doctor"));

        List<Doctor> doctors = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            doctors.add(new Doctor((row)));
        }

        return doctors;
    }

    public Doctor getDoctor(String name) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM doctor "));
        sb.append(String.format("WHERE `name` = %d ", name));

        Map<String, Object> row = dbConnection.selectRow(sb.toString());

        if ( row.isEmpty() ) {
            return null;
        }

        return new Doctor(row);
    }


}
