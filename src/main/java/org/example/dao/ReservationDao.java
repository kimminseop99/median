package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationDao extends Dao{
    private DBConnection dbConnection;

    public ReservationDao() {
        dbConnection = Container.getDBConnection();
    }


    public int doReservation(Reservation reservation) { // 새로운 예약 db에 추가
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO reservation "));
        sb.append(String.format("SET patient_id = '%d', ", reservation.patient_id));
        sb.append(String.format("regDate = NOW(), "));
        sb.append(String.format("rh = '%s', ", reservation.rh));
        sb.append(String.format("doctor_id = '%d', ", reservation.doctor_id));
        sb.append(String.format("`name`= %s, ", reservation.name));
        sb.append(String.format("`time`= %d, ", reservation.time));
        sb.append(String.format("`dpt_id`= %d ", reservation.dpt_id));

        return dbConnection.insert(sb.toString());
    }

    public List<Reservation> getReservations() { // 모든 예약목록 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM reservation"));

        List<Reservation> reservations = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            reservations.add(new Reservation((row)));
        }

        return reservations;
    }

    public Reservation getReservation(int patient_id) { // 특정 환자 예약정보 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM reservation "));
        sb.append(String.format("WHERE patient_id = %d ", patient_id));

        Map<String, Object> row = dbConnection.selectRow(sb.toString());

        if ( row.isEmpty() ) {
            return null;
        }

        return new Reservation(row);
    }

    public List<Reservation> getReservationDoctors(int dpt) { // 특정 진료과의 의사들의 예약 정보 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT R.* "));
        sb.append(String.format("FROM reservation AS R "));
        sb.append(String.format("INNER JOIN doctor AS D "));
        sb.append(String.format("ON R.dpt_id = D.dpt_id "));
        sb.append(String.format("WHERE D.dpt_id = %d ", dpt));
        sb.append(String.format("ORDER BY R.id DESC"));



        List<Reservation> reservations = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            reservations.add(new Reservation((row)));
        }



        return reservations;
    }


    public List<Reservation> getTime(int time) { // 특정 시간대에 예약된 정보 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM reservation "));
        sb.append(String.format("where `time` = %d", time));
        List<Reservation> reservations = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            reservations.add(new Reservation((row)));
        }

        return reservations;
    }

    public List<Reservation> getDoctorsDpt(int dpt_id) { // 특정 진료과에 속한 의사들의 정보
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT D.* "));
        sb.append(String.format("FROM doctor AS D "));
        sb.append(String.format("INNER JOIN reservation AS R "));
        sb.append(String.format("ON D.id = R.doctor_id "));
        sb.append(String.format("WHERE R.dpt_id = %d ", dpt_id));
        sb.append(String.format("ORDER BY D.id DESC"));

        List<Reservation> doctors = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            doctors.add(new Reservation((row)));
        }

        return doctors;
    }

    public List<Integer> getReservedTimes(int doctorNumber) { // 특정 의사의 예약 시간 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT time "));
        sb.append(String.format("FROM reservation "));
        sb.append(String.format("WHERE doctor_id = %d ", doctorNumber));

        List<Integer> reservedTimes = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            reservedTimes.add((int) row.get("time"));
        }

        return reservedTimes;
    }

    public List<Integer> getAvailableTimes(int doctor_id) { // 특정 의사의 예약 가능 시간 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT DISTINCT time "));
        sb.append(String.format("FROM reservation "));
        sb.append(String.format("WHERE doctor_id = %d", doctor_id));

        List<Integer> availableTimes = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            availableTimes.add((int) row.get("time"));
        }

        return availableTimes;
    }


    public void createReservation(int patientId, int doctorId, int selectedTime, int time) { // 새오운 예약 생성
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO reservation "));
        sb.append(String.format("(patient_id, doctor_id, time) "));
        sb.append(String.format("VALUES (%d, %d, %d)", patientId, doctorId, selectedTime));

        dbConnection.insert(sb.toString());
    }
}
