// Name: Eunsung Kim 
// Class Description: the class is for individual nodes to be used in the 
// DLPriorityQueue class.

public class DLinkedNode<T> {
	// Instance Variables
	private T dataItem;
	private double priority;
	private DLinkedNode<T> next;
	private DLinkedNode<T> prev;

	// Constructor with specified data item and priority
	public DLinkedNode(T data, double prio) {
		dataItem = data;
		priority = prio;
		next = null;
		prev = null;
	}

	// Constructor with no parameters
	public DLinkedNode() {
		dataItem = null;
		priority = 0;
		next = null;
		prev = null;
	}

	// method for getting the priority of the node
	public double getPriority() {
		return priority;
	}

	// method for getting the data item of the node
	public T getDataItem() {
		return dataItem;
	}

	// method for getting the next node of the node
	public DLinkedNode<T> getNext() {
		return next;
	}

	// method for getting the previous node of the node
	public DLinkedNode<T> getPrev() {
		return prev;
	}

	// method for setting the priority of the node
	public void setPriority(double prio) {
		priority = prio;
	}

	// method for setting the data item of the node to specified data
	public void setDataItem(T data) {
		dataItem = data;
	}

	// method for setting the next node to the specified node
	public void setNext(DLinkedNode<T> node) {
		next = node;
	}

	// method for setting the previous node to the specified node
	public void setPrev(DLinkedNode<T> node) {
		prev = node;
	}
}
