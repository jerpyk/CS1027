// Name: Eunsung Kim
// Class Description: the class is for generic type T, each non-linear linked node with
// one parent and any number of children nodes.

import java.util.Comparator;
import java.util.Iterator;

public class NLNode<T> {

	// Instance Variables
	private NLNode<T> parent;
	private ListNodes<NLNode<T>> children;
	private T data;

	// Default Constructor
	public NLNode() {
		parent = null;
		data = null;
		// create an empty list of node for the this node's children
		children = new ListNodes<NLNode<T>>();
	}

	// Constructor with defined data of type T and parent node p
	public NLNode(T d, NLNode<T> p) {
		parent = p;
		data = d;
		children = new ListNodes<NLNode<T>>();
	}

	// Method to set the parent of the node
	public void setParent(NLNode<T> p) {
		parent = p;
	}

	// Method to return the parent of the node
	public NLNode<T> getParent() {
		return parent;
	}

	// Method to add the new child to the list node children. The parent of this
	// child is this node
	public void addChild(NLNode<T> newChild) {
		this.children.add(newChild);
		newChild.setParent(this);
	}

	// Method that returns the iterator with each item being a child, non-linear
	// node of type T
	public Iterator<NLNode<T>> getChildren() {
		return this.children.getList();
	}

	// Method that returns the sorted iterator of this node's children as specified
	// by the Comparator sorter
	public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
		return children.sortedList(sorter);
	}

	// Method that returns the data item contained in this node
	public T getData() {
		return this.data;
	}

	// Method that sets the data item of this node
	public void setData(T d) {
		this.data = d;
	}

}
