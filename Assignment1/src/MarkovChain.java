/**
 * MarkovChain class that includes the variables and methods for each
 * MarkovChain object. The class uses Matrix and Vector classes predict the
 * probability from transition from one state to another.
 * 
 * @author Eunsung Kim
 *
 */
public class MarkovChain {

	private Vector stateVector;
	private Matrix transitionMatrix;

	/**
	 * Constructor for MarkovChain object, created with a state Vector and a
	 * transition Matrix
	 * 
	 * @param sVector a state Vector
	 * @param tMatrix a transition Matrix
	 */
	public MarkovChain(Vector sVector, Matrix tMatrix) {
		stateVector = sVector;
		transitionMatrix = tMatrix;
	}

	/**
	 * Returns the boolean value of whether the MarkovChain's transition Matrix and
	 * state Vectors are valid.
	 * 
	 * @return boolean value
	 */
	public boolean isValid() {
		double sumVal = 0;
		// check if the matrix is square (same row and column numbers), and check if
		// that is equal to the column number of the state vector
		if (transitionMatrix.getNumRows() == transitionMatrix.getNumCols()
				&& transitionMatrix.getNumCols() == stateVector.getNumCols()) {

			// check if the sum of the elements of the Vector is 1
			for (int i = 0; i < stateVector.getNumCols(); i++)
				sumVal += stateVector.getData()[0][i];
			if (sumVal < 0.99 || sumVal > 1.01)
				return false;

			// check if the sum of each row of the Matrix is 1
			for (int i = 0; i < transitionMatrix.getNumRows(); i++) {
				sumVal = 0; // reset the sum value
				for (int j = 0; j < transitionMatrix.getNumCols(); j++) {
					// add the elements of the transition Matrix in each row
					sumVal += transitionMatrix.getData()[i][j];
				}
				if (sumVal < 0.99 || sumVal > 1.01)
					return false;
			}
			return true; // return true if the condition is satisfied
		}
		return false;
	}

	/**
	 * Returns a Matrix with containing the probabilities of transitioning into each
	 * state.
	 * 
	 * @param numSteps the number of times to multiply the Matrix by itself
	 * @return the resultant Matrix
	 */
	public Matrix computeProbabilityMatrix(int numSteps) {
		// return null if not valid
		if (!isValid())
			return null;

		Matrix m = transitionMatrix;
		// multiply the transition Matrix by itself numSteps times
		for (int i = 0; i < numSteps - 1; i++) {
			m = m.multiply(transitionMatrix);
		}
		// finally, multiply the state Vector by the Matrix
		m = stateVector.multiply(m);
		return m;
	}
}
