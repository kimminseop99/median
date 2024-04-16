package org.example.controller;

import org.example.dto.Admin;
import org.example.dto.Board;
import org.example.dto.Doctor;
import org.example.dto.Member;

// 현재 사용자가 이용중인 정보.
// 이 안의 정보는 사용자가 프로그램을 사용 할 때 동안은 계속 유지됨.
public class Session {
    private Member loginedMember;
    private Doctor loginedDoctor;

    private Admin loginedAdmin;

    private Board currentBoard;

    // 회원 정보
    public Member getLoginedMember() {
        return loginedMember;
    }
    public void setLoginedMember(Member loginedMember) {
        this.loginedMember = loginedMember;
    }
    public boolean isLogined() {
        return loginedMember != null;
    }


    // 의사 정보
    public Doctor getLoginedDoctor() {
        return loginedDoctor;
    }
    public void setLoginedDoctor(Doctor loginedDoctor) {
        this.loginedDoctor = loginedDoctor;
    }
    public boolean isLoginedDoctor() {
        return loginedDoctor != null;
    }


    //관리자 정보
    public Admin getLoginedAdmin() {
        return loginedAdmin;
    }
    public void setLoginedAdmin(Admin loginedAdmin) {
        this.loginedAdmin = loginedAdmin;
    }
    public boolean isLoginedAdmin() {
        return loginedAdmin != null;
    }

    public Board getCurrentBoard() {
        return currentBoard;
    }

    public void setCurrentBoard(Board currentBoard) {
        this.currentBoard = currentBoard;
    }
}