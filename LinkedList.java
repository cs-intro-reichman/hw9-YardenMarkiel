/**
 * Represents a list of Nodes. 
 */
public class LinkedList {

    private Node first; // pointer to the first element of this list
    private Node last;  // pointer to the last element of this list
    public int size;    // number of elements in this list

    public LinkedList() {
        first = null;
        last = null;
        size = 0;
    }

    public Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index must be between 0 and size - 1");
        }
        Node current = first;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public void add(int index, MemoryBlock block) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Index must be between 0 and size");
        }

        Node newNode = new Node(block, null);

        if (size == 0) {
            first = newNode;
            last = newNode;
        } else if (index == 0) {
            newNode.next = first;
            first = newNode;
        } else if (index == size) {
            last.next = newNode;
            last = newNode;
        } else {
            Node current = getNode(index - 1);
            newNode.next = current.next;
            current.next = newNode;
        }

        size++;
    }

    public void addLast(MemoryBlock block) {
        add(size, block);
    }

    public void addFirst(MemoryBlock block) {
        add(0, block);
    }

    public MemoryBlock getBlock(int index) {
        Node node = getNode(index);
        return node.block;
    }

    public int indexOf(MemoryBlock block) {
        int index = 0;
        Node current = first;
        while (current != null) {
            if (current.block.equals(block)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public void remove(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Node cannot be null");
        }

        if (first == null) {
            throw new IllegalArgumentException("The list is empty");
        }

        if (node.equals(first)) {
            first = first.next;
            size--;
            if (size == 0) {
                last = null;
            }
            return;
        }

        Node current = first;
        while (current.next != null) {
            if (current.next.equals(node)) {
                current.next = current.next.next;
                size--;
                if (current.next == null) {
                    last = current;
                }
                return;
            }
            current = current.next;
        }

        throw new IllegalArgumentException("Node not found in the list");
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Index must be between 0 and size - 1");
        }

        if (index == 0) {
            first = first.next;
            size--;
            if (size == 0) {
                last = null;
            }
            return;
        }

        Node current = getNode(index - 1);

        if (index == size - 1) {
            last = current;
            current.next = null;
        } else {
            current.next = current.next.next;
        }

        size--;
    }

    public void remove(MemoryBlock block) {
        int index = indexOf(block);
        if (index == -1) {
            throw new IllegalArgumentException("Block not found in the list");
        }
        remove(index);
    }

    public ListIterator iterator() {
        return new ListIterator(first);
    }

    public String toString() {
        if (size == 0) {
            return "empty list";
        }

        StringBuilder result = new StringBuilder();
        Node current = first;
        int index = 1;
        while (current != null) {
            result.append(index).append(": ").append(current.block).append("\n");
            current = current.next;
            index++;
        }
        return result.toString();
    }
}