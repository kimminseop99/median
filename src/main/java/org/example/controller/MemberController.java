package org.example.controller;

import org.example.App;
import org.example.Main;
import org.example.container.Container;
import org.example.dto.Member;
import org.example.resource.ChangeInfo;
import org.example.service.MemberService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MemberController extends Controller {
    private Scanner sc;
    private MemberService memberService;
    private Session session;

    public MemberController() {
        sc = Container.getScanner();
        session = Container.getSession();
        memberService = Container.memberService;
    }

    public void doAction(String cmd, String actionMethodName) {
        switch (actionMethodName) {
            case "join":
                doJoin();
                break;
            case "login":
                doLogin();
                break;
            case "logout":
                doLogout();
                break;
            case "update":
                doUpdate();
                break;
            default:
                System.out.println("존재하지 않는 명령어 입니다.");
                break;
        }

    }

    private void doUpdate() {

        while (true) {
            boolean checkpoint = false;

            Member member = session.getLoginedMember();
            String hiddenPhone = member.phone.substring(0, 8) + "-****";
            String hiddenRrn = member.rrn.substring(0, 6) + "-******";
            String hiddenloginPw = "*".repeat(member.loginPw.length());
            System.out.println("                       회원 정보                      ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.printf("               1.이름 : %s                           \n", member.name);
            System.out.printf("               2.나이 : %d                           \n", member.age);
            System.out.printf("               3.전화번호 : %s                       \n", hiddenPhone);
            System.out.printf("               4.주민번호 : %s(변경 불가)              \n", hiddenRrn);
            System.out.printf("               5.신장 : %.1f                         \n", member.height);
            System.out.printf("               6.몸무게 : %.1f                        \n", member.weight);
            System.out.printf("               7.기저질환 : %s                       \n", member.ud);
            System.out.printf("               8.아이디 : %s                          \n", member.loginId);
            System.out.printf("               9.비밀번호 : %s                        \n", hiddenloginPw);
            System.out.println("═════════════════════════════════════════════════════");
            String memberInfoChange = "";
            while (true) {
                System.out.print("수정 하실 정보를 입력해주세요 : ");
                memberInfoChange = sc.nextLine().trim();
                try {
                    int memberInfo = Integer.parseInt(memberInfoChange);
                    if (memberInfo < 1 || memberInfo > 9 || memberInfo == 4) {
                        System.out.println("수정 하실 정보를 정확히 입력해주세요(주민 번호는 수정 불가)");
                        continue;
                    }
                    break;
                } catch (NumberFormatException e) {
                    if (!(memberInfoChange.equals("이름") || memberInfoChange.equals("나이") ||
                            memberInfoChange.equals("전화번호") || memberInfoChange.equals("신장") ||
                            memberInfoChange.equals("몸무게") || memberInfoChange.equals("기저질환") ||
                            memberInfoChange.equals("아이디") || memberInfoChange.equals("비밀번호"))) {
                        System.out.println("올바른 정보를 입력해주세요.");
                        continue;
                    }
                    break;
                }
            }
            int id = session.getLoginedMember().id;
            switch (memberInfoChange) {
                case "1":
                case "이름":
                    ChangeInfo.changeName(id);
                    break;
                case "2":
                case "나이":
                    ChangeInfo.changeAge(id);
                    break;
                case "3":
                case "전화번호":
                    ChangeInfo.changePhone(id);
                    break;
                case "5":
                case "신장":
                    ChangeInfo.changeHeight(id);
                    break;
                case "6":
                case "몸무게":
                    ChangeInfo.changeWeight(id);
                    break;
                case "7":
                case "기저질환":
                    ChangeInfo.changeUd(id);
                    break;
                case "8":
                case "아이디":
                    ChangeInfo.changeLoginId(id);
                    break;
                case "9":
                case "비밀번호":
                    ChangeInfo.changeLoginPw(id);
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

    public void doJoin() {
        String loginId = null;

        while (true) {
            System.out.print("사용하실 아이디 : ");
            loginId = sc.nextLine();

            if (!isJoinableLoginId(loginId)) {
                System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
                continue;
            }

            break;
        }

        String loginPw = null;
        String loginPwConfirm = null;

        while (true) {
            System.out.print("사용하실 비밀번호 : ");
            loginPw = sc.nextLine();
            System.out.print("비밀번호 확인 : ");
            loginPwConfirm = sc.nextLine();

            if (!loginPw.equals(loginPwConfirm)) {
                System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                continue;
            }

            break;
        }

        int age = 0;
        while (true) {
            System.out.print("나이 : ");
            age = sc.nextInt();

            if (age < 1) {
                System.out.println("나이를 다시한번 확인해 주세요.");
                continue;
            }
            break;
        }


        sc.nextLine();
        String phone = "";
        while (true) {
            System.out.print("핸드폰 번호 : ");
            phone = sc.nextLine();

            if (phone.isEmpty()) {
                continue;
            }
            if (phone.length() > 13) {
                System.out.println("전화번호는 13자리 이하여야 합니다.");
            } else {
                break;
            }
        }

        String rrn = "";
        while (true) {
            System.out.print("주민 번호 : ");
            rrn = sc.nextLine();

            if (rrn.isEmpty()) {
                continue;
            }
            if (rrn.length() != 14) {
                System.out.println("주민번호는 14자리여야 합니다.");
            } else {
                break;
            }
        }

        System.out.print("신장 : ");
        double height = sc.nextDouble();

        System.out.print("체중 : ");
        double weight = sc.nextDouble();
        sc.nextLine();

        System.out.printf("기저질환 : ");
        String ud = sc.nextLine();

        System.out.printf("이름 : ");
        String name = sc.nextLine();


        memberService.join(name, age, phone, rrn, height, weight, ud, loginId, loginPw);

        System.out.printf("[%s]님! 회원가입이 완료되었습니다! 환영합니다^^~\n", name);
    }

    public void doLogin() {
        System.out.printf("로그인 아이디 : ");
        String loginId = sc.nextLine();
        System.out.printf("로그인 비번 : ");
        String loginPw = sc.nextLine();

        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            System.out.println("해당회원은 존재하지 않습니다.");
            return;
        }

        if (!member.loginPw.equals(loginPw)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }

        session.setLoginedMember(member);
        Member loginedMember = session.getLoginedMember();

        System.out.printf("로그인 성공! %s님 환영합니다!\n", loginedMember.name);
    }

    private void doLogout() {
        session.setLoginedMember(null);
        System.out.println("로그아웃 되었습니다.");
    }

    private boolean isJoinableLoginId(String loginId) {
        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            return true;
        }

        return false;
    }
}