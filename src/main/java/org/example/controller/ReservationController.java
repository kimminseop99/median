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
        if(actionMethodName.equals("page")){
            System.out.println("           환영합니다!! 여기는 예약 페이지 입니다          ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.println("                   1. 예약 정보 출력                   ");
            System.out.println("                   2. 병원 예약                        ");
            System.out.println("                   3. 뒤로 가기                        ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.print("번호를 선택해 주세요: ");
            int num = sc.nextInt();
            sc.nextLine();

            switch (num){
                case 1:
                    showReservationPage();
                    break;
                case 2:
                    createReservation();
                    break;
                case 3:
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    break;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");
                    break;
            }
        }else{
            System.out.println("명령어가 올바르지 않습니다.");
        }

    }


    public void showReservationPage() {
        // 예약 페이지 헤더 출력
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("                    예약 정보 확인                     ");
        System.out.println("═════════════════════════════════════════════════════");

        // 현재 로그인한 회원 정보 확인
        Member loginedMember = session.getLoginedMember();
        System.out.println("로그인한 회원: " + loginedMember.getName());

        // 예약 정보 출력
        List<Reservation> reservations = (List<Reservation>) reservationService.getReservation(loginedMember.getId());
        if (reservations.isEmpty()) {
            System.out.println("예약된 정보가 없습니다.");
        } else {
            System.out.println("[예약 목록]");
            for (Reservation reservation : reservations) {
                System.out.println("진료과: " + reservation.getDpt_id());
                System.out.println("의사: " + reservation.getDoctor_id());
                System.out.println("예약 시간: " + convertTimeToString(reservation.getTime()));
                System.out.println("환자 ID: " + reservation.getPatient_id());
                System.out.println();
            }
        }

        // 예약 페이지 푸터 출력
        System.out.println("═════════════════════════════════════════════════════");
    }


    public void createReservation() {

        while (true) {
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
            System.out.print("진료과 번호를 선택하세요: ");
            int dpt_id = sc.nextInt();
            sc.nextLine();

            // 선택한 진료과에 해당하는 의사 목록 가져오기
            List<Reservation> doctorsDpt = reservationService.getDoctorsDpt(dpt_id);

            // 의사 목록 출력
            System.out.println("[의사 목록]");
            for (int i = 0; i < doctorsDpt.size(); i++) {
                Reservation doctor = doctorsDpt.get(i);
                System.out.println((i + 1) + ". " + doctor.getName());
            }

            // 사용자로부터 의사 번호 입력 받기
            System.out.print("의사 번호를 선택하세요: ");
            int doctor_id = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            // 선택한 의사의 예약 가능한 시간대 목록 가져오기
            List<Integer> availableTimes = reservationService.getAvailableTimes(doctor_id);

            // 예약 가능한 시간대 출력
            System.out.println("[예약 가능한 시간대]");
            for (int i = 0; i < availableTimes.size(); i++) {
                int time = availableTimes.get(i);
                System.out.println((i + 1) + ". " + convertTimeToString(time));
            }

            // 사용자로부터 시간대 번호 입력 받기
            System.out.print("예약할 시간대 번호를 선택하세요: ");
            int selectedTimeIndex = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            // 선택한 시간대 번호에 해당하는 시간 가져오기
            int time = availableTimes.get(selectedTimeIndex - 1);

            // 세션에서 현재 로그인한 회원의 ID 가져오기
            int patient_id = session.getLoginedMember().getId();

            // 예약 생성
            reservationService.createReservation(patient_id, doctor_id, dpt_id, time);

            System.out.println("예약이 성공적으로 생성되었습니다.");


            // 반복 여부 확인
            System.out.print("더 예약하시겠습니까? (Y/N): ");
            String answer = sc.nextLine().trim();
            if (!answer.equalsIgnoreCase("Y")) {
                break;


            }
        }
    }

    private String convertTimeToString(int time) {
        // 시간과 분으로 분리
        int hour = time / 100;
        int minute = time % 100;

        // 시간과 분을 문자열로 변환하여 조합
        String hourStr = (hour < 10) ? "0" + hour : String.valueOf(hour);
        String minuteStr = (minute < 10) ? "0" + minute : String.valueOf(minute);

        // "HH:mm" 형식의 문자열 반환
        return hourStr + ":" + minuteStr;
    }


}