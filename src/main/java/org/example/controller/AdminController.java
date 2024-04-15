package org.example.controller;

import org.example.App;
import org.example.container.Container;
import org.example.dto.Admin;
import org.example.dto.Member;
import org.example.dto.Reservation;
import org.example.resource.ChangeInfo;
import org.example.service.AdminService;
import org.example.service.DoctorService;
import org.example.service.MemberService;

import java.text.SimpleDateFormat;
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

    public void doAction(String cmd, String actionMethodName) {
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
                        if (Container.getSession().isLoginedAdmin() ) {
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
        }
        else {
            System.out.println("명령어가 올바르지 않습니다.");
        }

    }

    private void manageArticle() {
    }

    private void manageReservation() {
    }

    private void manageDoctor() {
    }

    private void manageMember() {
        List<Member> memberList = MemberService.getAllMember();
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("                    회원 정보 확인                     ");
        System.out.println("═════════════════════════════════════════════════════");
        if (memberList.isEmpty()) {
            System.out.println("등록 된 없습니다.");
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

        System.out.print("회원 추가는 1번 삭제는 2번을 입력해주세요(뒤로가기 0번) : ");
        int cmd = sc.nextInt();
        if(cmd == 1){

        }
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

            if(checkpoint){
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
        App.start();
    }

    private void doLogout() {
        session.setLoginedAdmin(null);
        System.out.println("로그아웃 되었습니다.");
        App.start();
    }


}