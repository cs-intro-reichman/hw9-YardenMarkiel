/** Represents an iterator of a linked list. */
public class ListIterator {
    private Node current;

    public ListIterator(Node node) {
        current = node;
    }

    public boolean hasNext() {
        return (current != null);
    }

    public MemoryBlock next() {
        MemoryBlock block = current.block;
        current = current.next;
        return block;
    }
}