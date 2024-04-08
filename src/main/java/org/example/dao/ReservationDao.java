package org.example.dao;

import org.example.container.Container;
import org.example.db.DBConnection;
import org.example.dto.Article;
import org.example.dto.Reservation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationDao extends Dao{
    private DBConnection dbConnection;

    public ReservationDao() {
        dbConnection = Container.getDBConnection();
    }


    public int doReservation(Reservation reservation) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("INSERT INTO reservation "));
        sb.append(String.format("SET patient_id = '%d', ", reservation.patient_id));
        sb.append(String.format("regDate = NOW(), "));
        sb.append(String.format("rh = '%s', ", reservation.rh));
        sb.append(String.format("doctor_id = '%d', ", reservation.doctor_id));
        sb.append(String.format("`name`= %s ", reservation.name));

        return dbConnection.insert(sb.toString());
    }

    public List<Reservation> getReservations() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("SELECT * FROM reservation"));

        List<Reservation> reservations = new ArrayList<>();
        List<Map<String, Object>> rows = dbConnection.selectRows(sb.toString());

        for ( Map<String, Object> row : rows ) {
            reservations.add(new Reservation((row)));
        }

        return reservations;
    }

    public Reservation getReservation(int patient_id) {
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


}
