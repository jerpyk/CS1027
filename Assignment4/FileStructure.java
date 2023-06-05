// Name: Eunsung Kim
// Class Description: the class is for a file structure, with the top of the file structure 
// being the specified non-linear root node.

import java.util.ArrayList;
import java.util.Iterator;

public class FileStructure {

	// Instance Variable
	private NLNode<FileObject> root;

	// Constructor
	public FileStructure(String fileObjectName) throws FileObjectException {
		FileObject f = new FileObject(fileObjectName);
		root = new NLNode<FileObject>(f, null);
		// if the file object stored in the root is a folder
		if (f.isDirectory()) {
			// call the auxiliary recursive method to get through every files
			recFileStructure(root);
		}
	}

	// Auxiliary recursive method for the constructor
	private void recFileStructure(NLNode<FileObject> r) {
		FileObject f = r.getData();
		// base case for when the FileObject is a file (not necessary but included)
		if (f.isFile())
			return;

		// recursive case fir when the FilObject is a folder
		else if (f.isDirectory()) {
			Iterator<FileObject> itrList = f.directoryFiles();
			// for every children of this folder
			while (itrList.hasNext()) {
				// create the child node
				NLNode<FileObject> n = new NLNode<FileObject>(itrList.next(), null);
				r.addChild(n);
				n.setParent(r);
				// recursively call the method with its child
				recFileStructure(n);
			}
		}
	}

	// Method to return the root node of the file structure
	public NLNode<FileObject> getRoot() {
		return root;
	}

	// Method to return the iterator with items being the absolute file path names
	// of the specified type
	public Iterator<String> filesOfType(String type) {
		// get the array list and return it as an iterator
		return recFilesOfType(root, type).iterator();
	}

	// Auxiliary recursive method for filesOfType method
	private ArrayList<String> recFilesOfType(NLNode<FileObject> r, String type) {
		// create a new array list to add the files of specified type
		ArrayList<String> fileTypeList = new ArrayList<String>();
		FileObject f = r.getData();
		// base case, when the file object is a file
		if (f.isFile()) {
			String abPath = f.getLongName();
			// check the extension of the file
			if (abPath.endsWith(type)) {
				// if matches, add to the array list
				fileTypeList.add(abPath);
			}

			// recursive case, when the file object is a folder
		} else if (f.isDirectory()) {
			// create the iterator with the folder's children
			Iterator<NLNode<FileObject>> itrList = r.getChildren();
			// for every children of this folder
			while (itrList.hasNext()) {
				NLNode<FileObject> n = itrList.next();
				// recursively call the method to continuously add the files by combining the
				// array lists
				fileTypeList.addAll(recFilesOfType(n, type));
			}
		}

		// return the array list with all of the files with the specified file type
		return fileTypeList;
	}

	// Method to return the absolute path name of the first found file that matches
	// specified name
	public String findFile(String name) {
		return recFindFile(root, name);

	}

	// Auxiliary recursive method for findFile method
	private String recFindFile(NLNode<FileObject> r, String name) {
		String s = "";
		FileObject f = r.getData();
		// base case, when the file object is a file
		if (f.isFile()) {
			String fileName = f.getName();
			// if the specified file name is the same as the current file name
			if (name.equals(fileName))
				// return the absolute path of the file
				return f.getLongName();

			// recursive case, when the file object is a folder
		} else if (f.isDirectory()) {
			// create the iterator with the folder's children
			Iterator<NLNode<FileObject>> itrList = r.getChildren();
			// for every children of this folder
			while (itrList.hasNext()) {
				NLNode<FileObject> n = itrList.next();
				// recursively call the method to get the file with the specified name
				s += recFindFile(n, name);
				// if the name of the found file is not empty string
				if (!s.equals(""))
					// return the absolute path of the file
					return s;
			}
		}

		// return the empty string if nothing was returned before
		return s;
	}

}
