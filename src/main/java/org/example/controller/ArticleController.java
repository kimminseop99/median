package org.example.controller;

import org.example.App;
import org.example.container.Container;
import org.example.dto.*;
import org.example.resource.OnlyMember;
import org.example.service.ArticleService;
import org.example.service.MemberService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static org.example.container.Container.adminService;

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
        Board board = session.getCurrentBoard();
        while (true) {
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
                System.out.print("번호를 선택해 주세요: ");
                int num = 0;
                try {
                    num = sc.nextInt();
                    sc.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("잘못된 입력 형식입니다. 숫자를 입력해주세요.");
                    sc.nextLine();
                    continue;
                }
                switch (num) {
                    case 1:
                        if(new OnlyMember().memberControl()) {
                            doWrite();
                            break;
                        }else {
                            continue;
                        }
                    case 2:
                        showList();
                        break;
                    case 3:
                        if (board.getName().equals("공지")) {
                            System.out.println("공지 페이지는 목록 확인만 가능합니다. ");
                            continue;
                        }
                        showDetail();
                        break;
                    case 4:
                        if(new OnlyMember().memberControl()) {
                            doModify();
                            break;
                        }else {
                            continue;
                        }
                    case 5:
                        if(new OnlyMember().memberControl()) {
                            doDelete();
                            break;
                        }else {
                            continue;
                        }
                    case 6:
                        doCurrentBoard();
                        break;
                    case 7:
                        doChangeBoard();
                        break;
                    case 8:
                        System.out.println("이전 메뉴로 돌아갑니다.");
                        App.start();
                        break;
                    default:
                        System.out.println("잘못된 번호입니다. 다시 입력해 주세요.");
                        break;
                }
            }
            break;
        }
    }

    private void doChangeBoard() {
        System.out.println("                      게시판 목록                      ");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.println("|   1. 자유 게시판                                    |");
        System.out.println("|   2. 공지 게시판                                    |");
        System.out.println("|   3. 간담췌외과                                     |");
        System.out.println("|   4. 신경외과                                      |");
        System.out.println("|   5. 산부인과                                      |");
        System.out.println("|   6. 흉부외과                                      |");
        System.out.println("|   7. 소아외과                                      |");
        System.out.println("|   8. 뒤로 가기                                     |");
        System.out.println("═════════════════════════════════════════════════════");
        System.out.print("게시판 번호를 입력하세요) ");

        int boardId = checkScNum();
        Board board = articleService.getBoard(boardId);

        if (board == null) {
            System.out.println("해당 게시판은 존재하지 않습니다.");
        } else {
            System.out.printf("[%s 게시판]으로 변경되었습니다.\n", board.getName());
            session.setCurrentBoard(board);
        }

        doAction("article", "page");
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
        doAction("article", "page");
    }

    public void doWrite() {
        System.out.printf("제목 : ");
        String title = sc.nextLine();
        System.out.printf("내용 : ");
        String body = sc.nextLine();

        int patient_id = session.getLoginedMember().getId();
        int boardId = session.getCurrentBoard().getId();

        int newId = articleService.write(patient_id, boardId, title, body);

        System.out.printf("%d번 게시물이 생성되었습니다.\n", newId);
        doAction("article", "page");

    }

    public void showList() {
        System.out.print("검색하시고 싶은 글의 제목을 적어주세요(없을 시엔 공백) : ");
        String searchKeyword = sc.nextLine().trim();
        String boardCode = Container.getSession().getCurrentBoard().getCode();

        List<Article> forPrintArticles = articleService.getForPrintArticles(boardCode, searchKeyword);

        if (forPrintArticles.isEmpty()) {
            System.out.println("검색결과가 존재하지 않습니다.");
            doAction("article", "page");
        }

        String boardName = Container.getSession().getCurrentBoard().getName();

        System.out.printf("[%s 게시판]\n", boardName);
        if (boardName.equals("공지")) {

            System.out.println("번호 |  작성자 | 조회 |  제목    | 내용 ");
        } else {
            System.out.println("번호 |  작성자 | 조회 | 제목 ");
        }

        for (int i = forPrintArticles.size() - 1; i >= 0; i--) {
            Article article = forPrintArticles.get(i);
            Member member = memberService.getMember(article.patient_id);


            if (boardName.equals("공지")) {
                System.out.printf("0   |  관리자 | %s | %s\n", article.title, article.body);
            } else {
                System.out.printf("%4d | %5s | %4d | %s\n", article.id, member.name, article.hit, article.title);
            }
        }
        doAction("article", "page");

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

        if (Container.getSession().isLoginedDoctor() || Container.getSession().isLoginedAdmin()) {
            doAction("article", "page");
        }
        if (foundArticle != null) {
            System.out.println("댓글을 작성 하시겠습니까?");
            boolean replyCheck = articleReplyAuthorityCheck();

            if (!replyCheck) {
                doAction("article", "page");
            }

            if (replyCheck) {
                System.out.println("댓글을 입력 해주세요.");
                System.out.printf("입력) ");
                String replyBody = sc.nextLine();

                Board board = session.getCurrentBoard();

                int patient_id = session.getLoginedMember().getId();
                if (board.getName().equals("공지")) {
                    articleService.replyWrite(0, patient_id, replyBody);
                } else {
                    articleService.replyWrite(id, patient_id, replyBody);
                }
                System.out.println("댓글이 작성되었습니다.");

                articleRepliesShowList(id);
            }
        }
        doAction("article", "page");

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

        Member loginedMember = session.getLoginedMember();

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
        doAction("article", "page");

    }

    public void doDelete() {

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
            doAction("article", "page");
        }

        articleService.delete(foundArticle.id);

        System.out.printf("%d번 게시물이 삭제되었습니다.\n", foundArticle.id);
        doAction("article", "page");

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