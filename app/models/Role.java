package models;

public class Role {

	public final String name;
	
	public int cash;
	
	private static int DEFAULT_CASH = 300000;
	
	public Role(String name) {
		this.name = name;
		this.cash = DEFAULT_CASH;
	}
	
	/**
	 * The actions when a role is buying a estate cell.
	 * @param cell The given estate cell to be bought.
	 */
	public void buyEstate(EstateMapCell cell) {
		this.cash = this.cash - cell.price;
		cell.owner = this;
	}
	
	/**
	 * The actions when a role is passing by a estate cell.
	 * The cash of the role would be deducted with the price of the cell
	 * if the owner is not the same.
	 * @param cell The given estate cell the role is passing by.
	 */
	public void passEstate(EstateMapCell cell) {
		if (cell.owner != null && !this.equals(cell.owner)) {
			this.cash -= cell.price;
			cell.owner.cash += cell.price;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
