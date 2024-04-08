package org.example.container;

import org.example.controller.Session;
import org.example.dao.*;
import org.example.db.DBConnection;
import org.example.service.*;

import java.util.Scanner;

public class Container {
    public static Scanner sc;
    public static Session session;
    public static DBConnection dbConnection;
    public static ArticleDao articleDao;
    public static MemberDao memberDao;
    public static ArticleService articleService;

    public static MemberService memberService;
    public static ReservationDao reservationDao;
    public static ReservationService reservaitonService;
    public static DoctorDao doctorDao;

    public static DoctorService doctorService;
    public static DptDao dptDao;
    public static DptService dptService;


    static {
        articleDao = new ArticleDao();
        memberDao = new MemberDao();
        articleService = new ArticleService();
        memberService = new MemberService();
        reservationDao = new ReservationDao();
        reservaitonService = new ReservationService();
        doctorDao = new DoctorDao();
        doctorService = new DoctorService();
    }

    public static Scanner getScanner(){
        if(sc == null){
            sc = new Scanner(System.in);

        }

        return sc;
    }

    public static Session getSession(){
        if(session == null){
            session = new Session();

        }

        return session;
    }

    public static DBConnection getDBConnection() {
        if ( dbConnection == null ) {
            dbConnection = new DBConnection();
        }

        return dbConnection;
    }
}
