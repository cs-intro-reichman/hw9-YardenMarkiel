/**
 * Represents a block of memory.
 * Each memory block has a base address, and a length in words. 
 */
public class MemoryBlock {
    int baseAddress;  // the address where this memory block begins
    int length;       // the length of this memory block, in words

    public MemoryBlock(int baseAddress, int length) {
        this.baseAddress = baseAddress;
        this.length = length;
    }

    public boolean equals(MemoryBlock other) {
        return baseAddress == other.baseAddress && length == other.length;
    }

    public String toString() {
        return "(" + baseAddress + " , " + length + ")";
    }
}