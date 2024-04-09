package org.example.controller;

import org.example.container.Container;
import org.example.dto.Doctor;
import org.example.dto.Member;
import org.example.dto.Reservation;
import org.example.service.DoctorService;
import org.example.service.MemberService;
import org.example.service.ReservationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationController extends Controller {

    private Scanner sc;
    private ReservationService reservationService;
    private Session session;

    private MemberService memberService;

    public ReservationController() {
        sc = Container.getScanner();
        session = Container.getSession();
        reservationService = Container.reservaitonService;
        memberService = Container.memberService;
    }

    public void doAction(String cmd, String actionMethodName) {
        if (actionMethodName.equals("page")) {
            doCase();
        } else {
            System.out.println("존재하지 않는 명령어 입니다.");
        }

    }

    public void doCase() {

        while(true) {
            System.out.println("╔═════════════════════════════════════════════════════╗");
            System.out.println("║                     진료과 목록                       ║");
            System.out.println("╠═════════════════════════════════════════════════════╣");
            System.out.println("║      진료과명       |          전화번호(tel)           ║");
            System.out.println("╟─────────────────────────────────────────────────────╢");
            System.out.println("║    1.  간담췌외과     │          042-585-1402         ║");
            System.out.println("║    2.  신경외과       │          042-586-7676         ║");
            System.out.println("║    3.  산부인과       │          042-123-4567         ║");
            System.out.println("║    4.  흉부외과       │          042-987-6543         ║");
            System.out.println("║    5.  소아외과       │          042-111-2222         ║");
            System.out.println("╚═════════════════════════════════════════════════════╝");
            Member loginedMember = session.getLoginedMember();
            int dpt = 0;
            while (true) {
                System.out.printf("%s님 희망하는 진료과번호를 작성해주세요\n", loginedMember.name);
                System.out.print("진료과 : ");
                dpt = sc.nextInt();
                sc.nextLine();
                if(dpt> 5 || dpt < 1){
                    System.out.println("진료과 번호(1~5)를 다시 작성해주세요");
                    continue;
                }
                break;
            }

            List<Reservation> forPrintDoctors = reservationService.getReservationDoctors(dpt);


            if (forPrintDoctors.isEmpty()) {
                System.out.println("작성하신 진료과에 의사가 존재하지 않습니다.");
                continue;
            }

            System.out.println("[의사명]");
            for(int i = forPrintDoctors.size() -1; i >=0; i--){
                Reservation reservation = forPrintDoctors.get(i);
                System.out.println((i+1)+"."+reservation.name);
            }
            sc.nextLine();
           System.out.println("[시간]");
           System.out.println("1. 09:10 ~ 10:00");
           System.out.println("2. 10:10 ~ 11:00");
           System.out.println("3. 11:10 ~ 12:00");
           System.out.println("<점심시간> 12:00 ~ 13:00");
           System.out.println("4. 13:10 ~ 14:00");
           System.out.println("5. 14:10 ~ 15:00");
           System.out.println("6. 15:10 ~ 16:00");
           System.out.println("7. 16:10 ~ 17:00");
           System.out.println("8. 17:10 ~ 18:00");


            System.out.println("예약 하시고 싶은 의사명과 시간(1~8)을 작성해주세요");
            System.out.print("의사명 : ");
            String doctorChoice = sc.nextLine();
            System.out.print("시간 : ");
            int time = sc.nextInt();

            int patient_id = session.getLoginedMember().getId();
            int doctor_id = session.getLoginedMember().doctor_id;
            List<Reservation> getTimer = reservationService.getTime(time);


            for(int i = forPrintDoctors.size() -1; i >=0; i--){
                Reservation reservation = forPrintDoctors.get(i);
                if(doctorChoice.equals(reservation.name)){
                    if(getTimer.isEmpty()) {
                        reservationService.doReservation(patient_id, reservation.rh, doctor_id, reservation.name, time, reservation.dpt_id);
                    }else{
                        System.out.println("다른 시간대를 선택해 주세요");
                        continue;
                    }
                    break;
                }else{
                    System.out.println("해당 의사가 없습니다.");
                    return;
                }
            }






        }





    }

}