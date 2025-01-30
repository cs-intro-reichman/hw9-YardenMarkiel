/**
 * Represents a node in a linked list. Each node points to a MemoryBlock object. 
 */
public class Node {
    MemoryBlock block;  // The memory block that this node points at
    Node next = null;   // The next node in the list

    public Node(MemoryBlock block) {
        this.block = block;
    }

    public String toString() {
        return "{" + block + "}";
    }
}