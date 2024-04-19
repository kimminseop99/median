package org.example.resource;

public class PrintColor {

    // ANSI 이스케이프 시퀀스로 콘솔에 색상 입히기
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    public static final String NAVY_BLUE = "\033[0;38;5;18m"; // 관리자
    public static final String SKY_BLUE = "\033[0;38;5;111m"; // 환자
    public static final String STEEL_GRAY = "\033[0;38;5;244m"; // 의사
    public static final String LIGHT_GREEN = "\033[0;102m"; // 게시판
    // 예시 함수
    public static void printAdmin(String message) {
        System.out.println(NAVY_BLUE + message + RESET);
    }

    public static void printPatient(String message) {
        System.out.println(SKY_BLUE + message + RESET);
    }

    public static void printDoctor(String message) {
        System.out.println(STEEL_GRAY + message + RESET);
    }

    public static void printArticle(String message) {
        System.out.println(LIGHT_GREEN + message + RESET);
    }
    // 텍스트에 색상 적용
    public static String colorize(String text, String color) {
        return color + text + RESET;
    }


}
