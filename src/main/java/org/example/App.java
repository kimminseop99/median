package org.example;

import org.example.container.Container;
import org.example.controller.*;
import org.example.db.DBConnection;

public class App {
    public App() {
        DBConnection.DB_NAME = "median";
        DBConnection.DB_USER = "sbsst";
        DBConnection.DB_PASSWORD = "sbs123414";
        DBConnection.DB_PORT = 3306;

        Container.getDBConnection().connect();

    }

    public static void start() {

        System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                    메인                                    ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ 번호 │ 명령어                                                               ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║  1  │ 회원 페이지 : member page                                             ║");
        System.out.println("║  2  │ 의사 페이지 : doctor page                                             ║");
        System.out.println("║  3  │ 예약 페이지 (로그인 후 이용가능) : reservation page                      ║");
        System.out.println("║  4  │ 게시판 페이지 : article page                                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════");


        MemberController memberController = new MemberController();
        DoctorController doctorController = new DoctorController();
        ArticleController articleController = new ArticleController();
        ReservationController reservationController = new ReservationController();

        while (true) {
            System.out.println("[메인]");
            System.out.printf("명령어) ");
            String cmd = Container.getScanner().nextLine();
            cmd = cmd.trim();

            if (cmd.isEmpty()) {
                continue;
            }


            if (cmd.equals("exit")) {
                System.out.println("프로그램을 종료합니다.");
                System.exit(0);
            }


            String[] cmdBits = cmd.split(" ");

            if (cmdBits.length == 1) {
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            String controllerName = cmd;


            String actionMethodName = cmdBits[1];

            Controller controller = null;

            if (controllerName.equals("article page") || controllerName.equals("4")) {
                controller = articleController;
            } else if (controllerName.equals("member page") || controllerName.equals("1")) {
                if (Container.getSession().isLoginedDoctor()) {
                    System.out.println("의사 계정 로그아웃 후 이용해주세요.");
                    continue;
                }
                controller = memberController;
            } else if (controllerName.equals("reservation page") || controllerName.equals("3")) {
                if (Container.getSession().isLoginedDoctor()) {
                    System.out.println("이용할 수 없습니다.");
                    continue;
                }
                controller = reservationController;
            } else if (controllerName.equals("doctor page") || controllerName.equals("2")) {
                if (Container.getSession().isLogined()) {
                    System.out.println("회원 계정 로그아웃 후 이용해주세요.");
                    continue;
                }
                controller = doctorController;
            } else {
                System.out.println("존재하지 않는 명령어입니다.");
                continue;
            }


            controller.doAction(cmd, actionMethodName);
        }

    }
}