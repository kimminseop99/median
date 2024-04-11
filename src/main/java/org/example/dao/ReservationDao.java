package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Reservation;

import java.sql.Time;
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

    public List<Reservation> getReservation(int patient_id) { // 특정 환자 예약정보 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * "));
        sb.append(String.format("FROM reservation "));
        sb.append(String.format("WHERE patient_id = %d ", patient_id));

        List<Reservation> reservations = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            reservations.add(new Reservation((row)));
        }

        return reservations;
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
        sb.append(String.format("where `doctor_time` = %d", time));
        List<Reservation> reservations = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            reservations.add(new Reservation((row)));
        }

        return reservations;
    }

    public List<String> getDoctorsDpt(int dpt_id) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT doctor.name "));
        sb.append(String.format("FROM doctor "));
        sb.append(String.format("JOIN dpt ON doctor.dpt_id = dpt.id "));
        sb.append(String.format("WHERE dpt.id = %d ", dpt_id));

        List<String> doctors = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            doctors.add((String) row.get("name"));
        }

        return doctors;
    }

    public List<Integer> getReservedTimes(int doctorNumber) { // 특정 의사의 예약 시간 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT doctor_time "));
        sb.append(String.format("FROM reservation "));
        sb.append(String.format("WHERE doctor_id = %d ", doctorNumber));

        List<Integer> reservedTimes = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            reservedTimes.add((int) row.get("time"));
        }

        return reservedTimes;
    }

    public List<Time> getDoctor_time(int doctor_id) { // 특정 의사의 예약 가능 시간 보여줌
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT `time` "));
        sb.append(String.format("FROM doctor_time "));
        sb.append(String.format("WHERE doctor_id = %d", doctor_id));

        List<Time> doctor_time = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            Time time = (Time) row.get("time");
            doctor_time.add(time);
        }

        return doctor_time;
    }



    public int createReservation(Reservation reservation) { // 새오운 예약 생성
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO reservation "));
        sb.append(String.format("SET regDate = NOW(), "));
        sb.append(String.format("patient_id = %d, ", reservation.patient_id));
        sb.append(String.format("rh = '%s', ", reservation.rh));
        sb.append(String.format("name = '%s', ", reservation.name));
        sb.append(String.format("doctor_id = %d, ", reservation.doctor_id));
        sb.append(String.format("dpt_id = %d, ", reservation.dpt_id));
        sb.append(String.format("doctor_time = '%s' ", reservation.time));

        return dbConnection.insert(sb.toString());
    }

    public List<Time> getUnavailableTimes(int dpt_id, int doctor_id) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT doctor_time "));
        sb.append(String.format("FROM reservation "));
        sb.append(String.format("WHERE dpt_id = %d ", dpt_id));
        sb.append(String.format("AND doctor_id = %d ", doctor_id));

        List<Time> unavailableTimes = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for (Map<String, Object> row : rows) {
            Time time = (Time) row.get("doctor_time");
            unavailableTimes.add(time);
        }

        return unavailableTimes;
    }
}
