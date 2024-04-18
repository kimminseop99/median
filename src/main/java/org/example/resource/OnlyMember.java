package org.example.resource;

import org.example.container.Container;
import org.example.controller.Session;
import org.example.dto.Board;

public class OnlyMember {
    private Session session;

    public OnlyMember(){
        session = Container.getSession();
    }

    public boolean memberControl(){
        Board board = session.getCurrentBoard();
        if (session.isLoginedDoctor() || session.isLoginedAdmin()) {
            System.out.println("사용 하실 수 없는 시스템 입니다.");
            return false;
        }
        if (board.getName().equals("공지")) {
            System.out.println("공지 페이지는 목록 확인만 가능합니다. ");
            return false;
        }
        if (!session.isLogined()) {
            System.out.println("회원 로그인 후 이용 가능합니다.");
            return false;
        }
        return true;
    }
}
