package org.example.service;

import org.example.container.Container;
import org.example.dao.DoctorDao;
import org.example.dao.ReservationDao;
import org.example.dto.Doctor;
import org.example.dto.Reservation;

import java.util.List;

public class DoctorService {
    private DoctorDao doctorDao;

    public DoctorService() {
        doctorDao = Container.doctorDao;
    }

    public int doDoctor(String name, int dpt_id, String loginPw) {
        Doctor doctor = new Doctor();
        doctor.setName(name);
        doctor.setDpt_id(dpt_id);
        doctor.setLoginPw(loginPw);
        return doctorDao.doDoctor(doctor);
    }


    public List<Doctor> getDoctors(){
        return doctorDao.getDoctors();
    }

    public List<Doctor> getForPrintDoctors(int dpt) {
        return DoctorDao.getForPrintDoctors(dpt);
    }
}