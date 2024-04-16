package org.example.controller;

import org.example.App;
import org.example.container.Container;
import org.example.dto.*;
import org.example.service.ArticleService;
import org.example.service.MemberService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ArticleController extends Controller {
    private Scanner sc;

    private ArticleService articleService;
    private MemberService memberService;
    private Session session;

    public ArticleController() {
        sc = Container.getScanner();
        session = Container.getSession();
        articleService = Container.articleService;
        memberService = Container.memberService;
    }

    public void doAction(String cmd, String actionMethodName) {
        if (actionMethodName.equals("page")) {
            System.out.println("                      게시판 페이지                      ");
            System.out.println("═════════════════════════════════════════════════════");
            System.out.println("|                   1. 게시물 작성                    |");
            System.out.println("|                   2. 게시물 목록                    |");
            System.out.println("|                   3. 게시물 댓글                    |");
            System.out.println("|                   4. 게시물 수정                    |");
            System.out.println("|                   5. 게시물 삭제                    |");
            System.out.println("|                   6. 현재 게시판 확인                |");
            System.out.println("|                   7. 게시판 변경                    |");
            System.out.println("|                   8. 뒤로 가기                      |");
            System.out.println("═════════════════════════════════════════════════════");

            while (true){
            System.out.print("번호를 선택해 주세요: ");
            int num = sc.nextInt();
            sc.nextLine();
            Board board = session.getCurrentBoard();
            switch (num) {
                case 1:
                    if(Container.getSession().isLoginedDoctor()){
                        System.out.println("사용 하실 수 없는 시스템 입니다.");
                        continue;
                    }
                    if(!Container.getSession().isLogined()){
                        System.out.println("로그인 후 이용 가능합니다.");
                        continue;
                    }
                    if(board.getName().equals("공지") && !Container.getSession().isLoginedAdmin()){
                        System.out.println("관리자만이 작성할 수 있는 게시판 입니다.");
                        doAction("article","page");
                    }
                    doWrite();
                    break;
                case 2:
                    showList();
                    break;
                case 3:
                    showDetail();
                    break;
                case 4:
                    if(Container.getSession().isLoginedDoctor()){
                        System.out.println("사용 하실 수 없는 시스템 입니다.");
                        continue;
                    }
                    if(!Container.getSession().isLogined()){
                        System.out.println("로그인 후 이용 가능합니다.");
                        continue;
                    }
                    if(board.getName().equals("공지") && !Container.getSession().isLoginedAdmin()){
                        System.out.println("관리자만이 수정할 수 있는 게시판 입니다.");
                        doAction("article","page");
                    }
                    doModify();
                    break;
                case 5:
                    if(Container.getSession().isLoginedDoctor()){
                        System.out.println("사용 하실 수 없는 시스템 입니다.");
                        continue;
                    }
                    if(!Container.getSession().isLogined()){
                        System.out.println("로그인 후 이용 가능합니다.");
                        continue;
                    }
                    if(board.getName().equals("공지") && !Container.getSession().isLoginedAdmin()){
                        System.out.println("관리자만이 삭제할 수 있는 게시판 입니다.");
                        doAction("article","page");
                    }
                    doDelete();
                    break;
                case 6:
                    doCurrentBoard();
                    break;
                case 7:
                    doChangeBoard();
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
        } else {
            System.out.println("명령어가 올바르지 않습니다.");
        }

    }

    private void doChangeBoard() {
        System.out.println("                      게시판 목록                      ");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("|   1. 자유 게시판                                    |");
        System.out.println("|   2. 공지 게시판                                    |");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println();
        System.out.println("                   진료과 게시판 목록                    ");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("|   3. 간담췌외과                                    |");
        System.out.println("|   4. 신경외과                                      |");
        System.out.println("|   5. 산부인과                                      |");
        System.out.println("|   6. 흉부외과                                      |");
        System.out.println("|   7. 소아외과                                      |");
        System.out.println("|   8. 뒤로 가기                                     |");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.print("게시판 번호를 입력하세요) ");

        int boardId = checkScNum();
        Board board = articleService.getBoard(boardId);

        if ( board == null ) {
            System.out.println("해당 게시판은 존재하지 않습니다.");
        } else {
            System.out.printf("[%s 게시판]으로 변경되었습니다.\n", board.getName());
            session.setCurrentBoard(board);
        }

        doAction("article","page");
    }

    private void doCurrentBoard() {
        Board board = session.getCurrentBoard();
        System.out.printf("현재 게시판 : [%s 게시판]\n", board.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            // 스레드가 중단되었을 때 발생하는 예외 처리
            e.printStackTrace();
        }
        doAction("article","page");
    }

    public void doWrite() {

        if(Container.getSession().isLoginedAdmin()){
            System.out.printf("제목 : ");
            String title = sc.nextLine();
            System.out.printf("내용 : ");
            String body = sc.nextLine();

            int patient_id = session.getLoginedAdmin().getId();
            int boardId = session.getCurrentBoard().getId();

            int newId = articleService.write(patient_id, boardId, title, body);

            System.out.printf("%d번 게시물이 생성되었습니다.\n", newId);
            doAction("article","page");
        }else{
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        int patient_id = session.getLoginedMember().getId();
        int boardId = session.getCurrentBoard().getId();

        int newId = articleService.write(patient_id, boardId, title, body);

        System.out.printf("%d번 게시물이 생성되었습니다.\n", newId);
        doAction("article","page");
        }
    }

    public void showList() {
        System.out.print("검색하시고 싶은 글의 제목을 적어주세요 : ");
        String searchKeyword = sc.nextLine().trim();
        String boardCode = Container.getSession().getCurrentBoard().getCode();

        List<Article> forPrintArticles = articleService.getForPrintArticles(boardCode, searchKeyword);

        if (forPrintArticles.isEmpty()) {
            System.out.println("검색결과가 존재하지 않습니다.");
            doAction("article","page");
        }


        System.out.println("번호 |   작성자 | 조회 | 제목 ");
        for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
            Article article = forPrintArticles.get(i);
            Member member = memberService.getMember(article.patient_id);

            System.out.printf("%4d | %5s | %4d | %s\n", article.id, member.name, article.hit, article.title);
        }
        doAction("article","page");
    }

    private boolean articleReplyAuthorityCheck() {
        System.out.println("1) 네 / 2) 아니오");
        System.out.printf("입력) ");
        String replyCheck = sc.nextLine();

        if (replyCheck.equals("1") || replyCheck.equals("네")) {
            if (!session.isLogined()) {
                System.out.println("로그인 후 이용 가능합니다.");
                return false;
            }
        } else {
            return false;
        }

        return true;
    }

    public void showDetail() {
        System.out.print("게시물 번호를 입력하세요) ");

        int id = checkScNum();

        if (id == 0) {
            return;
        }

        Article foundArticle = articleService.getForPrintArticle(id);

        if (foundArticle == null) {
            System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
            return;
        }

        foundArticle.increaseHit();

        Member member = memberService.getMember(foundArticle.patient_id);

        System.out.println("** 번호 : " + foundArticle.id + " **");
        System.out.printf("날짜 : %s\n", foundArticle.regDate);
        System.out.printf("작성자 : %s\n", member.name);
        System.out.printf("제목 : %s\n", foundArticle.title);
        System.out.printf("내용 : %s\n", foundArticle.body);
        System.out.printf("조회 : %d\n", foundArticle.hit);
        System.out.println();

        System.out.printf("== [%d번 게시물 댓글] ==\n", id);
        articleRepliesShowList(id);

        if(Container.getSession().isLoginedDoctor()){
            doAction("article", "page");
        }

        System.out.println("댓글을 작성 하시겠습니까?");
        boolean replyCheck = articleReplyAuthorityCheck();

        if (replyCheck == false) {
            return;
        }

        if (replyCheck) {
            System.out.println("댓글을 입력 해주세요.");
            System.out.printf("입력) ");
            String replyBody = sc.nextLine();
            int patient_id = session.getLoginedMember().getId();

            articleService.replyWrite(id, patient_id, replyBody);
            System.out.println("댓글이 작성되었습니다.");

            articleRepliesShowList(id);
        }
        doAction("article","page");
    }

    private void articleRepliesShowList(int articleId) {
        List<ArticleReply> forPrintArticleReplies = articleService.getForPrintArticleReplies(articleId);

        System.out.printf("%d번 게시물 댓글\n", articleId);
        System.out.println("번호 |   작성자 | 제목 ");
        for (int i = forPrintArticleReplies.size() - 1; i >= 0; i--) {
            ArticleReply reply = forPrintArticleReplies.get(i);
            Member replyMember = memberService.getMember(reply.patient_id);

            System.out.printf("%3d | %5s | %s\n", reply.getId(), replyMember.name, reply.body);
        }
    }

    public void doModify() {
        if(Container.getSession().isLoginedAdmin()){
            System.out.print("수정할 게시물 번호를 입력하세요) ");
            int id = checkScNum();

            if (id == 0) {
                return;
            }

            Article foundArticle = articleService.getArticle(id);

            if (foundArticle == null) {
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

            Admin loginedMember = session.getLoginedAdmin();

            if (foundArticle.patient_id != loginedMember.id) {
                System.out.printf("권한이 없습니다.\n");
                return;
            }

            System.out.printf("제목 : ");
            String title = sc.nextLine();
            System.out.printf("내용 : ");
            String body = sc.nextLine();

            articleService.modify(foundArticle.id, title, body);

            System.out.printf("%d번 게시물이 수정되었습니다.\n", foundArticle.getId());
            doAction("article","page");}
        else {
            System.out.print("수정할 게시물 번호를 입력하세요) ");

            int id = checkScNum();

            if (id == 0) {
                return;
            }

            Article foundArticle = articleService.getArticle(id);

            if (foundArticle == null) {
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

            Member loginedAdmin = session.getLoginedMember();

            if (foundArticle.patient_id != loginedAdmin.id) {
                System.out.printf("권한이 없습니다.\n");
                return;
            }

            System.out.printf("제목 : ");
            String title = sc.nextLine();
            System.out.printf("내용 : ");
            String body = sc.nextLine();

            articleService.modify(foundArticle.id, title, body);

            System.out.printf("%d번 게시물이 수정되었습니다.\n", foundArticle.getId());
            doAction("article", "page");
        }
    }

    public void doDelete() {
        if(Container.getSession().isLoginedAdmin()) {
            System.out.print("삭제할 게시물 번호를 입력하세요) ");

            int id = checkScNum();

            if (id == 0) {
                return;
            }

            Article foundArticle = articleService.getArticle(id);

            if (foundArticle == null) {
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

            Admin loginedAdmin = session.getLoginedAdmin();

            if (foundArticle.patient_id != loginedAdmin.getId()) {
                System.out.printf("권한이 없습니다.\n");
                return;
            }

            articleService.delete(foundArticle.id);

            System.out.printf("%d번 게시물이 삭제되었습니다.\n", foundArticle.id);
            doAction("article","page");
        }
        else {
            System.out.print("삭제할 게시물 번호를 입력하세요) ");

            int id = checkScNum();

            if (id == 0) {
                return;
            }

            Article foundArticle = articleService.getArticle(id);

            if (foundArticle == null) {
                System.out.printf("%d번 게시물은 존재하지 않습니다.\n", id);
                return;
            }

            Member loginedMember = session.getLoginedMember();

            if (foundArticle.patient_id != loginedMember.getId()) {
                System.out.printf("권한이 없습니다.\n");
                return;
            }

            articleService.delete(foundArticle.id);

            System.out.printf("%d번 게시물이 삭제되었습니다.\n", foundArticle.id);
            doAction("article", "page");
        }
    }

    public int checkScNum() {
        int id = 0;

        try {
            id = sc.nextInt();
            sc.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("잘못 입력하셨습니다.");
            sc.nextLine();
            return 0;
        }

        return id;
    }
}