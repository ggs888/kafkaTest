package model;

public class Cell {

    private Player player;
    private Piece piece;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public Cell(Player player, Piece piece) {
        this.player = player;
        this.piece = piece;
    }

    public Cell(){
        this.player = null;
        this.piece = null;
    }
    @Override
    public String toString() {
        return "Cell{" +
                "player=" + player +
                ", piece=" + piece +
                '}';
    }
}
