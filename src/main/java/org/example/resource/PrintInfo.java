package org.example.resource;

import org.example.dto.Admin;
import org.example.dto.Doctor;
import org.example.dto.Member;

public class PrintInfo {
    public static void member(Member member){
        String hiddenPhone = member.phone.substring(0, 8) + "-****";
        String hiddenRrn = member.rrn.substring(0, 6) + "-******";
        String hiddenloginPw = "*".repeat(member.loginPw.length());
        System.out.println("                       회원 정보                      ");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.printf("               1.이름 : %s                           \n", member.name);
        System.out.printf("               2.나이 : %d                           \n", member.age);
        System.out.printf("               3.전화번호 : %s                       \n", hiddenPhone);
        System.out.printf("               4.주민번호 : %s       (변경 불가)        \n", hiddenRrn);
        System.out.printf("               5.신장 : %.1f                         \n", member.height);
        System.out.printf("               6.몸무게 : %.1f                        \n", member.weight);
        System.out.printf("               7.기저질환 : %s                       \n", member.ud);
        System.out.printf("               8.아이디 : %s                          \n", member.loginId);
        System.out.printf("               9.비밀번호 : %s                        \n", hiddenloginPw);
        System.out.println("═════════════════════════════════════════════════════");
    }

    public static  void doctor(Doctor doctor){
        String hiddenloginPw = "*".repeat(doctor.loginPw.length());
        System.out.println("                       의사 정보                       ");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.printf("                     1.이름 : %s        \n", doctor.name);
        System.out.printf("                     2.의사 번호 : %d           (수정 불가)\n", doctor.id);
        System.out.printf("                     3.비밀번호 : %s   \n", hiddenloginPw);
        System.out.println("═════════════════════════════════════════════════════");
    }

    public static void admin(Admin admin){
        String hiddenloginPw = "*".repeat(admin.loginPw.length());
        System.out.println("                       회원 정보                      ");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.printf("               1.이름 : %s                           \n", admin.name);
        System.out.printf("               2.아이디 : %s                          \n", admin.loginId);
        System.out.printf("               3.비밀번호 : %s                        \n", hiddenloginPw);
        System.out.println("═════════════════════════════════════════════════════");
    }
}

