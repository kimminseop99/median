package org.example.service;

import org.example.container.Container;
import org.example.dao.MemberDao;
import org.example.dao.ReservationDao;
import org.example.dto.Member;
import org.example.dto.Reservation;

import java.util.List;

public class ReservationService {
    private ReservationDao reservationDao;

    public ReservationService() {
        reservationDao = Container.reservationDao;
    }

    public int doReservation(int patient_id,String rh,int doctor_id, String name) {
        Reservation reservation = new Reservation(patient_id, rh, doctor_id, name);
        return reservationDao.doReservation(reservation);
    }

    public List<Reservation> getReservations(){
        return reservationDao.getReservations();
    }

    public Reservation getReservation(int patient_id){
        return reservationDao.getReservation(patient_id);
    }

}