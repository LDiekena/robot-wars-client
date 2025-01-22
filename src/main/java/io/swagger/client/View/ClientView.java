package io.swagger.client.View;

import java.util.Scanner;

public class ClientView {
    //Farbcodes
    public final String farbReset = "\u001B[0m";
    public final String gelb = "\u001B[33m";

    public void displayIntroScreen() {
        System.out.println("      Herzlich willkommen bei Robot Wars!");

        String welcomeASCII = gelb + "           ___\n" +
                "          |_|_|\n" +
                "          |_|_|              _____\n" +
                "          |_|_|     ____    |*_*_*|\n" +
                " _______   _\\__\\___/ __ \\____|_|_   _______\n" +
                "/ ____  |=|      \\  <_+>  /      |=|  ____ \\\n" +
                "~|    |\\|=|======\\\\______//======|=|/|    |~\n" +
                " |_   |    \\      |      |      /    |    |\n" +
                "  \\==-|     \\     | Robot|     /     |----|~~/\n" +
                "  |   |      |    | Wars |    |      |____/~/\n" +
                "  |   |       \\____\\____/____/      /    / /\n" +
                "  |   |         {----------}       /____/ /\n" +
                "  |___|        /~~~~~~~~~~~~\\     |_/~|_|/\n" +
                "   \\_/        |/~~~~~||~~~~~\\|     /__|\\\n" +
                "   | |         |    ||||    |     (/|| \\)\n" +
                "   | |        /     |  |     \\       \\\\\n" +
                "   |_|        |     |  |     |\n" +
                "              |_____|  |_____|\n" +
                "              (_____)  (_____)\n" +
                "              |     |  |     |\n" +
                "              |     |  |     |\n" +
                "              |/~~~\\|  |/~~~\\|\n" +
                "              /|___|\\  /|___|\\\n" +
                "             <_______><_______>\n" + farbReset;

        System.out.println(welcomeASCII);

    }

    public int askCreateOrChooseRobot() {
        System.out.println("Um an einem Spiel teilzunehmen benötigst du einen Roboter, bitte wähle aus den folgenden Optionen aus: \n1 - Einen neuen Roboter erstellen. " +
                "\n2 - Einen bestehenden Roboter wählen.");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public int askCreateOrChooseErrorMessage() {
        System.out.println("Fehlerhafte Eingabe, bitte versuche es erneut.");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public int askCreateOrChooseGame() {
        System.out.println("Bitte wähle aus den folgenden Optionen eine Spielemöglichkeit aus: \n1 - Ein neues Spiel erstellen. \n2 - Einem bestehenden Spiel beitreten.");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }



}
