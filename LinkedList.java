



/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	public  int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		Node current = first;
		int counter = 0;
		while ( counter < index ){
			current = current.getNext();
			counter++;
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		//// Write your code here
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("Index must be between 0 and size");
		}
		Node newNode = new Node(block, null);
		if ( size == 0 ) {
			newNode.next = first;
			first = newNode;
		} 
		if ( index == 0 ) {
			newNode.next = first;
			first = newNode;
		} 
		if ( index == size ){
			last.next = newNode;
			last = newNode;
		}
		int counter = 0;
		Node current = first;
		while ( counter < index - 1 ){
			current = current.next;
			counter++;
		}
		newNode.next = current.next;
		current.next = newNode;
		size++;
	}

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		//// Write your code here
		add(size, block);
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) {
		//// Write your code here
		add(0, block);
	}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		//// Replace the following statement with your code
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index must be between 0 and size");
		}
		int counter = 0;
		Node current = first;
		while ( counter < index  ){
			current = current.next;
			counter++;
		}
		return current.block;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// Replace the following statement with your code
		int index = 0;
		Node current = first;
		while ( index <= size ){
			if ( current.block == block ) return index;
			current = current.next;
			index++;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		//// Write your code here
		if (node == null) {
        throw new IllegalArgumentException("Node cannot be null");
    }
		Node current = first;
		if (node.equals(first)) {
			first = first.next;
			size--;
			if (first == null) { 
				last = null;
			} return;
		}
		while ( current.next != null ){
			if ( current.next.equals(node)){
				Node x = current.next;
				current.next = x.next;
				size--;
				return;
			}
			current = current.next;
		}
		if ( node.equals(last)){
			last = current;
			size--;
		}
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		///replace the folowling statement with your code
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException("Index must be between 0 and size");
		}
	
		if (index == 0) {
			first = first.next;
			size--;
			if (size == 0) {
				last = null;
			}
			return;
		}
	
		Node current = first;
		for (int i = 0; i < index - 1; i++) {
			current = current.next;
		}
	
		if (index == size - 1) {
			last = current;
			current.next = null;
		} else {
			current.next = current.next.next;
		}
	
		size--;
	}

	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		//// Write your code here
		if (block == null) {
			throw new IllegalArgumentException("Block cannot be null");
		}
	
		if (first == null) {
			throw new IllegalArgumentException("The list is empty");
		}
			Node current = first;
			while ( current.next != null ){
				if ( current.next.block.equals(block) ){
					current.next = current.next.next;
					size--;
					return;
				}
				current = current.next;
			}
			last = current;
			current.next = null;
			size--;
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		//// Replace the following statement with your code
		String result = 1 + ": null" + "ֿֿֿֿֿ\n";
		if ( size == 0) return " empty list";
		Node current = first;
		int index = 2;
		while ( current != null){
			result = result + ( index ) + ": " + current.block + "\n";
			current = current.next;
			index++;
		}
		result = result + ( index ) + ": " + current.block + "\n";
		return result;
	}
}