/**
 * Represents a managed memory space.
 */
public class MemorySpace {
    private LinkedList allocatedList;
    private LinkedList freeList;

    public MemorySpace(int maxSize) {
        allocatedList = new LinkedList();
        freeList = new LinkedList();
        freeList.addLast(new MemoryBlock(0, maxSize));
    }

    public int malloc(int length) {
        ListIterator freeIterator = freeList.iterator();
        while (freeIterator.hasNext()) {
            MemoryBlock curBlockFree = freeIterator.next();
            if (curBlockFree.length >= length) {
                MemoryBlock newBlockAlloc = new MemoryBlock(curBlockFree.baseAddress, length);
                allocatedList.addLast(newBlockAlloc);
                if (curBlockFree.length > length) {
                    curBlockFree.baseAddress += length;
                    curBlockFree.length -= length;
                } else {
                    freeList.remove(curBlockFree);
                }
                return newBlockAlloc.baseAddress;
            }
        }
        return -1;
    }

    public void free(int address) {
        ListIterator allocIterator = allocatedList.iterator();
        while (allocIterator.hasNext()) {
            MemoryBlock curBlockAlloc = allocIterator.next();
            if (curBlockAlloc.baseAddress == address) {
                allocatedList.remove(curBlockAlloc);
                freeList.addLast(curBlockAlloc);
                return;
            }
        }
    }

    public void defrag() {
        ListIterator freeIterator = freeList.iterator();
        while (freeIterator.hasNext()) {
            MemoryBlock curBlock = freeIterator.next();
            ListIterator secondIterator = freeList.iterator();
            while (secondIterator.hasNext()) {
                MemoryBlock nextBlock = secondIterator.next();
                if (curBlock != nextBlock && curBlock.baseAddress + curBlock.length == nextBlock.baseAddress) {
                    curBlock.length += nextBlock.length;
                    freeList.remove(nextBlock);
                    secondIterator = freeList.iterator();
                }
            }
        }
    }

    public String toString() {
        return "Free List: " + freeList.toString() + "\nAllocated List: " + allocatedList.toString();
    }
}