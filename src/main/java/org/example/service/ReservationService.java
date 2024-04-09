package org.example.service;

import org.example.container.Container;
import org.example.dao.ReservationDao;
import org.example.dto.Reservation;

import java.util.List;

public class ReservationService {
    private ReservationDao reservationDao;

    public ReservationService() {
        reservationDao = Container.reservationDao;
    }

    public int doReservation(int patient_id,String rh,int doctor_id, String name, int time, int dpt_id) {
        Reservation reservation = new Reservation(patient_id, rh, doctor_id, name, time, dpt_id);
        return reservationDao.doReservation(reservation);
    }

    public List<Reservation> getReservations(){
        return reservationDao.getReservations();
    }

    public Reservation getReservation(int patient_id){
        return reservationDao.getReservation(patient_id);
    }

    public List<Reservation> getReservationDoctors(int dpt) {
        return reservationDao.getReservationDoctors(dpt);
    }


    public List<Reservation> getTime(int time) {
        return reservationDao.getTime(time);
    }
}