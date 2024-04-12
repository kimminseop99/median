package org.example.controller;

import org.example.container.Container;
import org.example.dto.Member;
import org.example.dto.Reservation;
import org.example.resource.DoctorName;
import org.example.resource.DptName;
import org.example.service.MemberService;
import org.example.service.ReservationService;

import java.sql.Time;
import java.text.SimpleDateFormat;
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
            System.out.println("                      예약 페이지                      ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.println("|                   1. 예약 정보 출력                  |");
            System.out.println("|                   2. 병원 예약                      |");
            System.out.println("|                   3. 예약 취소                      |");
            System.out.println("|                   4. 뒤로 가기                      |");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.print("번호를 선택해 주세요: ");
            int num = sc.nextInt();
            sc.nextLine();

            switch (num) {
                case 1:
                    showReservationPage();
                    break;
                case 2:
                    createReservation();
                    break;
                case 3:
                    deleteReservation();
                    break;
                case 4:
                    System.out.println("이전 메뉴로 돌아갑니다.");
                    break;
                default:
                    System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");
                    break;
            }
        } else {
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

        int patient_id = session.getLoginedMember().getId();

        // 예약 정보 출력
        List<Reservation> reservations = reservationService.getReservation(patient_id);
        if (reservations.isEmpty()) {
            System.out.println("예약된 정보가 없습니다.");
            System.out.println("예약 페이지로 되돌아가는 중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 스레드가 중단되었을 때 발생하는 예외 처리
                e.printStackTrace();
            }

            doAction("reservation", "page");
        } else {
            System.out.println("[예약 목록]");
            for (Reservation reservation : reservations) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String formattedTime = sdf.format(reservation.doctor_time);


                System.out.println("** 예약 번호 : "+reservation.getId()+" **");

                System.out.println("예약자 이름: " + loginedMember.getName());
                System.out.println("의사 이름: " + DoctorName.numToDoctor(reservation.dpt_id, reservation.doctor_id));
                System.out.println("진료과: " + DptName.numToDept(reservation.dpt_id));
                System.out.println("예약 시간: " + formattedTime);
                System.out.println("환자 증상: " + reservation.rh);
                System.out.println();
            }
        }

        // 예약 페이지 푸터 출력
        System.out.println("═════════════════════════════════════════════════════");

        System.out.println("예약 페이지로 되돌아가는 중...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 스레드가 중단되었을 때 발생하는 예외 처리
            e.printStackTrace();
        }
        doAction("reservation", "page");
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
            // 세션에서 현재 로그인한 회원의 ID 가져오기
            int patient_id = session.getLoginedMember().getId();
            int dpt_id = 0;
            boolean checkduplicate = false;
            while (true) {
                System.out.print("진료과 번호를 선택하세요: ");
                dpt_id = sc.nextInt();
                sc.nextLine();
                // 같은과에 중복으로 예약하는지 체크
                List<Reservation> isDuplicateReservation = reservationService.getReservation(patient_id);
                for (Reservation list : isDuplicateReservation) {
                    if (list.dpt_id == dpt_id) {
                        System.out.println("동일한 진료과에 중복으로 예약할 수 없습니다.");
                        checkduplicate = true;
                        break;
                    }

                }

                if(!checkduplicate){
                    break;
                }

                checkduplicate = false;

            }
            // 같은과에 중복으로 예약하는지 체크

            // 의사 목록 가져오기
            List<String> doctors = reservationService.getDoctorsDpt(dpt_id);

