package org.example.resource;

public class PrintLogo {

    public static void mainLogo() {
        System.out.println("             ███╗   ███╗███████╗██████╗ ██╗ █████╗ ███╗   ██╗");
        System.out.println("             ████╗ ████║██╔════╝██╔══██╗██║██╔══██╗████╗  ██║");
        System.out.println("             ██╔████╔██║█████╗  ██║  ██║██║███████║██╔██╗ ██║");
        System.out.println("             ██║╚██╔╝██║██╔══╝  ██║  ██║██║██╔══██║██║╚██╗██║");
        System.out.println("             ██║ ╚═╝ ██║███████╗██████╔╝██║██║  ██║██║ ╚████║");
        System.out.println("             ╚═╝     ╚═╝╚══════╝╚═════╝ ╚═╝╚═╝  ╚═╝╚═╝  ╚═══╝");
        System.out.print("프로그램 시작중");
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("");
    }
}
