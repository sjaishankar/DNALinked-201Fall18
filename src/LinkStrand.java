
public class LinkStrand implements IDnaStrand{
	
	private class Node {
	   	String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	   }
	private Node myFirst,myLast;
	private long mySize;
	private int myAppends;
	private int myIndex;
	private int myLocalIndex;
	private Node myCurrent;
	
	public LinkStrand() {
		this("");
	}
	
	public LinkStrand(String s) {
		initialize(s);
	}
	
	@Override
	public long size() {
		return mySize;
	}
	/* initializes assigns values to each instance field that is not a part of
	 * the charAt() method
	 * @param source is the string of nucleotides that form the DNA being spliced
	 */
	@Override
	public void initialize(String source) {
		Node x = new Node(source);
		myFirst = x;
		myLast = x;
		mySize = source.length();
		myAppends = 0;
	}

	@Override
	public IDnaStrand getInstance(String source) {
		return new LinkStrand(source);
	}
	/*
	 * This method adds a node to the LinkedList of nodes by
	 * first creating a new Node with the dna as the info, then changing
	 * myLast's pointer to the new node, and increasing mySize and adding
	 * one to the myAppends count
	 * @param dna is the DNA sequence being appended
	 * @returns the LinkStrand object with the appended node
	 */
	@Override
	public IDnaStrand append(String dna) {
		Node x = new Node(dna);
		myLast.next = x;
		myLast = x;
		mySize += dna.length();
		myAppends++;
		return this;
	}
	/*
	 * (non-Javadoc)
	 * @returns a new LinkStrand with the reverse of the object's LinkedList, 
	 * such that the last node in the original list becomes the first node,
	 * which points to the second-to-last node, etc.
	 */
	@Override
	public IDnaStrand reverse() {
		Node start = myFirst;
		Node blank = new Node("");
		
		while(start != null) {
			StringBuilder str = new StringBuilder(start.info);
			Node x = new Node(str.reverse().toString());
			x.next = blank.next;
			blank.next = x;
			start = start.next;
		}
		
		StringBuilder rev = new StringBuilder();
		Node i = blank;
		while(i != null) {
			rev.append(i.info);
			i = i.next;
		}
		
		return new LinkStrand(rev.toString());
	}

	@Override
	public int getAppendCount() {
		return myAppends;
	}
	/*
	 * Method traverse through the characters in each node, without restarting
	 * to locate the character at index
	 * @param index is the index at which the character desired is located,
	 * and is independent of which node contains that character
	 * @return desired character at index
	 */
	@Override
	public char charAt(int index) {
		int count = 0;
		int dex = 0;
		Node list = myFirst;
		
		if(index >= this.toString().length()) throw new IndexOutOfBoundsException();
		
		while (count != index) {
			count++;
			dex++;
			if (dex >= list.info.length()) {
				dex = 0;
				if(list.next == null) throw new IndexOutOfBoundsException();
				list = list.next;
			}
		}

		myIndex = count;
		myLocalIndex = dex;
		myCurrent = list;
		
		return list.info.charAt(dex);
	}
	/*
	 * Method appends all the info from each node of the LinkedList
	 * using StringBuilder, then
	 * @returns the String created form StringBuilder
	 */
	@Override
	public String toString() {
		StringBuilder str = new StringBuilder("");
		Node start = myFirst;
		while(start != null) {
			str.append(start.info);
			start = start.next;
		}
		return str.toString();
	}
	
}
