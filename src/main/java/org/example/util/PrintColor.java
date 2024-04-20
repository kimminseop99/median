package org.example.util;

public class PrintColor {

    // ANSI 이스케이프 시퀀스로 콘솔에 색상 입히기
    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[47m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";


    public static final String NAVY_BLUE = "\033[0;38;5;18m";
    public static final String SKY_BLUE = "\033[0;38;5;111m";

    public static final String LIGHT_YELLOW= "\u001B[93m";
    public static final String LIGHT_CYAN= "\u001B[96m";

    // 예시 함수
    public static void printAdmin(String message) {
        System.out.println(CYAN + message + RESET);
    }

    public static void printPatient(String message) {
        System.out.println(SKY_BLUE + message + RESET);
    }

    public static void printDoctor(String message) {
        System.out.println(WHITE + message + RESET);
    }

    public static void printArticle(String message) { // 게시판 색상
        System.out.println(LIGHT_YELLOW + message + RESET);
    }

    public static void printReservation(String message) {
        System.out.println(GREEN + message + RESET);
    }

    public static void printMain(String message) {
        System.out.println(BLUE  + message + RESET);
    }


}
