package org.example.service;

import org.example.container.Container;
import org.example.dao.ReservationDao;
import org.example.dto.Reservation;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ReservationService {
    private static ReservationDao reservationDao;

    public ReservationService() {
        reservationDao = Container.reservationDao;
    }

    public static void setName(String exname, String name) {
        ReservationDao.setName(exname, name);
    }

    public static List<Reservation> getforPrintReservation(int id) {
        return ReservationDao.getforPrintReservation(id);
    }

    public static List<Reservation> getReservations() {
        return reservationDao.getReservations();
    }

    public List<Reservation> getReservation(int patient_id){
        return reservationDao.getReservation(patient_id);
    }


    public List<Reservation> getDoctorsDpt(int dpt_id) {
        return reservationDao.getDoctorsDpt(dpt_id);
    }

    public List<Time> getDoctor_time(int doctor_id) {

        return reservationDao.getDoctor_time(doctor_id);
    }
    public int createReservation(int patient_id, String rh, String name, int doctor_id, int dpt_id, Time doctor_time, Date doctor_date) {
        Reservation reservation = new Reservation(patient_id, rh, doctor_id, name, dpt_id, doctor_time, doctor_date);
        return reservationDao.createReservation(reservation);
    }

    public List<Time> getUnavailableTimes(String doctor_date, int doctor_id) {
        return reservationDao.getUnavailableTimes(doctor_date,doctor_id);
    }


    public static void cancelReservation(int reservationNumber) {
        reservationDao.cancelReservation(reservationNumber);
    }
}