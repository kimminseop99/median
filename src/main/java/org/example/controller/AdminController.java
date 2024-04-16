package org.example.controller;

import org.example.App;
import org.example.container.Container;
import org.example.dao.MemberDao;
import org.example.dto.Admin;
import org.example.dto.Doctor;
import org.example.dto.Member;
import org.example.dto.Reservation;
import org.example.resource.ChangeInfo;
import org.example.service.AdminService;
import org.example.service.DoctorService;
import org.example.service.MemberService;
import org.example.service.ReservationService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminController extends Controller {
    private Scanner sc;
    private AdminService adminService;
    private Session session;


    public AdminController() {
        sc = Container.getScanner();
        session = Container.getSession();
        adminService = Container.adminService;
    }

    public void doAction(String patientNum, String actionMethodName) {
        if (actionMethodName.equals("page")) {
            System.out.println("                    관리자 페이지                      ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.println("|                   1. 로그인                         |");
            System.out.println("|                   2. 로그아웃                       |");
            System.out.println("|                   3. 회원 관리                      |");
            System.out.println("|                   4. 의사 관리                      |");
            System.out.println("|                   5. 예약 관리                      |");
            System.out.println("|                   6. 게시판 관리                     |");
            System.out.println("|                   7. 관리자 정보 변경                |");
            System.out.println("|                   8. 뒤로 가기                      |");
            System.out.println("═════════════════════════════════════════════════════");
            while (true) {
                System.out.print("번호를 선택해 주세요: ");
                int num = sc.nextInt();
                sc.nextLine();

                switch (num) {
                    case 1:
                        if (Container.getSession().isLoginedAdmin()) {
                            System.out.println("로그아웃 후 이용해주세요.");
                            continue;
                        }
                        doLogin();
                        break;
                    case 2:
                        if (!Container.getSession().isLoginedAdmin()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        doLogout();
                        break;
                    case 3:
                        if (!Container.getSession().isLoginedAdmin()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        manageMember();
                        break;
                    case 4:
                        if (!Container.getSession().isLoginedAdmin()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        manageDoctor();
                        break;
                    case 5:
                        if (!Container.getSession().isLoginedAdmin()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        manageReservation();
                        break;
                    case 6:
                        if (!Container.getSession().isLoginedAdmin()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        manageArticle();
                        break;
                    case 7:
                        if (!Container.getSession().isLoginedAdmin()) {
                            System.out.println("로그인 후 이용해주세요.");
                            continue;
                        }
                        doUpdate();
                        break;
                    case 8:
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        App.start();
                    default:
                        System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");
                        break;
                }
                break;
            }
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }

    }

    private void manageArticle() {
    }

    private void manageReservation() {
        List<Reservation> reservationList = ReservationService.getReservations();
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("                    예약 내용 확인                     ");
        System.out.println("═════════════════════════════════════════════════════");

        if (reservationList.isEmpty()) {
            System.out.println("예약된 정보가 없습니다.");
            System.out.println("관리자 페이지로 되돌아가는 중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 스레드가 중단되었을 때 발생하는 예외 처리
                e.printStackTrace();
            }

            doAction("admin", "page");
        } else {
            System.out.println("[예약 목록]");
            for (Reservation reservation : reservationList) {
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                String formattedTime = sdf.format(reservation.doctor_time);
                String dptName = DoctorService.getDptName(reservation.dpt_id);
                String patientName = MemberService.getMemberName(reservation.patient_id);
                String doctorName = DoctorService.getDoctorName(reservation.doctor_id);

                System.out.println("** 예약 번호 : " + reservation.getId() + " **");

                System.out.println("진료과: " + dptName);
                System.out.println("의사 이름: " + doctorName);
                System.out.println("예약자 이름: " + patientName);
                System.out.println("예약 시간: " + formattedTime);
                System.out.println("환자 증상: " + reservation.rh);
                System.out.println();
            }
        }
        // 예약 페이지 푸터 출력
        System.out.println("═════════════════════════════════════════════════════");


            List<Integer> reservationNumCheck = new ArrayList<>();
            for (Reservation reservation : reservationList) {

                reservationNumCheck.add(reservation.getId());
            }
            while (true){
                System.out.print("삭제 할 예약 번호를 입력해주세요(뒤로가기 0번) : ");
                int reservationNum = sc.nextInt();
                if(!(reservationNum == 0)){
                    if(!reservationNumCheck.contains(reservationNum)){
                        System.out.println("예약 번호를 다시 한번 확인해 주세요");
                        continue;
                    }else{
                        ReservationService.cancelReservation(reservationNum);
                        System.out.println("예약이 삭제되었습니다.");
                    }
                }

            break;
        }
        System.out.println("관리자 페이지로 되돌아가는 중...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 스레드가 중단되었을 때 발생하는 예외 처리
            e.printStackTrace();
        }

        doAction("admin", "page");

    }

    private void manageDoctor() {
        List<Doctor> doctorList = DoctorService.getAllDoctor();
        List<Integer> doctorsId = DoctorService.getAllDoctorId();
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("                    의사 정보 확인                     ");
        System.out.println("═════════════════════════════════════════════════════");
        if (doctorList.isEmpty()) {
            System.out.println("등록 된 의사가 없습니다.");
            System.out.println("관리자 페이지로 되돌아가는 중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 스레드가 중단되었을 때 발생하는 예외 처리
                e.printStackTrace();
            }

            doAction("admin", "page");
        } else {
            System.out.println("[의사 목록]");
            int index = 0;
            for (Doctor doctor : doctorList) {
                Integer id = doctorsId.get(index);
                String dptName = DoctorService.getDptName(doctor.dpt_id);
                System.out.println("** 의사 번호 : " + id + " **");

                System.out.println("이름: " + doctor.name);
                System.out.println("진료과: " + dptName);
                System.out.println("비밀번호: " + doctor.loginPw);
                System.out.println();
                index++;
            }
        }
        // 예약 페이지 푸터 출력
        System.out.println("═════════════════════════════════════════════════════");

        while (true) {
            System.out.print("의사 추가는 1번 삭제는 2번을 입력해주세요(뒤로가기 0번) : ");
            int checkCmd = sc.nextInt();
            if (checkCmd == 1) {
                doCreateDoctor();
            } else if (checkCmd == 2) {
                doDeleteDoctor();
            } else if(checkCmd == 0){
                System.out.println("관리자 페이지로 되돌아가는 중...");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // 스레드가 중단되었을 때 발생하는 예외 처리
                    e.printStackTrace();
                }

                doAction("admin", "page");
            }
            else {
                continue;
            }
            break;
        }

    }

    private void doDeleteDoctor() {
        List<Integer> doctorsId = DoctorService.getAllDoctorId();
        while (true) {
            System.out.print("삭제 할 의사의 번호를 입력해주세요(뒤로가기 0번) : ");
            int doctorNum = sc.nextInt();
            if (doctorNum == 0) {
                doAction("admin", "page");
                return;
            } else {
                boolean doctorFound = false;

                for (Integer doctorId : doctorsId) {

                    if (doctorNum == doctorId) {
                        DoctorService.deleteDoctorTime(doctorNum);
                        DoctorService.deleteDoctor(doctorNum);
                        System.out.println(doctorNum + "번 의사가 삭제되었습니다.");
                        doctorFound = true;
                        break;
                    }

                }
                if (!doctorFound) {
                    System.out.println("해당 번호의 의사가 존재하지 않습니다.");
                    continue;
                }
                break;

            }
        }
        doAction("admin", "page");
    }

    private void doCreateDoctor() {
        sc.nextLine();
        System.out.print("이름 : ");
        String name = sc.nextLine();



        System.out.print("진료과 번호를 입력해주세요 : ");
        int dpt_id = sc.nextInt();

        String loginPw = null;
        String loginPwConfirm = null;


        while (true) {
            sc.nextLine();
            System.out.print("사용하실 비밀번호 : ");
            loginPw = sc.nextLine();

            System.out.print("비밀번호 확인 : ");
            loginPwConfirm = sc.nextLine();

            if (!loginPw.equals(loginPwConfirm)) {
                System.out.println("비밀번호가 일치하지 않습니다 다시 입력해주세요.");
                continue;
            }

            break;
        }

        DoctorService.join(name, dpt_id, loginPw);
        int doctor_id = DoctorService.getJoinDoctorId();
        DoctorService.createDoctorTime(doctor_id);

        System.out.printf("[%s]의사의 회원가입이 완료되었습니다!\n", name);
        doAction("admin", "page");
    }

    private void manageMember() {
        List<Member> memberList = MemberService.getAllMember();
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("                    회원 정보 확인                     ");
        System.out.println("═════════════════════════════════════════════════════");
        if (memberList.isEmpty()) {
            System.out.println("등록 된 회원이 없습니다.");
            System.out.println("관리자 페이지로 되돌아가는 중...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 스레드가 중단되었을 때 발생하는 예외 처리
                e.printStackTrace();
            }

            doAction("admin", "page");
        } else {
            System.out.println("[회원 목록]");
            for (Member member : memberList) {

                System.out.println("** 번호 : " + member.id + " **");

                System.out.println("이름: " + member.name);
                System.out.println("가입날짜: " + member.regDate);
                System.out.println("나이: " + member.age);
                System.out.println("핸드폰 번호: " + member.phone);
                System.out.println("주민번호: " + member.rrn);
                System.out.println("신장: " + member.height);
                System.out.println("몸무게: " + member.weight);
                System.out.println("기저질환: " + member.ud);
                System.out.println("아이디: " + member.loginId);
                System.out.println("비밀번호: " + member.loginPw);
                System.out.println();
            }
        }
        // 예약 페이지 푸터 출력
        System.out.println("═════════════════════════════════════════════════════");

        boolean checkPatientNum = false;
        while (true) {
            System.out.print("삭제 할 회원의 번호를 입력해주세요(뒤로가기 0번) : ");
            int patientNum = sc.nextInt();
            if (patientNum == 0) {
                doAction("admin", "page");
                return;
            } else {
                for (Member member : memberList) {
                    if (member.id == patientNum) {
                       MemberService.deletePatient(patientNum);
                        System.out.println(patientNum + "번 회원이 삭제되었습니다.");
                        checkPatientNum = true;
                    }
                }
                if (!checkPatientNum) {
                    System.out.println("해당 번호의 회원이 존재하지 않습니다.");
                    continue;
                }
                break;

            }
        }
        doAction("admin", "page");
    }

    private void doUpdate() {

        while (true) {
            boolean checkpoint = false;

            Admin admin = session.getLoginedAdmin();
            String hiddenloginPw = "*".repeat(admin.loginPw.length());
            System.out.println("                       회원 정보                      ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.printf("               1.이름 : %s                           \n", admin.name);
            System.out.printf("               2.아이디 : %s                          \n", admin.loginId);
            System.out.printf("               3.비밀번호 : %s                        \n", hiddenloginPw);
            System.out.println("═════════════════════════════════════════════════════");
            String adminInfoChange = "";
            while (true) {
                System.out.print("수정 하실 정보를 입력해주세요 : ");
                adminInfoChange = sc.nextLine().trim();
                try {
                    int adminInfo = Integer.parseInt(adminInfoChange);
                    if (adminInfo < 1 || adminInfo > 3) {
                        System.out.println("수정 하실 정보를 정확히 입력해주세요");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    if (!(adminInfoChange.equals("이름") || adminInfoChange.equals("아이디") ||
                            adminInfoChange.equals("비밀번호"))) {
                        System.out.println("올바른 정보를 입력해주세요.");
                        continue;
                    }
                    break;
                }
            }
            int id = session.getLoginedAdmin().id;
            switch (adminInfoChange) {
                case "1":
                case "이름":
                    ChangeInfo.changeAdminName(id);
                    break;
                case "2":
                case "아이디":
                    ChangeInfo.changeLoginAdminId(id);
                    break;
                case "3":
                case "비밀번호":
                    ChangeInfo.changeLoginAdminPw(id);
                    break;
                default:
                    System.out.println("없는 번호 입니다.");
                    break;
            }
            System.out.println("정보가 수정되었습니다.");

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

        System.out.println("메인 페이지로 되돌아가는 중...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // 스레드가 중단되었을 때 발생하는 예외 처리
            e.printStackTrace();
        }
        App.start();

    }

    public void doLogin() {
        System.out.printf("로그인 아이디 : ");
        String loginId = sc.nextLine();
        System.out.printf("로그인 비번 : ");
        String loginPw = sc.nextLine();

        Admin admin = adminService.getAdminByLoginId(loginId);

        if (admin == null) {
            System.out.println("해당회원은 존재하지 않습니다.");
            return;
        }

        if (!admin.loginPw.equals(loginPw)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        session.setLoginedAdmin(admin);
        Admin loginedAdmin = session.getLoginedAdmin();

        System.out.printf("로그인 성공! %s님 환영합니다!\n", loginedAdmin.name);
        doAction("admin", "page");
    }

    private void doLogout() {
        session.setLoginedAdmin(null);
        System.out.println("로그아웃 되었습니다.");
        App.start();
    }


}