// 의사 목록 출력


            System.out.println("[의사 목록]");
            for (int i = 0; i < doctors.size(); i++) {
                String doctor = doctors.get(i);
                System.out.println((i + 1)+ ". " + doctor);
            }


            // 사용자로부터 의사 번호 입력 받기
            System.out.print("의사 번호를 선택하세요: ");
            int doctor_id = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            // 선택한 의사의 시간대 목록 가져오기
            List<Time> doctor_time = reservationService.getDoctor_time(doctor_id);

            // 선택한 의사의 예약 불가능한 시간대 목록 가져오기
            List<Time> AavailableTimes = reservationService.getUnavailableTimes(dpt_id, doctor_id);


            // 예약 가능한 시간대 출력
            System.out.println("[예약 가능한 시간대]");
            if (doctor_time.isEmpty()) {
                System.out.println("예약 가능한 시간대가 없습니다.");
                return; // 메서드 종료
            }

            for (int i = 0; i < doctor_time.size(); i++) {
                Time time = doctor_time.get(i);
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String formattedTime = sdf.format(time);
                System.out.printf("%d. %s\n", (i + 1), formattedTime);

            }


            int selectedTimeIndex = 0;

            while (true) {
                // 사용자로부터 시간대 번호 입력 받기
                System.out.print("예약할 시간대 번호를 선택하세요: ");
                selectedTimeIndex = sc.nextInt();
                sc.nextLine(); // 버퍼 비우기


                // 선택한 시간대 번호가 유효한지 확인
                if (selectedTimeIndex < 1 || selectedTimeIndex > doctor_time.size()) {
                    System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");
                    continue;
                }
                Time selectedTime = doctor_time.get(selectedTimeIndex - 1);
                boolean isAvailableTime = true;
                for (Time Atime : AavailableTimes) {
                    if (selectedTime.equals(Atime)) {
                        isAvailableTime = false;
                        break;
                    }
                }

                if (!isAvailableTime) {
                    System.out.println("선택한 시간은 이미 예약되었습니다.");
                    continue;
                }
                break;
            }


            // 선택한 시간대 번호에 해당하는 시간 가져오기
            Time selectedTime = doctor_time.get(selectedTimeIndex - 1);

            // 세션에서 현재 로그인한 회원의 이름 가져오기
            String name = session.getLoginedMember().getName();

            System.out.print("증상을 적어주세요 : ");
            String rh = sc.nextLine();




            // 예약 생성
            int newReservation = reservationService.createReservation(patient_id, rh, name, doctor_id, dpt_id, selectedTime);

            System.out.printf("%d번 예약이 성공적으로 생성되었습니다.\n", newReservation);

            showReservationPage();

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                // 스레드가 중단되었을 때 발생하는 예외 처리
                e.printStackTrace();
            }
            System.out.println("예약 페이지로 되돌아가는 중...");
            doAction("reservation", "page");
        }
    }

    public void deleteReservation() {
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("                    예약 정보 확인                     ");
        System.out.println("═════════════════════════════════════════════════════");

        // 현재 로그인한 회원 정보 확인
        Member loginedMember = session.getLoginedMember();

        int patient_id = session.getLoginedMember().getId();

        // 예약 정보 출력
        List<Reservation> reservations = reservationService.getReservation(patient_id);
        if (reservations.isEmpty()) {
            System.out.println("예약된 정보가 없습니다.");
            System.out.println("예약 페이지로 되돌아가는 중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 스레드가 중단되었을 때 발생하는 예외 처리
                e.printStackTrace();
            }

            doAction("reservation", "page");
        } else {
            System.out.println("[예약 목록]");
            for (Reservation reservation : reservations) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String formattedTime = sdf.format(reservation.doctor_time);


                System.out.println("** 예약 번호 : "+reservation.getId()+" **");

                System.out.println("예약자 이름: " + loginedMember.getName());
                System.out.println("의사 이름: " + DoctorName.numToDoctor(reservation.dpt_id, reservation.doctor_id));
                System.out.println("진료과: " + DptName.numToDept(reservation.dpt_id));
                System.out.println("예약 시간: " + formattedTime);
                System.out.println("환자 증상: " + reservation.rh);
                System.out.println();
            }
        }

        // 예약 페이지 푸터 출력
        System.out.println("═════════════════════════════════════════════════════");

        System.out.print("취소하시고 싶은 예약번호를 선택해주세요 : ");
        int reservationNumber = sc.nextInt();

        reservationService.cancelReservation(reservationNumber);
        System.out.println("예약이 취소되었습니다.");

        System.out.println("예약 페이지로 되돌아가는 중...");
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // 스레드가 중단되었을 때 발생하는 예외 처리
            e.printStackTrace();
        }
        doAction("reservation", "page");
    }


}