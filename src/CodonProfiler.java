import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * To do so, a HashMap is created where the keys are all possible codons
	 * in the IDNAStrand strand and the values are the frequencies of the codons.
	 * Then, for each codon in String[] codons, the value from the HashMap is
	 * retrieved and put into the return array.
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>();
		String str = strand.toString();
		for(int i = 0; i < str.length()-2; i+=3) {
			String cod = str.substring(i, i+3);
			if(!map.containsKey(cod)) {
				map.put(cod, 0);
			}
			map.put(cod, map.get(cod)+1);
		}
		
		int[] ret = new int[codons.length];
		for(int k = 0; k < codons.length; k++) {
			if(map.containsKey(codons[k])) {
				ret[k] = map.get(codons[k]);
			}
			else {
				ret[k] = 0;
			}
		}
		
		return ret;
	}
}
