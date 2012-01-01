package models;

import com.google.gson.annotations.Expose;

public class EstateCell extends Cell {

    public int level;

    public int price;

    public Player owner;

    public EstateCell(Cell next, Cell previous, int id, String roadName,
            String cellName, int price) {
        super(next, previous, id, roadName, cellName);
        this.level = 0;
        this.price = price;
    }

    public EstateCell(int id, String roadName, String cellName, int price) {
        this(null, null, id, roadName, cellName, price);
    }

    @Override
    public boolean canBuy() {
        return owner == null;
    }
    
    /**
     * Change the owner of the cell to the given player.
     * An {@link OwnerChangeEvent} would be generated.
     * @param player The new owner of the cell.
     */
    public void changeOwner(Player player) {
        this.owner = player;
        Event.events.publish(new OwnerChangeEvent(player.name));
    }
    
    static class OwnerChangeEvent extends Event {
        
        public final String ownerName;
        
        public OwnerChangeEvent(String ownerName) {
            this.ownerName = ownerName;
        }
    }

    @Override
    public boolean needPass() {
        return true;
    }

}
