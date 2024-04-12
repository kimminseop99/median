package org.example.resource;

import org.example.dto.Member;
import org.example.service.MemberService;

import java.util.Scanner;

import static org.example.container.Container.memberService;
import static org.example.container.Container.session;

public class ChangeInfo {
    static Scanner sc = new Scanner(System.in);
    public static void changeName(int id){
        System.out.print("바꾸실 이름을 적어주세요 : ");
        String name = sc.nextLine();
        MemberService.StringUpdate("name",name,id);
    }

    public static void changeAge(int id){
        int age = 0;
        while (true) {
            System.out.print("바꾸실 나이를 적어주세요 : ");
            age = sc.nextInt();
            sc.nextLine(); // 버퍼 비우기

            if (age < 1) {
                System.out.println("나이를 다시한번 확인해 주세요.");
                continue;
            }
            break;
        }

        MemberService.IntUpdate("age", age, id);
    }

    public static void changePhone(int id){
        System.out.print("바꾸실 전화번호를 적어주세요 : ");
        String phone = sc.nextLine();
        MemberService.StringUpdate("phone", phone, id);
    }
    public static void changeHeight(int id){
        System.out.print("바꾸실 키를 적어주세요 : ");
        double height = sc.nextDouble();
        sc.nextLine();
        MemberService.DoubleUpdate("height", height, id);
    }
    public static void changeWeight(int id){
        System.out.print("바꾸실 몸무게를 적어주세요 : ");
        double weight = sc.nextDouble();
        sc.nextLine();
        MemberService.DoubleUpdate("weight", weight, id);
    }
    public static void changeUd(int id){
        System.out.print("바꾸실 기저질환을 적어주세요 : ");
        String ud = sc.nextLine();
        MemberService.StringUpdate("ud", ud, id);
    }
    public static void changeLoginId(int id){
        String loginId = null;

        while (true) {
            System.out.print("바꾸실 아이디를 적어주세요 : ");
            loginId = sc.nextLine();

            if (!isJoinableLoginId(loginId)) {
                System.out.printf("%s(은)는 이미 사용중인 아이디 입니다.\n", loginId);
                continue;
            }

            break;
        }
        MemberService.StringUpdate("loginId", loginId, id);
    }
    public static void changeLoginPw(int id){
        String loginPw = null;
        String loginPwConfirm = null;
        while (true) {
            System.out.print("아이디 : ");
            String loginId = sc.nextLine();
            String memberId = session.getLoginedMember().loginId;
            if(!loginId.equals(memberId)){
                System.out.println("아이디가 올바르지 않습니다.");
                continue;
            }
            while (true) {
                System.out.print("바꾸실 비밀번호를 적어주세요 : ");
                loginPw = sc.nextLine();
                System.out.printf("비번확인 : ");
                loginPwConfirm = sc.nextLine();

                if (!loginPw.equals(loginPwConfirm)) {
                    System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요.");
                    continue;
                }

                break;
            }
            break;
        }
        MemberService.StringUpdate("loginPw", loginPw, id);
    }

    private static boolean isJoinableLoginId(String loginId) {
        Member member = memberService.getMemberByLoginId(loginId);

        if (member == null) {
            return true;
        }

        return false;
    }
}
