package store;

import java.util.List;
import java.util.*;

/**
 * @author micha
 *
 */
public class GameList {

	List<StoreItem> items_;
	String type_;
	/**
	 * Types are: new, upcoming_pop, top_sell, for_you, specials
	 */
	
	/**
	 * 
	 */
	public GameList () {
		items_ = new ArrayList<StoreItem>();
	}

	public void addItem(StoreItem item) {
		items_.add(item);
	}
	
	/**
	 * @return the items_
	 */
	public List<StoreItem> getItems () {
		return items_;
	}

	/**
	 * @param items_ the items_ to set
	 */
	public void setItems ( List<StoreItem> items_ ) {
		this.items_ = items_;
	}

	/**
	 * @return the type_
	 */
	public String getType () {
		return type_;
	}

	/**
	 * @param type_ the type_ to set
	 */
	public void setType ( String type_ ) {
		this.type_ = type_;
	}

	
	
}
