/**
 * Set class for creating a linked list of linear nodes.
 * 
 * @author Eunsung Kim
 *
 * @param <T> Generic type T
 */
public class Set<T> {

	private LinearNode<T> setStart; // the front node

	/**
	 * Constructor for Set object, initializing the first node to null.
	 */
	public Set() {
		setStart = null;
	}

	/**
	 * Adds a node with the element to the front of the list.
	 * 
	 * @param element the data of generic type T contained in the node
	 */
	public void add(T element) {
		LinearNode<T> newNode = new LinearNode<T>(element);
		newNode.setNext(setStart);
		setStart = newNode; // set the new node to the front of the set
	}

	/**
	 * Returns the length of the set.
	 * @return the set length
	 */
	public int getLength() {
		int listLength = 0;
		LinearNode<T> currNode = setStart; // begin the current node at the front node
		while (currNode != null) { // repeat until the current node is null
			listLength++; 
			currNode = currNode.getNext(); // move to the next node
		}
		return listLength;
	}

	/**
	 * Returns the data contained in the ith node of the set.
	 * 
	 * @param i the index number of the set
	 * @return the element of generic type T at ith node
	 */
	public T getElement(int i) {
		LinearNode<T> currNode = setStart; // begin at front of the set
		if (i == 0) // if already the front node
			return currNode.getElement();

		for (int j = 1; j <= i; j++) {
			currNode = currNode.getNext(); // check until reaching the ith node
		}
		return currNode.getElement();
	}

	/**
	 * Returns the boolean value whether the set contains element of generic type T.
	 * 
	 * @param element data of generic type T
	 * @return boolean value
	 */
	public boolean contains(T element) {
		LinearNode<T> currNode = setStart; // begin at the front of the set
		while (currNode != null) { // repeat until reaching the end of the set
			if (currNode.getElement().equals(element)) // check if it contains the element
				return true;
			else
				currNode = currNode.getNext(); // move to next node
		}
		return false;
	}
	
	/**
	 *	Returns the String representation of the set.
	 */
	public String toString() {
		LinearNode<T> currNode = setStart; // begin at the front of the set
		String s = "";
		while (currNode != null) { // repeat until reaching the end of the set
			if (currNode.getNext() != null) // if the last node
				s = s + currNode.getElement() + " ";
			else
				s = s + currNode.getElement();
			currNode = currNode.getNext(); 
		}
		return s;
	}
}
