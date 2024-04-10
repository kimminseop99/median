package org.example.service;

import org.example.container.Container;
import org.example.dao.ReservationDao;
import org.example.dto.Reservation;

import java.util.List;
import java.util.function.DoublePredicate;

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

    public List<Reservation> getDoctorsDpt(int dpt_id) {
        return reservationDao.getDoctorsDpt(dpt_id);
    }

    public List<Integer> getReservedTimes(int doctor_id) {

        return reservationDao.getReservedTimes(doctor_id);
    }

    public List<Integer> getAvailableTimes(int doctor_id) {

        return reservationDao.getAvailableTimes(doctor_id);
    }


    public void createReservation(int patient_id, int doctor_id, int dpt_id, int time) {
        reservationDao.createReservation(patient_id, doctor_id, dpt_id, time);
    }
}