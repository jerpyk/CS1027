/**
 * Power Set class for creating list of sets, each set containing a unique
 * combination of elements of type T.
 * 
 * @author Eunsung Kim
 *
 * @param <T> Generic type T
 */
public class PowerSet<T> {
	private Set<T>[] set; // array of Set objects

	/**
	 * Constructor for PowerSet object, initializing the set array with elements
	 * array.
	 * 
	 * @param elements array of data of generic type T
	 */
	public PowerSet(T[] elements) {
		// the number of possible combinations (2^number of elements)
		int combLength = (int) Math.pow(2, elements.length);
		set = new Set[combLength]; // initialize the Set array with this size
		// the String array to contain the binary representation of each combination
		String[] binaryString = new String[combLength];
		int binaryInt;
		// repeat for every combination
		for (int i = 0; i < combLength; i++) {
			set[i] = (Set<T>)new Set(); // initialize each Set object
			// change the binary representation to integer value
			binaryInt = Integer.parseInt(Integer.toBinaryString(i));
			// pad the number with leading zeros and the number of digits to length of the
			// elements array
			binaryString[i] = String.format("%0" + elements.length + "d", binaryInt);
		}
		// repeat for every combination
		for (int i = 0; i < combLength; i++) {
			// repeat for every digit of the binary
			for (int j = 0; j < elements.length; j++) {
				// if the String representation of the binary has a 1
				if (binaryString[i].substring(j, j + 1).equals("1"))
					// add the element in the position of the 1
					set[i].add(elements[j]);
			}
		}
	}

	/**
	 * Returns the length of the set array (PowerSet), the number of unique
	 * combinations.
	 * 
	 * @return the set array length
	 */
	public int getLength() {
		return set.length;
	}

	/**
	 * Returns the ith set of the set array (PowerSet).
	 * 
	 * @param i the index number of the set
	 * @return set at index i
	 */
	public Set<T> getSet(int i) {
		return set[i];
	}

}
