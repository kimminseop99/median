package org.example.controller;

import org.example.dto.Doctor;
import org.example.dto.Member;

// 현재 사용자가 이용중인 정보.
// 이 안의 정보는 사용자가 프로그램을 사용 할 때 동안은 계속 유지됨.
public class Session {
    private Member loginedMember;
    private Doctor loginedDoctor;
    public Member getLoginedMember() {
        return loginedMember;
    }

    public void setLoginedMember(Member loginedMember) {
        this.loginedMember = loginedMember;
    }


    public boolean isLogined() {
        return loginedMember != null;
    }

    public Doctor getLoginedDoctor() {
        return loginedDoctor;
    }

    public void setLoginedDoctor(Doctor loginedDoctor) {
        this.loginedDoctor = loginedDoctor;
    }


    public boolean isLoginedDoctor() {
        return loginedDoctor != null;
    }
}