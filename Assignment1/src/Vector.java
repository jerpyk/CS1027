/**
 * Vector class that includes the variables and methods for each Vector object.
 * The class is a child class of Matrix, since Vector is a Matrix with one row.
 * 
 * @author Eunsung Kim
 *
 */
public class Vector extends Matrix {

	/**
	 * Constructor for Vector object, with number of columns.
	 * 
	 * @param c the number of columns
	 */
	public Vector(int c) {
		// call the constructor of the Matrix class
		super(1, c); // set the row number to 1
	}

	/**
	 * Constructor for Vector object, with number of columns and data from linArr.
	 * 
	 * @param c      the number of columns
	 * @param linArr the array with elements to fill the Vector with
	 */
	public Vector(int c, double[] linArr) {
		// call the constructor of the Matrix class
		super(1, c, linArr);
	}

	/**
	 * Returns the element of the Vector at row 0 and column c
	 * 
	 * @param c the column number
	 * @return the value stored at the specified location
	 */
	public double getElement(int c) {
		return getData()[0][c];

	}
}
