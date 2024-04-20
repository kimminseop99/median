package org.example.controller;

import org.example.App;
import org.example.container.Container;
import org.example.dto.Doctor;
import org.example.dto.Reservation;
import org.example.resource.ChangeInfo;
import org.example.resource.PrintInfo;
import org.example.service.DoctorService;
import org.example.service.MemberService;
import org.example.service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class DoctorController extends Controller {

    private Scanner sc;
    private DoctorService doctorService;
    private Session session;

    private MemberService memberService;
    private  ReservationService reservationService;

    public DoctorController() {
        sc = Container.getScanner();
        session = Container.getSession();
        doctorService = Container.doctorService;
        memberService = Container.memberService;
        reservationService = Container.reservaitonService;
    }

    public void doAction(String cmd, String actionMethodName) {
        while (true) {
        if (actionMethodName.equals("page")) {
            System.out.println("                      의사 페이지                      ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.println("                    1. 로그인                         ");
            System.out.println("                    2. 로그아웃                       ");
            System.out.println("                    3. 의사 정보 수정                  ");
            System.out.println("                    4. 진료                          ");
            System.out.println("                    5. 뒤로 가기                      ");
            System.out.println("═════════════════════════════════════════════════════");
                System.out.print("번호를 선택해 주세요: ");
                int num = 0;
                try {
                    num = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력 형식입니다. 숫자를 입력해주세요.");
                    sc.nextLine();
                    continue;
                }
                switch (num) {
                    case 1:
                        if (Container.getSession().isLoginedDoctor() ) {
                            System.out.println("로그아웃 후 이용해주세요.");
                            continue;
                        }
                        doLogin();
                        break;
                    case 2:
                        if (!Container.getSession().isLoginedDoctor()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        doLogout();
                        break;
                    case 3:
                        if (!Container.getSession().isLoginedDoctor()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        doUpdate();
                        break;
                    case 4:
                        if (!Container.getSession().isLoginedDoctor()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        doConsultation();
                        break;
                    case 5:
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        App.start();
                    default:
                        System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");
                        break;
                }
                break;
            }else {
            System.out.println("명령어가 올바르지 않습니다.");
        }
        }


    }

    private void doConsultation() {
        Doctor doctor = session.getLoginedDoctor();

        List<Reservation> forPrintReservations = ReservationService.getforPrintReservation(doctor.getId());
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("                    예약 정보 확인                     ");
        System.out.println("═════════════════════════════════════════════════════");

        if (forPrintReservations.isEmpty()) {
            System.out.println("예약된 정보가 없습니다.");
            System.out.println("의사 페이지로 되돌아가는 중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 스레드가 중단되었을 때 발생하는 예외 처리
                e.printStackTrace();
            }

            doAction("doctor", "page");
        } else {
            System.out.println("[예약 목록]");
            for (Reservation reservation : forPrintReservations) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String formattedTime = sdf.format(reservation.doctor_time);
                String dptName = DoctorService.getDptName(doctor.dpt_id);
                String patientName = MemberService.getMemberName(reservation.patient_id);

                System.out.println("** 예약 번호 : " + reservation.getId() + " **");

                System.out.println("진료과: " + dptName);
                System.out.println("의사 이름: " + doctor.name);
                System.out.println("예약자 이름: " + patientName); //
                System.out.println("예약 시간: " + formattedTime);
                System.out.println("환자 증상: " + reservation.rh);
                System.out.println();
            }
        }
        // 예약 페이지 푸터 출력
        System.out.println("═════════════════════════════════════════════════════");
        List<Integer> reservationNumCheck = new ArrayList<>();
        for (Reservation reservation : forPrintReservations) {

            reservationNumCheck.add(reservation.getId());
        }
        while (true) {
            System.out.print("진료가 완료된 예약 번호를 입력해주세요(뒤로가기 0번) : ");
            int completeConsultation = sc.nextInt();

            if(!(completeConsultation == 0)){
                if(!reservationNumCheck.contains(completeConsultation)){
                    System.out.println("예약 번호를 다시 한번 확인해 주세요");
                    continue;
                }else{
                    ReservationService.cancelReservation(completeConsultation);
                    System.out.println("진료가 완료되었습니다.");
                }
            }
            break;
        }

        System.out.println("의사 페이지로 되돌아가는 중...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 스레드가 중단되었을 때 발생하는 예외 처리
            e.printStackTrace();
        }

        doAction("doctor", "page");
    }

    private void doUpdate() {

        while (true) {
            boolean checkpoint = false;

            Doctor doctor = session.getLoginedDoctor();
            PrintInfo.doctor(doctor);
            String doctorIngochange = "";
            while (true) {
                System.out.print("수정 하실 정보를 입력해주세요 : ");
                doctorIngochange = sc.nextLine().trim();
                try {
                    int doctorInfo = Integer.parseInt(doctorIngochange);
                    if (!(doctorInfo == 1 || doctorInfo == 3)) {
                        System.out.println("수정 하실 정보를 정확히 입력해주세요(의사 번호는 수정 불가)");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    if (!(doctorIngochange.equals("이름") || doctorIngochange.equals("비밀번호"))) {
                        System.out.println("올바른 정보를 입력해주세요.");
                        continue;
                    }
                    break;
                }
            }
            int id = session.getLoginedDoctor().id;
            switch (doctorIngochange) {
                case "1":
                case "이름":
                    ChangeInfo.changeDoctorName(id);
                    break;
                case "2":
                case "비밀번호":
                    ChangeInfo.changeDoctorLoginPw(id);
                    break;
                default:
                    System.out.println("없는 번호 입니다.");
                    break;
            }
            System.out.println("정보가 수정되었습니다.");
            PrintInfo.doctor(doctor);
            while (true) {
                System.out.print("더 바꾸실 정보가 있습니까?(네 or 아니요) : ");
                String answer = sc.nextLine().trim();

                if (answer.equals("네")) {
                    checkpoint = true;
                    break;
                } else if (answer.equals("아니요")) {
                    break;
                } else {
                    System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
                }
            }

            if (checkpoint) {
                continue;
            }

            break;
        }

        System.out.println("의사 페이지로 되돌아가는 중...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 스레드가 중단되었을 때 발생하는 예외 처리
            e.printStackTrace();
        }
        doAction("doctor", "page");

    }

    public void doLogin() {
        System.out.printf("의사 번호 : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.printf("로그인 비번 : ");
        String loginPw = sc.nextLine();

        Doctor doctor = doctorService.getDoctorByLoginId(id);

        if (doctor == null) {
            System.out.println("해당회원은 존재하지 않습니다.");
            return;
        }

        if (!doctor.loginPw.equals(loginPw)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        session.setLoginedDoctor(doctor);
        Doctor loginedDoctor = session.getLoginedDoctor();

        System.out.printf("로그인 성공! %s님 환영합니다!\n", loginedDoctor.name);
        doAction("doctor", "page");
    }

    private void doLogout() {
        session.setLoginedDoctor(null);
        System.out.println("로그아웃 되었습니다.");
        App.start();
    }

}

