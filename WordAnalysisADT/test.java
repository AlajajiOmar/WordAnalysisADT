
import java.io.IOException;

public class test {

	public static void main(String[] args) throws IOException {


		WordAnalysisADT w = new WordAnalysisADT();
		w.readFileAndAnalyse("C:\\Users\\User\\eclipse-workspace\\phase2\\src\\phase2\\info.txt");
		WordInformation[] t = w.sortedArray;

		System.out.println("Operation 1: "+ w.documentLength());
		System.out.println("Operation 2: "+ w.uniqueWords());
		System.out.println("Operation 3: "+ w.totalOccurancesForWord("abdullah"));
		System.out.println("Operation 4: "+ w.totalWordsForLength(3));
		System.out.print("Operation 5: "); w.displayUniqueWords();
		System.out.print("Operation 6: ");
		LinkedList<WordOccurrence> occ = w.occurrences("abdullah");
		if(occ == null)
			System.out.println("N/A");
		else {
			occ.findfirst();
			while(!occ.last()) {
				System.out.print("("+occ.retrieve().getLineNum()+", "+occ.retrieve().getPos()+"), ");
				occ.findnext();
			}
			System.out.println("("+occ.retrieve().getLineNum()+", "+occ.retrieve().getPos()+")");
		}
		System.out.println("Operation 7: "+ w.checkAdjacent("mohammad", "ali"));

		
	}



	}


