package models;

public class EmptyCell extends Cell {

    public EmptyCell(int id, String roadName, String cellName) {
        super(id, roadName, cellName);
    }

    @Override
    public boolean canBuy() {
        return false;
    }

    @Override
    public boolean needPass() {
        return false;
    }

}
