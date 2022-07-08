package model;

import javafx.util.Pair;

import java.util.List;
import java.util.Objects;

public class Game {

    private Board board;
    private List<Player> playerList;

    //Query: where should this makeMove method be ? in player class ? in board class ? or in this Game class ?
    // As of now, makeMove function is just doing validations on top of setCells. rest is same

    public Pair<Boolean, String> makeMove(Player player, int pieceVal, int row, int col){
        //Below two lines to use zero based indexing
        row--;
        col--;

        //Query:: Below validations/ handling is not good. How to improve this ?
        Pair<Piece, String> responsePiece = getPieceIfValidValue(player, pieceVal);
        Piece piece = responsePiece.getKey();
        if(Objects.isNull(piece))
            return new Pair<>(false, responsePiece.getValue());

        Pair<Boolean, String> responseValidDest = isValidDestination(row, col);
        if(!responseValidDest.getKey())
            return new Pair<>(false, responseValidDest.getValue());

        Pair<Boolean, String> responseValidMove = isValidMove(player, piece, row, col);
        if(!responseValidMove.getKey())
            return new Pair<>(false, responseValidMove.getValue());

        board.setCell(row, col, player, piece);
        player.getPieceList().remove(piece);
        return new Pair<>(true, "Successful move");
    }

    private Pair<Boolean, String> isValidMove(Player player, Piece piece, int row, int col) {
        Cell cell = this.board.getCells()[row][col];
        if(Objects.isNull(cell.getPlayer()))
            return new Pair<>(true, "Placed as cell is empty.");

        if(player.equals(cell.getPlayer()))
            return new Pair<>(false, "You can't place over your own piece.");

        if(cell.getPiece().getVal() >= piece.getVal())
            return new Pair<>(false, "Your piece is smaller, you can't place here.");

        return new Pair<>(true, "Placed as previous piece was smaller.");
    }

    public Player winner(){
        int rows = this.board.getRows();
        int cols = this.board.getCols();

        //Checking horizontally
        for(int i=0;i<rows;i++){
            Player prevPlayer = this.board.getCells()[i][0].getPlayer();
            int j=-1;
            if(Objects.nonNull(prevPlayer))
                for(j=1;j<cols;j++)
                    if(!prevPlayer.equals(this.board.getCells()[i][j].getPlayer()))
                        break;
            if(j==cols)
                return prevPlayer;
        }

        //Checking vertically
        for(int j=0;j<cols;j++){
            Player prevPlayer = this.board.getCells()[0][j].getPlayer();
            int i=-1;
            if(Objects.nonNull(prevPlayer))
                for(i=1;i<rows;i++)
                    if(!prevPlayer.equals(this.board.getCells()[i][j].getPlayer()))
                        break;
            if(i==rows)
                return prevPlayer;
        }

        //Checking first diagonal
        int min = Math.min(rows, cols);
        Player prevPlayer = this.board.getCells()[0][0].getPlayer();
        int i=-1;
        if(Objects.nonNull(prevPlayer))
            for(i=1;i<min;i++)
                if(!prevPlayer.equals(this.board.getCells()[i][i].getPlayer()))
                    break;
        if(i==min)
            return prevPlayer;

        //Checking second diagonal
        prevPlayer = this.board.getCells()[min-1][min-1].getPlayer();
        i=-100;
        if(Objects.nonNull(prevPlayer))
            for(i=min-2;i>=0;i--)
                if(!prevPlayer.equals(this.board.getCells()[i][i].getPlayer()))
                    break;
        if(i==-1)
            return prevPlayer;

        return null;
    }

    private Pair<Boolean, String> isValidDestination(int row, int col) {
        if(row<0 || col<0 || row>=this.board.getRows() || col>=this.board.getCols())
            return new Pair<>(false, " Entered row or column was out of bound.");
        return new Pair<>(true, "");
    }

    private Pair<Piece, String> getPieceIfValidValue(Player player, int pieceVal) {
        Piece piece =  player.getPieceList().stream().filter(pic -> pic.getVal()==pieceVal).findFirst().orElse(null);
        return new Pair<>(piece, "Enter valid piece");
    }

    public Game(Board board, List<Player> playerList) {
        this.board = board;
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
