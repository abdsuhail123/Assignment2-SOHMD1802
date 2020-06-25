// Dylan Fries 2019
// ItemList is a partially full array of Item Objects. 
public class ItemList{
	
	private Item[] items; // array of items
	private int currentIndex = 0; // current index of items
	private int maxSize = 100; // max number of Item object in items

	private boolean DEBUG_MODE = false;

	// Default constructor
	// set max array size, create array
	public ItemList(){
		items = new Item[maxSize];
		currentIndex = 0;
	}

	public int itemCount(){
		return currentIndex;
	}

	// Add the next item to the array
	public void addItem(Item newItem){

		if(DEBUG_MODE)
			System.out.println("DEBUG ItemList: Adding new item " + newItem.toString() + " to the item list at index " + currentIndex);

		items[currentIndex] = newItem;
		currentIndex ++; // add to array then update

	}

	// Print the whole item list as a String
	// formatting and returning all the strings
	public String toString(){

		String list = "[";		

		if(currentIndex == 0){
			return "[EMPTY]";
		}

		// Note you are using current Index not max here. 
		for(int i = 0; i < currentIndex-1; i++){
			list += items[i].toString() + ", ";
		}

		// handle the end of the list
		list+= items[currentIndex-1].toString() + "]";

		return list;
	}
	
}