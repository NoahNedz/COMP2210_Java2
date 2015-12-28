/**
* LinkedStrand.java
* Provides a linked chunk list implementation of the DnaStrand interface.
* 
* @author   Mark Gallagher Jr <mag0038@tigermail.auburn.edu>
* @author   Dean Hendrix <dh@auburn.edu>
* @version  2014-10-25
*
*/
public class LinkedStrand implements DnaStrand {

    /**
    * Container for storing DNA information. A given DNA strand is
    * represented by a chain of nodes.
    *
    * D O   N O T   M A K E   A N Y   C H A N G E S   T O
    *
    * T H E   N O D E   C L A S S 
    *
    */
    public class Node {
        public String dnaInfo;
        public Node next;

        public Node() {
        }

        public Node(String s, Node n) {
            dnaInfo = s;
            next = n;
        }
    }

    // the first and last node in the DNA strand
    // L E A V E   T H E S E   F I E L D S   P U B L I C
    public Node front;
    public Node rear;

    // an empty strand
    public static LinkedStrand EMPTY_STRAND = new LinkedStrand();

    // the number of nucleotides in this strand
    private long size;

    /**
    * Create a strand representing an empty DNA strand, length of zero.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    */
    public LinkedStrand() {
        front = rear = null;
        size = 0;
    }


    /**
    * Create a strand representing s. No error checking is done to see if s represents
    * valid genomic/DNA data.
    *
    * @param s is the source of cgat data for this strand
    */
    public LinkedStrand(String s) {
        front = rear = new Node(s, null);
        size = s.trim().length();
    }


    /**
    * Initialize by copying DNA data from the string into this strand,
    * replacing any data that was stored. The parameter should contain only
    * valid DNA characters, no error checking is done by the this method.
    * 
    * @param source is the string used to initialize this strand
    */
    public void initializeFrom(String source) {
        front = rear = new Node(source, null);
        size = source.trim().length();
    }


    /**
    * Simulate a restriction enzyme cut by finding the first occurrence of
    * enzyme in this strand and replacing this strand with what comes before
    * the enzyme while returning a new strand representing what comes after the
    * enzyme. If the enzyme isn't found, this strand is unaffected and an empty
    * strand with size equal to zero is returned.
    * 
    * @param enzyme is the string being searched for
    * @return the part of the strand that comes after the enzyme
    */
    public DnaStrand cutWith(String enzyme) {
        if (front != rear) {
            throw new IllegalStateException("Multiple nodes in initial DNA strand!");
        }

        int pos = front.dnaInfo.indexOf(enzyme);
        if (pos == -1) return EMPTY_STRAND;

        DnaStrand ret = new LinkedStrand(front.dnaInfo.substring(pos + enzyme.length()));
        initializeFrom(front.dnaInfo.substring(0, pos));

        return ret;
    }


    /**
    * Cut this strand at every occurrence of enzyme, replacing
    * every occurrence of enzyme with splice.
    *
    * @param enzyme is the pattern/strand searched for and replaced
    * @param splice is the pattern/strand replacing each occurrence of enzyme
    * @return the new strand leaving the original strand unchanged.
    */
    public DnaStrand cutAndSplice(String enzyme, String splice) {
        if (front != rear) {
            throw new IllegalStateException("Multiple nodes in initial DNA strand!");
        }

        LinkedStrand ret = new LinkedStrand(front.dnaInfo);
        int pos = 0;
        boolean isSpliced = false;
        Node p = ret.front;
        while (p.dnaInfo.contains(enzyme)) {
            pos = p.dnaInfo.indexOf(enzyme);
            if (pos == 0) {
                p.next = new Node(p.dnaInfo.substring(pos + enzyme.length()), null);
                p.dnaInfo = splice;
                if (p.next.dnaInfo.length() == 0) {
                    p.next = null;
                }
                else {
                    p = p.next;
                }
                ret.size = ret.size - enzyme.length() + splice.length();
            }
            else {
                p.next = new Node(p.dnaInfo.substring(pos), null);
                p.dnaInfo = p.dnaInfo.substring(0, pos);
                p = p.next;
            }

            isSpliced = true;
        }

        ret.rear = p;

        if (!isSpliced) return EMPTY_STRAND;
        return ret;
    }


    /**
    * Returns the number of elements/base-pairs/nucleotides in this strand.
    * @return the number of base-pairs in this strand
    */
    public long size() {
        return size;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("");
        Node p = front;
        while (p != null) {
            sb.append(p.dnaInfo);
            p = p.next;
        }
        return sb.toString();
    }


    /**
    * Appends the parameter to this strand changing this strand to represent
    * the addition of new characters/base-pairs, e.g., changing this strand's
    * size.
    * 
    * If possible implementations should take advantage of optimizations
    * possible if the parameter is of the same type as the strand to which data
    * is appended.
    * 
    * @param dna is the strand being appended
    * @return this strand after the data has been added
    */
    public DnaStrand append(DnaStrand dna) {
        if (dna instanceof LinkedStrand) {
            LinkedStrand ss = (LinkedStrand) dna;
            rear.next = ss.front;
            rear = ss.rear;
            return this;
        }
        else {
            return append(dna.toString());
        }
    }


    /**
    * Similar to append with a DnaStrand parameter in
    * functionality, but fewer optimizations are possible. Typically this
    * method will be called by the append method with an DNAStrand
    * parameter if the DnaStrand passed to that other append method isn't the same 
    * class as the strand being appended to.
    * 
    * @param dna is the string appended to this strand
    * @return this strand after the data has been added
    */
    public DnaStrand append(String dna) {
        Node ss = new Node(dna, null);
        rear.next = ss;
        rear = rear.next;
        return this;
    }

}
