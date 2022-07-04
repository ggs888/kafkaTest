package model;


import util.ConsoleColors;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Player {
    private String id;
    private String name;
    private String color;
    private List<Piece> pieceList;

    public Player(String id, String name, String color, int size) {
        this.id = id;
        this.name = name;
        this.color = color;
        List<Piece> pieceList = new ArrayList<>();
        //Query: Better way to do below initialization
        for(int i=0;i<size;i++)
            pieceList.add(new Piece(i+1));
        this.pieceList = pieceList;
    }

    public void printPlayerPieces(){
        System.out.println();
        System.out.print("Pieces of " + this.name + " are: ");
        for( Piece piece : pieceList)
            System.out.print(this.color + piece.getVal() + " " + ConsoleColors.RESET);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<Piece> getPieceList() {
        return pieceList;
    }

    public void setPieceList(List<Piece> pieceList) {
        this.pieceList = pieceList;
    }
}
