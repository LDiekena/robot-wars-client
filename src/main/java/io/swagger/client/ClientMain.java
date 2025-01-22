package io.swagger.client;

import io.swagger.client.View.*;
import io.swagger.client.api.*;
import io.swagger.client.model.*;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ClientMain {

    static DefaultApi defaultApi = new DefaultApi();
    static ClientView clientView = new ClientView();

    public static void main(String[] args) throws ApiException, InterruptedException {

        String choosenRobot = "";
        String gameID = "";
        String playerID = "";
        String choosenMap = "";

        //Intro
        clientView.displayIntroScreen();

        //Bestehendes Spiel oder neu
        System.out.println("Bitte wähle aus den beiden folgenden Optionen aus: \n1 - Ich habe keine angefangenen Spiele und möchte somit eine neue Spielrunde starten." +
                "\n2 - Ich habe ein angefangenes Spiel und möchte bei diesem weiter machen.");
        Scanner sc = new Scanner(System.in);
        int newGameOrExistingGame = sc.nextInt();

        while (newGameOrExistingGame !=1 && newGameOrExistingGame != 2) {
            System.out.println("Fehlerhafte Eingabe, bitte versuche es erneut.");
        }

        //Neues Spiel
        if (newGameOrExistingGame == 1) {
            //Roboter Abfrage
            int userChoiceCreateOrChooseRobot = clientView.askCreateOrChooseRobot();
            while (userChoiceCreateOrChooseRobot != 1 && userChoiceCreateOrChooseRobot != 2) {
                userChoiceCreateOrChooseRobot = clientView.askCreateOrChooseErrorMessage();
            }

            if(userChoiceCreateOrChooseRobot == 1) {
                //neues Robot Objekt
                NewRobot newRobot = new NewRobot();

                //Nutzerabfrage nach Werten für den Roboter
                sc = new Scanner(System.in);
                System.out.println("Bitte gebe den Namen deines Roboters ein: ");
                String robotName = sc.nextLine();
                newRobot.setName(robotName);

                System.out.println("Für die Werteverteilung kannst du 15 Skillpunkte verteilen.");

                int robotHealth = 1;
                int robotAttackDamage = 1;
                int robotAttackRange = 1;
                int robotMovementRate = 1;
                int usedSkillpoints = robotHealth + robotAttackDamage + robotAttackRange + robotMovementRate;

                System.out.println("Bitte gebe den Wert für die Gesundheit deines Roboters ein: ");
                robotHealth = sc.nextInt();
                System.out.println("Bitte gebe den Wert für den Schaden deines Roboters ein: ");
                robotAttackDamage = sc.nextInt();
                System.out.println("Bitte gebe den Wert für die Reichweite deines Roboters ein: ");
                robotAttackRange = sc.nextInt();
                System.out.println("Bitte gebe den Wert für die Bewegungsrate deines Roboters ein: ");
                robotMovementRate = sc.nextInt();

                usedSkillpoints = robotHealth + robotAttackDamage + robotAttackRange + robotMovementRate;

                while (usedSkillpoints != 15 || robotHealth == 0 || robotAttackDamage == 0 || robotAttackRange == 0 || robotMovementRate == 0) {
                    System.out.println("Die eingegebenen Werte verstoßen gegen die Regeln. Die maximale Anzahl an Skilpoints (15) darf nicht überschritten werden," +
                            " alle Skillpoints müssen verwendet werden und alle Werte müssen mindesten einen Wert von 1 haben");

                    System.out.println("Bitte gebe den Wert für die Gesundheit deines Roboters ein: ");
                    robotHealth = sc.nextInt();
                    System.out.println("Bitte gebe den Wert für den Schaden deines Roboters ein: ");
                    robotAttackDamage = sc.nextInt();
                    System.out.println("Bitte gebe den Wert für die Reichweite deines Roboters ein: ");
                    robotAttackRange = sc.nextInt();
                    System.out.println("Bitte gebe den Wert für die Bewegungsrate deines Roboters ein: ");
                    robotMovementRate = sc.nextInt();

                    usedSkillpoints = robotHealth + robotAttackDamage + robotAttackRange + robotMovementRate;
                }

                newRobot.setHealth(BigDecimal.valueOf(robotHealth));
                newRobot.setAttackDamage(BigDecimal.valueOf(robotAttackDamage));
                newRobot.setAttackRange(BigDecimal.valueOf(robotAttackRange));
                newRobot.setMovementRate(BigDecimal.valueOf(robotMovementRate));

                Robot responseRobot = defaultApi.apiRobotsRobotPost(newRobot);

                System.out.println("Folgender Roboter wurde erfolgreich erstellt: " + responseRobot);

            } else {
                //chooseRobot
                List<Robot> robotList = defaultApi.apiRobotsGet();

                System.out.println("Du kannst zwischen folgenden Robotern wählen: ");

                for (int counter = robotList.size() - 1; counter >= 0; counter--) {
                    System.out.println(robotList.get(counter));
                }

                System.out.println("Bitte wähle den gewünschten Roboter aus indem du seine ID eingibst: ");
                sc = new Scanner(System.in);
                choosenRobot = sc.nextLine();

                while (!doesChoosenRobotExist(robotList, choosenRobot)) {
                    System.out.println("Die eingegebene ID entspricht keinem existierenden Roboter, bitte versuche es erneut.");
                    choosenRobot = sc.nextLine();
                }

                System.out.println("Deine Roboterauswahl war erfolgreich!");

            }

            //Spiel Abfrage
            int userChoiceCreateOrChooseGame = clientView.askCreateOrChooseGame();
            while (userChoiceCreateOrChooseGame != 1 && userChoiceCreateOrChooseGame != 2) {
                userChoiceCreateOrChooseGame = clientView.askCreateOrChooseErrorMessage();
            }

            //Spiel erstellen
            if(userChoiceCreateOrChooseGame == 1) {
                //Neues Spiel
                NewGame newGame = new NewGame();

                List<Map> maps = defaultApi.apiMapsGet();

                System.out.println("Du kannst zwischen folgenden Karten wählen: ");

                for (int counter = maps.size() - 1; counter >= 0; counter--) {
                    System.out.println(maps.get(counter));
                }

                System.out.println("Bitte wähle die gewünschte Karte aus indem du ihre ID eingibst: ");
                sc = new Scanner(System.in);
                choosenMap = sc.nextLine();

            /*
            while (!doesChoosenMapExist(maps, choosenRobot)) {
                System.out.println("Die eingegebene ID entspricht keiner existierender Karte, bitte versuche es erneut.");
                choosenRobot = sc.nextLine();
            }
            */

                //System.out.println("Deine Kartenauswahl war erfolgreich!");

                newGame.setMapId(choosenMap);

                Game newGameData = defaultApi.apiGamesGamePost(newGame);

                //Speichern der GameID und PlayerID zur weiteren Nutzung
                gameID = newGameData.getId();

                System.out.println("Die Spielerstellung war erfolgreich, dein Spiel trägt die ID " + gameID + ". Teile diese ID mit jemanden mit dem du" +
                        " spielen möchtest.");

                //Spielersteller joined dem erstellten Spiel
                JoinGame joinGame = new JoinGame();
                joinGame.setRobotId(choosenRobot);
                JoinGameResponse joinGameResponse = defaultApi.apiGamesGameIdJoinPost(joinGame, newGameData.getId());

                //Player ist immer beim createGame der erste in der Liste
                playerID = joinGameResponse.getPlayerId();

                //System.out.println("Du wurdest erfolgreich deinem erstellten Spiel zugewiesen, deine Player ID lautet " + playerID + ".");

                //Bestehenden Spiel per ID beitreten
            } else {
                JoinGame joinGame = new JoinGame();

                System.out.println("Bitte gebe die ID des Spiels ein dem du beitreten möchtest.");
                sc = new Scanner(System.in);
                String wantedGameID = sc.nextLine();

                while (!doesGameExist(wantedGameID)) {
                    System.out.println("Die eingegebene ID entspricht keinem existierenden Spiel, bitte versuche es erneut.");
                    wantedGameID = sc.nextLine();
                }

                joinGame.setRobotId(choosenRobot);

                JoinGameResponse gamedata = defaultApi.apiGamesGameIdJoinPost(joinGame, wantedGameID);

                //Speichern der im GameID und PlayerID zur weiteren Nutzung
                gameID = wantedGameID;
                playerID = gamedata.getPlayerId();

                System.out.println("Du bist dem Spiel erfolgreich beigetreten!");
            }

            //Warte auf Spielbeginn
            System.out.println("Es wird überprüft ob das Spiel gestartet werden kann, bitte haben Sie einen Moment geduld.");

            boolean gameIsStarted = false;

            while (!gameIsStarted) {
                Game gameData = defaultApi.apiGamesGameIdGet(gameID);
                if(gameData.getStatus().equals(Game.StatusEnum.STARTED)) {
                    gameIsStarted = true;
                }
                Thread.sleep(10000);
            }

            System.out.println("Das Spiel wurde gestartet, viel Spaß beim Spielen!");
            //Spielbeginn

            //Print Startspielfeld
            List<Object> mapItems;
            Double mapSize = 0.0;
            Double mapSizeX = 0.0;
            String[] field = null;

            List<Map> mapList = defaultApi.apiMapsGet();
            for(Map map : mapList) {
                Object mapID = map.get("id");
                if(mapID.equals(choosenMap)) {
                    mapSize = (Double) map.get("mapSize");
                    mapSizeX = (Double) map.get("mapSizeX");
                    mapItems = (List) map.get("mapItems");

                    field = new String[mapSize.intValue()];
                    Arrays.fill(field, " [ ]");

                    for(Object item : mapItems) {
                        Map<String, Object> itemMap = (Map<String, Object>) item;
                        String type = (String) itemMap.get("type");
                        Double index = (Double) itemMap.get("index");

                        if (type.equals("ROBOT")) {
                            field[index.intValue()] = " [X]";
                        } else if (type.equals("WALL")) {
                            field[index.intValue()] = " [W]";
                        }
                    }
                }
            }

            printMap(field, mapSizeX.intValue());

            //Move
            int currentMovementRate = 0;

            List<Robot> robotList = defaultApi.apiRobotsGet();

            for(int counter = robotList.size() - 1; counter >= 0; counter--) {
                if(robotList.get(counter).getId().equals(choosenRobot)) {
                    currentMovementRate = robotList.get(counter).getMovementRate().intValue();
                }
            }

            while (!isTurnOver(currentMovementRate, getLastMoveFromPlayer(playerID, gameID, defaultApi), gameID)) {
                System.out.println("Du hast noch " + currentMovementRate + " Züge zu deiner Verfügung");

                //TODO: Option 4 - Zug beenden trotz noch vorhandener Züge
                System.out.println("Bitte gebe an welche Art von Zug du machen möchtest. \n1 - Deinen Roboter ein Feld bewegen \n2 - Die Richtung deines Roboters anpassen" +
                        "\n3 - Deinen Roboter angreifen lassen \n4 - Deinen Zug beenden");
                sc = new Scanner(System.in);
                int turnChoice = sc.nextInt();

                while (turnChoice != 1 && turnChoice != 2 && turnChoice != 3 && turnChoice != 4) {
                    System.out.println("Die Eingabe war fehlerhaft, bitte versuche es erneut.");
                    turnChoice = sc.nextInt();
                }

                if(turnChoice == 1) {
                    //MOVE
                    move(playerID, gameID, mapSize.intValue(), mapSizeX.intValue());
                    currentMovementRate--;

                } else if (turnChoice == 2) {
                    //ALIGN
                    align(playerID, gameID);
                    currentMovementRate--;

                } else if (turnChoice == 3){
                    //ATTACK
                    attack(gameID, playerID);
                    currentMovementRate--;
                } else {
                    //TODO: END
                }
            }

        //Bestehendes Spiel
        } else {
            System.out.println("Bitte gebe die ID des Spiels ein zu welchem du zurückkehren möchtest:");
            gameID = sc.next();

            while (!doesGameExist(gameID)) {
                System.out.println("Fehlerhafte Eingabe, bitte versuche es erneut.");
                gameID = sc.next();
            }

            System.out.println("Bitte gebe deine PlayerID ein:");
            playerID = sc.next();

            while (!doesPlayerExistInGame(gameID, playerID)) {
                System.out.println("Fehlerhafte Eingabe, bitte versuche es erneut.");
                playerID = sc.next();
            }

            //TODO: Überprüfe ob Spieler dran ist, Move oder warten bis anderer Spieler Zug beendet und Spielfeld aktualisiert wurde


        }
    }

    public static boolean doesChoosenRobotExist(List<Robot> robotList, String robotID) throws ApiException {
        boolean robotExist = false;
        for (int counter = robotList.size() - 1; counter >= 0; counter--) {
            if (robotList.get(counter).getId().equals(robotID)) {
                robotExist = true;
                break;
            }
        }
        return robotExist;
    }

    /*
    public static boolean doesMapExist(String mapID) throws ApiException {
        boolean MapExist = false;
         = defaultApi.apiMapsGet();
        if(game != null) {
            MapExist = true;
        }
        return MapExist;
    }
    */

    public static boolean doesGameExist(String gameID) throws ApiException {
        boolean gameExist = false;
        Game game = defaultApi.apiGamesGameIdGet(gameID);
        if(game != null) {
            gameExist = true;
        }
        return gameExist;
    }

    public static boolean doesPlayerExistInGame(String gameID, String playerID) throws ApiException {
        Game gameData = defaultApi.apiGamesGameIdGet(gameID);
        System.out.println(gameData.getPlayer().get(0).getPlayerId());
        System.out.println(gameData.getPlayer().get(1).getPlayerId());
        if(gameData.getPlayer().get(0).getPlayerId().equals(playerID)) {
            return true;
        } else if (gameData.getPlayer().get(1).getPlayerId().equals(playerID)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isGameStarted(String gameID) throws ApiException {
        boolean gameStarted = false;
        Game game = defaultApi.apiGamesGameIdGet(gameID);
        if(game.getStatus() == Game.StatusEnum.STARTED) {
            gameStarted = true;
        }
        return gameStarted;
    }

    public static Move getLastMoveFromPlayer(String playerID, String gameID, DefaultApi defaultApi) throws ApiException {
        List<Move> movesData = defaultApi.apiGamesGameIdMoveGet(gameID);
        Move lastMoveData = null;

        for (int counter = movesData.size() - 1; counter >= 0; counter--) {
            if (movesData.get(counter).getPlayerId().equals(playerID)) {
                lastMoveData = movesData.get(counter);
                break;
            }
        }
        return lastMoveData;
    }

    public static boolean isTurnOver(int currentMovementRate, Move lastMove, String gameID) throws ApiException{
        List<Move> moves = defaultApi.apiGamesGameIdMoveGet(gameID);

        if(currentMovementRate == 0 && lastMove.getMovementType() == MovementType.ATTACK) {
            return true;
        } else if (lastMove.getMovementType() == MovementType.END && moves.size() > 2) {
            return true;
        }
        return false;
    }

    public static void printMap(String[] field, int mapSizeX) {
        for (int i = 0; i < field.length; i++){
            if((i + 1) % (mapSizeX) == 0) {
                System.out.println(field[i]);
            } else {
                System.out.print(field[i]);
            }
        }
    }

    public static void move(String playerID, String gameID, int mapSize, int mapSizeX) throws ApiException {
        NewMove newMove = new NewMove();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bitte gebe an in welche Richtung du gehen möchtest:\nw - Oben \na - Links \ns - Unten \nd = Rechts");
        String directionChoice = sc.next();

        while (!directionChoice.equals("w") && !directionChoice.equals("a") && !directionChoice.equals("s") && !directionChoice.equals("d")) {
            System.out.println("Die Eingabe war fehlerhaft, bitte versuche es erneut.");
            directionChoice = sc.next();
        }

        Move lastMove = getLastMoveFromPlayer(playerID, gameID, defaultApi);

        //TODO: Wall bisher nicht variabel!
        if(directionChoice.equals("w")  && lastMove.getMapIndex().intValue() >= 8 && lastMove.getAlign().equals(Align.N)) {
            newMove.setMapIndex(BigDecimal.valueOf(lastMove.getMapIndex().intValue() - mapSizeX));
        } else if (directionChoice.equals("a") && (lastMove.getMapIndex().intValue() % mapSizeX) == 0 && lastMove.getAlign().equals(Align.W)) {
            newMove.setMapIndex(BigDecimal.valueOf(lastMove.getMapIndex().intValue() - 1));
        } else if (directionChoice.equals("s") && lastMove.getMapIndex().intValue() < mapSize - mapSizeX && lastMove.getAlign().equals(Align.S)) {
            newMove.setMapIndex(BigDecimal.valueOf(lastMove.getMapIndex().intValue() + mapSizeX));
        } else if (directionChoice.equals("d") && (lastMove.getMapIndex().intValue() != 8 || lastMove.getMapIndex().intValue() != 17 ||
                lastMove.getMapIndex().intValue() != 26 || lastMove.getMapIndex().intValue() != 35 || lastMove.getMapIndex().intValue() != 44) &&
                lastMove.getAlign().equals(Align.E)) {
            newMove.setMapIndex(BigDecimal.valueOf(lastMove.getMapIndex().intValue() + 1));
        } else {
            System.out.println("Fehler beim durchführen des Zuges.");
        }

        defaultApi.apiGamesGameIdMovePlayerPlayerIdPost(newMove, gameID, playerID);
    }

    public static void align(String playerID, String gameID) throws ApiException {
        NewMove newMove = new NewMove();
        Scanner sc = new Scanner(System.in);

        System.out.println("Bitte gebe die Richtung an in die du dich drehen möchtest, du hast folgende Optionen zur Auswahl: \nN \nNE \nE \nSE \nS \nSW \nW \nNW");
        String direction = sc.next();

        switch (direction) {
            case "N":
                newMove.setAlign(Align.N);
                break;
            case "NE":
                newMove.setAlign(Align.NE);
                break;
            case "E":
                newMove.setAlign(Align.E);
                break;
            case "SE":
                newMove.setAlign(Align.SE);
                break;
            case "S":
                newMove.setAlign(Align.S);
                break;
            case "SW":
                newMove.setAlign(Align.SW);
                break;
            case "W":
                newMove.setAlign(Align.W);
                break;
            case "NW":
                newMove.setAlign(Align.NW);
                break;
        }

        Move lastMove = getLastMoveFromPlayer(playerID, gameID, defaultApi);

        newMove.setMovementType(MovementType.ALIGN);
        newMove.setMapIndex(lastMove.getMapIndex());
        newMove.setPlayerId(playerID);

        defaultApi.apiGamesGameIdMovePlayerPlayerIdPost(newMove, gameID, playerID);
    }

    public static void attack(String gameID, String playerID) throws ApiException {
        NewMove newMove = new NewMove();

        Move lastMove = getLastMoveFromPlayer(playerID, gameID, defaultApi);

        newMove.setMovementType(MovementType.ATTACK);
        newMove.setAlign(lastMove.getAlign());
        newMove.setMapIndex(lastMove.getMapIndex());
        newMove.setPlayerId(playerID);

        defaultApi.apiGamesGameIdMovePlayerPlayerIdPost(newMove, gameID, playerID);
    }

}
