import javafx.util.Pair;
import model.Board;
import model.Game;
import model.Player;
import util.ConsoleColors;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class StartGame {

    public static void main(String[] args){

        Game game = initializeGame();
        game.getBoard().printBoard();
        printPlayers(game.getPlayerList());
        int totalPlayers = game.getPlayerList().size();
        int counter = 0;
        Scanner sc= new Scanner(System.in);

        while(true){
            Player currTurn = game.getPlayerList().get(counter);
            System.out.print("Its turn of: " + currTurn.getName() + ". Enter piece you want to place: ");
            int pieceVal = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter row and column you want to place it in: ");
            String str = sc.nextLine();
            int destRow = Integer.parseInt(str.split(" ")[0]);
            int destCol = Integer.parseInt(str.split(" ")[1]);
            Pair<Boolean, String> response = game.makeMove(currTurn, pieceVal, destRow, destCol);
            if(!response.getKey()){
                System.out.println(response.getValue());
                continue;
            }
            game.getBoard().printBoard();
            printPlayers(game.getPlayerList());
            Player winner = game.winner();
            if(Objects.nonNull(winner)){
                System.out.println(winner.getName() + " has won the game!");
                return;
            }
            counter = (counter+1)%totalPlayers;
        }

    }

    private static void  printPlayers(List<Player> playerList){
        for(Player player : playerList)
            player.printPlayerPieces();
        System.out.println();
    }

    private static Game initializeGame() {
        Board board = new Board(3,3);
        Player playerOne = new Player("1", "Gyan", ConsoleColors.GREEN, 6);
        Player playerTwo = new Player("2", "Computer", ConsoleColors.RED, 6);
        Game game = new Game(board, Arrays.asList(playerOne, playerTwo));
        return game;
    }
}

//Query-> Every time I have to find a piece of given value. I am interating over whole pieces list of player.
//Is it better to use map design wise, if yes then where to store that ? I mean in which class ?