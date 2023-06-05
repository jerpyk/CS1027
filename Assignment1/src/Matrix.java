/**
 * Matrix class that includes the variables and methods for each Matrix object.
 * to be created
 * 
 * @author Eunsung Kim
 *
 */
public class Matrix {

	private int numRows;
	private int numCols;
	private double[][] data;

	/**
	 * Constructor for Matrix object, with r rows and c columns.
	 * 
	 * @param r the number of rows for the Matrix
	 * @param c the number of columns for the Matrix
	 */
	public Matrix(int r, int c) {
		numRows = r;
		numCols = c;
		data = new double[r][c]; // creates a Matrix filled with 0s
	}

	/**
	 * Constructor for Matrix object, with r rows and c columns, filled with data
	 * from array linArr.
	 * 
	 * @param r      the number of rows for the Matrix
	 * @param c      the number of columns for the Matrix
	 * @param linArr the array with data to fill in the Matrix
	 */
	public Matrix(int r, int c, double[] linArr) {
		numRows = r;
		numCols = c;
		data = new double[r][c];
		int i = 0; // row number
		// Loop repeated until the data from linArr is all transported or the Matrix
		// runs out of space
		for (int j = 0; j < linArr.length && j < r * c; j++) {
			// if the column reaches the end and need to go to the next row
			if (j % c == 0 && j != 0) {
				i++; // increase the row number by 1
			}
			data[i][j % c] = linArr[j];
		}
	}

	/**
	 * Returns the number of rows of the Matrix.
	 * 
	 * @return number of rows
	 */
	public int getNumRows() {
		return numRows;

	}

	/**
	 * Returns the number of columns of the Matrix.
	 * 
	 * @return the number of columns
	 */
	public int getNumCols() {
		return numCols;
	}

	/**
	 * Returns the 2-D array data of the Matrix.
	 * 
	 * @return 2-D array data
	 */
	public double[][] getData() {
		return data;
	}

	/**
	 * Returns the element at row r, column c of the Matrix.
	 * 
	 * @param r the row number
	 * @param c the column number
	 * @return the element at row r, column c
	 */
	public double getElement(int r, int c) {
		return data[r][c];
	}

	/**
	 * Sets the element at specified location to a specified value.
	 * 
	 * @param r     the row number
	 * @param c     the column number
	 * @param value the value to be set as
	 */
	public void setElement(int r, int c, double value) {
		data[r][c] = value;
	}

	/**
	 * Switches the location of element at row A, column B to the element at row B,
	 * column A of the Matrix.
	 */
	public void transpose() {
		// new 2-D array for transposing
		double[][] newData = new double[numCols][numRows];
		// Set the new row and column number
		numRows = newData.length;
		numCols = newData[0].length;
		// fill the new data with switched row and column number
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				newData[i][j] = data[j][i];
			}
		}
		data = newData;
	}

	/**
	 * Multiplies the Matrix with a scalar.
	 * 
	 * @param scalar a double value
	 * @return Resultant Matrix of the multiplication
	 */
	public Matrix multiply(double scalar) {
		Matrix m = new Matrix(numRows, numCols);
		// for every row
		for (int i = 0; i < numRows; i++) {
			// for every column
			for (int j = 0; j < numCols; j++) {
				// multiply the element by the scalar
				m.data[i][j] = this.data[i][j] * scalar;
			}
		}
		return m;
	}

	/**
	 * 
	 * Multiplies the Matrix with another Matrix.
	 * 
	 * @param other a Matrix object
	 * @return Resultant Matrix after the multiplication
	 */
	public Matrix multiply(Matrix other) {
		if (this.numCols != other.numRows)
			return null;
		Matrix m = new Matrix(this.numRows, other.numCols);
		// for every row of this Matrix
		for (int i = 0; i < this.numRows; i++) {
			// for every column of other Matrix
			for (int j = 0; j < other.numCols; j++) {
				// for every column of this Matrix
				for (int k = 0; k < this.numCols; k++) {
					// keep adding the product for each this row and other column pair
					m.data[i][j] += this.data[i][k] * other.data[k][j];
				}
			}
		}
		return m;

	}

	/**
	 * Set the String representation of the Matrix.
	 */
	public String toString() {
		if (data.length == 0)
			return "Empty matrix";
		String s = "";
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				// 8 spaces, 3 decimals for each element
				s = String.format(s + "%8.3f", data[i][j]);
			}
			s = s + "\n";
		}
		return s;
	}
}
