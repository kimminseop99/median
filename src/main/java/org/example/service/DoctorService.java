package org.example.service;

import org.example.container.Container;
import org.example.dao.DoctorDao;
import org.example.dto.Doctor;
import org.example.dto.Reservation;

import java.util.List;

public class DoctorService {
    private DoctorDao doctorDao;

    public DoctorService() {
        doctorDao = Container.doctorDao;
    }

    public static List<Integer> getDoctorId(int dptId) {
        return DoctorDao.getDoctorId(dptId);
    }

    public static void StringUpdate(String Info, String changeInfo, int id) {
        DoctorDao.StringUpdate(Info, changeInfo, id);
    }

    public static String getDoctorName(int doctorId) {
        return DoctorDao.getDoctorName(doctorId);
    }

    public static String getDptName(int dptId) {
        return DoctorDao.getDptName(dptId);
    }

    public static List<Doctor> getAllDoctor() {
        return DoctorDao.getAllDoctor();
    }

    public static int deleteDoctor(int doctorNum) {
        return DoctorDao.deleteDoctor(doctorNum);
    }

    public static int join(String name, int dptId, String loginPw) {
        return DoctorDao.join(name, dptId, loginPw);

    }

    public static List<Integer> getAllDoctorId() {
        return DoctorDao.getAllDoctorId();
    }

    public static void createDoctorTime(int doctor_id) {
        DoctorDao.createDoctorTime(doctor_id);
    }

    public static int getJoinDoctorId() {
        return DoctorDao.getJoinDoctorId();
    }

    public static void deleteDoctorTime(int doctorNum) {
        DoctorDao.deleteDoctorTime(doctorNum);
    }


    public int doDoctor(String name, int dpt_id, String loginPw) {
        return  doctorDao.doDoctor(name, dpt_id, loginPw);
    }


    public static List<Doctor> getForPrintDoctors(int dpt) {
        return DoctorDao.getForPrintDoctors(dpt);
    }

    public Doctor getDoctorByLoginId(int id) {
        return doctorDao.getDoctorByLoginId(id);
    }
}