

public class WordInformation {
	 public String word;
	 public LinkedList<WordOccurrence> occList = new LinkedList<WordOccurrence>();
	 public int size;
	 
	 public WordInformation(String w, int l, int p) {
		 word = w;
		 occList.insert(new WordOccurrence(l, p));
	 }
	 public String getWord() {
		 return word;
	 }
	 public LinkedList<WordOccurrence> getOccurrences(){
		 return occList;
	 }
	 public int getSize() {
		 size = occList.getSize();
		 return size;
	 }


}
