package org.example.controller;

import org.example.container.Container;
import org.example.dto.Member;
import org.example.service.MemberService;
import org.example.service.ReservationService;

import java.util.Scanner;

public class ReservationController extends Controller {

    private Scanner sc;
    private ReservationService reservationService;
    private Session session;

    public ReservationController() {
        sc = Container.getScanner();
        session = Container.getSession();
        reservationService = Container.reservaitonService;
    }

    public void doAction(String cmd, String actionMethodName) {
        if (actionMethodName.equals("page")) {
            doCase();
        } else {
            System.out.println("존재하지 않는 명령어 입니다.");
        }

    }

    public void doCase() {

        while(true) {
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

        }


    }

}