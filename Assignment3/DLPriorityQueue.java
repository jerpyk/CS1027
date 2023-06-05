// Name: Eunsung Kim 
// Class Description: the class implements the PriorityQueueADT interface for 
// creating a priority queue object and using the methods for the priority queue.

public class DLPriorityQueue<T> implements PriorityQueueADT<T> {

	// Instance Variables
	private DLinkedNode<T> front;
	private DLinkedNode<T> rear;
	private int count;

	// Constructor
	public DLPriorityQueue() {
		front = null;
		rear = null;
		count = 0;
	}

	@Override
	// method for adding item of type T with the set priority
	public void add(T dataItem, double priority) {
		DLinkedNode<T> newNode = new DLinkedNode<T>(dataItem, priority);
		// check from the back of the queue
		DLinkedNode<T> currNode = rear;
		// if the queue is empty, set the new node as both the front and rear
		if (front == null) {
			front = newNode;
			rear = newNode;
		} else {
			// check all nodes
			while (currNode != null) {
				// if the new node has greater priority than current
				if (newNode.getPriority() > currNode.getPriority()) {
					// if the current node is the rear node
					if (currNode == rear) {
						// add the node to the rear
						rear.setNext(newNode);
						newNode.setPrev(rear);
						rear = newNode;
						rear.setNext(null);
						break; // exit loop
					} else {
						// add the node after the current node
						newNode.setPrev(currNode);
						newNode.setNext(currNode.getNext());
						currNode.getNext().setPrev(newNode);
						currNode.setNext(newNode);
						break;
					}
				}
				// move to the previous queue if not found
				currNode = currNode.getPrev();
			}
			// if reached the end of traversing the queue
			if (currNode == null) {
				// add the node to the front
				newNode.setNext(front);
				newNode.setPrev(null);
				front.setPrev(newNode);
				front = newNode;
			}
		}
		count++;
	}

	@Override
	// method for updating the priority of dataItem
	public void updatePriority(T dataItem, double newPriority) throws InvalidElementException {
		DLinkedNode<T> currNode = front;
		// traverse through the node front the front
		while (currNode != null) {
			// if the current data item matches with the specified dataItem
			if (currNode.getDataItem().equals(dataItem)) {
				// remove currNode 
				if (currNode == front) {
					front = front.getNext();
					if (front != null)
						front.setPrev(null);
				} else if (currNode == rear) {
					rear = rear.getPrev();
					if (rear != null)
						rear.setNext(null);
				} else {
					currNode.getPrev().setNext(currNode.getNext());
					currNode.getNext().setPrev(currNode.getPrev());
				}
				count--;
				// add it back using the add method
				this.add(currNode.getDataItem(), newPriority);
				break;
			}
			// move to the next node if not found yet
			currNode = currNode.getNext();
		}
		// if reached the end of traversing the queue and not found
		if (currNode == null)
			throw new InvalidElementException("Item Not in Priority Queue.");
	}

	@Override
	// method for removing the item with the smallest priority
	public T removeMin() throws EmptyPriorityQueueException {
		T deletedItem = null;
		// if the queue is empty
		if (front == null)
			throw new EmptyPriorityQueueException("Empty Priority Queue.");
		else {
			// store the deleted item
			deletedItem = front.getDataItem();
			// remove the front item, which is the one with the smallest priority
			front = front.getNext();
			if (front != null)
				front.setPrev(null);
		}
		count--;
		return deletedItem;
	}

	@Override
	// method for determining whether the queue is empty or not
	public boolean isEmpty() {
		return (count == 0);
	}

	@Override
	// method for finding the length of the priority queue
	public int size() {
		return count;
	}

	@Override
	// method to set the string representation of the DLPriorityQueue object
	public String toString() {
		String s = "";
		DLinkedNode<T> currNode = front;
		while (currNode != null) {
			s = s + currNode.getDataItem();
			currNode = currNode.getNext();
		}
		return s;

	}

	// method for getting the rear item of the queue
	public DLinkedNode<T> getRear() {
		return rear;
	}
}
