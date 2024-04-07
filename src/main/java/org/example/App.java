package org.example;

import org.example.container.Container;
import org.example.controller.ArticleController;
import org.example.controller.Controller;
import org.example.controller.MemberController;
import org.example.controller.ReservationController;
import org.example.db.DBConnection;

public class App {
    public App() {
        DBConnection.DB_NAME = "sbs_proj";
        DBConnection.DB_USER = "sbsst";
        DBConnection.DB_PASSWORD = "sbs123414";
        DBConnection.DB_PORT = 3306;

        Container.getDBConnection().connect();

        // 현재 게시판을 1번 게시판으로 선택
        Container.getSession().setCurrentBoard(Container.articleService.getBoard(2));
    }
    public void start() {

        System.out.println("╔═══════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                                명령어 모음                                  ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║ 번호 │ 명령어                                                               ║");
        System.out.println("╟───────────────────────────────────────────────────────────────────────────╢");
        System.out.println("║  1  │ 회원가입 : member join                                                ║");
        System.out.println("║  2  │ 로그인 : member login                                                ║");
        System.out.println("║  3  │ 로그아웃 (로그인 후 이용가능) : member logout                            ║");
        System.out.println("║  4  │ 마이페이지 (로그인 후 이용가능) : member page                            ║");
        System.out.println("║  5  │ 예약 페이지 (로그인 후 이용가능) : reservation page                      ║");
        System.out.println("║  6  │ 게시판 페이지 : article page                                          ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════");


        MemberController memberController = new MemberController();
        ArticleController articleController = new ArticleController();
        ReservationController reservationController = new ReservationController();
        while ( true ) {
            System.out.printf("명령어) ");
            String cmd = Container.getScanner().nextLine();
            cmd = cmd.trim();

            if (cmd.isEmpty()) {
                continue;
            }

            if ( cmd.equals("exit") ) {
                break;
            }

            String[] cmdBits = cmd.split(" "); // article write / member join

            if ( cmdBits.length == 1 ) {
                System.out.println("존재하지 않는 명령어 입니다.");
                continue;
            }

            String controllerName = cmdBits[0]; // article / member
            String actionMethodName = cmdBits[1]; // write / join

            Controller controller = null;

            if ( controllerName.equals("article") ) {
                controller = articleController;
            }
            else if ( controllerName.equals("member") ) {
                controller = memberController;
            }
            else if ( controllerName.equals("reservation") ) {
                controller =  reservationController;
            }
            else {
                System.out.println("존재하지 않는 명령어입니다.");
                continue;
            }

            String actionName = controllerName + "/" + actionMethodName;

            switch ( actionName ) {
                case "article/write":
                case "member/page":
                case "reservation/page":
                case "member/logout":
                    if (!Container.getSession().isLogined()) {
                        System.out.println("로그인 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            switch ( actionName ) {
                case "member/login":
                case "member/join":
                    if (Container.getSession().isLogined() ) {
                        System.out.println("로그아웃 후 이용해주세요.");
                        continue;
                    }
                    break;
            }

            controller.doAction(cmd, actionMethodName);
        }

        Container.getDBConnection().close();
        Container.getScanner().close();

        System.out.println("== 프로그램 끝 ==");
    }
}