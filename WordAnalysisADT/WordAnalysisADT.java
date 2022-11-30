

	import java.io.*;
	import java.util.Scanner;

	public class WordAnalysisADT {
		LinkedList<WordInformation>[] arrayOfDifferentLengths;
		// number of all words
		int n;
		// number of unique words
		int m;
		WordInformation sortedArray[];
		
		// reading 
		
		public void readFileAndAnalyse(String path) throws IOException {
			//Getting arrayOfDifferentLengths Size
			String longest = "";
			String current;
			Scanner f1 = new Scanner (new File(path));
			while(f1.hasNext()) {
				current = f1.next();
				current = current.replaceAll("[^a-zA-Z]", "").replaceAll("\\s+", "");
				if(current.length() > longest.length())
					longest = current;
			}
			int k = longest.length();
			arrayOfDifferentLengths = (LinkedList<WordInformation>[]) new LinkedList<?>[k+1];
			
			
			
			//Storing
			
			int linecounter = 1, wordcounter = 1;
			Scanner file=new Scanner (new File(path));
			String wordList [] = null;
			while(file.hasNextLine()){
				String word = file.nextLine().replaceAll("\\s+", " ");
				wordList = word.split(" ");
				for(int i = 0; i < wordList.length; i++) {
					wordList[i] = wordList[i].replaceAll("[^a-zA-Z]", "");
					word = wordList[i].toLowerCase();////////
					boolean flag = false;
					WordInformation tmp = new WordInformation(word, linecounter, wordcounter);
					LinkedList<WordInformation> t = arrayOfDifferentLengths[word.length()];
					if(t == null) {
						arrayOfDifferentLengths[word.length()] = new LinkedList<WordInformation>();
						arrayOfDifferentLengths[word.length()].insert(tmp);
					}
					else {
						t.findfirst();
						while(t.last() == false) {
							if(t.retrieve().getWord().equals(word)) {///
								tmp = t.retrieve();
								t.retrieve().occList.insert(new WordOccurrence(linecounter, wordcounter));
								arrayOfDifferentLengths[word.length()].setSize();/////
								flag = true;
							}
							t.findnext();
						}
						if(t.retrieve().getWord().equals(word)) {
							tmp = t.retrieve();
							t.retrieve().occList.insert(new WordOccurrence(linecounter, wordcounter));
							arrayOfDifferentLengths[word.length()].setSize();////
							flag = true;
						}
						if(flag != true) {
							t.insert(tmp);
						}
					}
					if(tmp.occList.getSize() == 1)
						m++;
					n++;
					flag = false;	
					wordcounter++;
				}
				linecounter++;
				wordcounter = 1;
			}
			
			//Storing in Sorted
			sortedArray = new WordInformation[m];
			int count = 0;
			for(int i = 1; i < arrayOfDifferentLengths.length; i++) {
				//arrayOfDifferentLengths[i]
				if(arrayOfDifferentLengths[i] == null)
					continue;
				LinkedList<WordInformation> tmp = arrayOfDifferentLengths[i];
				tmp.findfirst();
				while(tmp.last() == false) {
					sortedArray[count++] = tmp.retrieve();
					tmp.findnext();
				}
				sortedArray[count++] = tmp.retrieve();
			}
			
			//Sorting sorted array
			
			for(int i = 0; i < m - 1; i ++) {
				for(int j = 0; j < m - 1 - i ; j ++) {
					if(sortedArray[j].getSize() < sortedArray[j + 1].getSize()) {
						WordInformation tmp = sortedArray[j];
						sortedArray[j] = sortedArray[j + 1];
						sortedArray[j + 1] = tmp;
						
					}
				}
			}
			
			
			file.close();
		}

		// operation 1
		public int documentLength() { // O(1)
			return n;
		}
		// operation 2
		public int uniqueWords() { // O(1)
			return m;
		}
		// operation 3
		public int totalOccurancesForWord(String w) { // case1: O(m/k) case2:  O(n) or O(m)
			//cleaning the words from Punctuation etc...
			w = w.replaceAll("[^a-zA-Z]", "");
			if(w.length() > 0 && w.length() < arrayOfDifferentLengths.length) {
				LinkedList<WordInformation> l = arrayOfDifferentLengths[w.length()];
				if(l == null||l.empty())
					return 0;
				l.findfirst();
				while(!l.last()) {
					if(l.retrieve().getWord().equals(w))
						return l.retrieve().getSize();
					l.findnext();
				}
				if(l.retrieve().getWord().equals(w))
					return l.retrieve().getSize();
			
			}
			//Not Found
			return 0;
		}
		// operation 4
		public int totalWordsForLength(int l) { // O(1) both cases
			if(l > 0 && l < arrayOfDifferentLengths.length) {
				if(arrayOfDifferentLengths[l] == null)
					return -1;
				return arrayOfDifferentLengths[l].getSize();
			}
			return-1;
		}
		// operation 5
		public void displayUniqueWords() { // O(m)
			for(int i = 0; i < sortedArray.length-1; i++)
				System.out.print("("+sortedArray[i].getWord()+", "+sortedArray[i].getSize()+"), ");
			// If the txt file only have empty
			if(sortedArray.length != 0)
				System.out.println("("+sortedArray[sortedArray.length-1].getWord()+", "+sortedArray[sortedArray.length-1].getSize()+")");
			else
				System.out.println("N/A");
		}
		// operation 6
		public LinkedList<WordOccurrence> occurrences(String w) { // O(n)
			//cleaning the words from Punctuation etc...
			w = w.replaceAll("[^a-zA-Z]", "");
			
			LinkedList<WordOccurrence> tmp = null;
			if(w.length() > 0 && w.length() < arrayOfDifferentLengths.length) {
				LinkedList<WordInformation> l = arrayOfDifferentLengths[w.length()];
				if(l == null||l.empty())
					return null;
				l.findfirst();
				while(!l.last()) {
					if(l.retrieve().getWord().equals(w)) {
						tmp = l.retrieve().occList;
						break;
					}
					l.findnext();
				}
				if(l.retrieve().getWord().equals(w))
					tmp = l.retrieve().occList;
			}
			if(tmp == null)
				return null;
			return tmp;

		}
		// operation 7	
		public boolean checkAdjacent(String w1, String w2) { // O(n)
			//cleaning the words from Punctuation etc...
			w1 = w1.replaceAll("[^a-zA-Z]", "");
			w2 = w2.replaceAll("[^a-zA-Z]", "");

			//Finding the words
			LinkedList<WordOccurrence> w1occ = occurrences(w1);
			LinkedList<WordOccurrence> w2occ = occurrences(w2);
			// word not found
			if(w1occ == null || w2occ == null)
				return false;
			
			boolean flag = false;
			boolean flagw1 = false;
			boolean flagw2 = false;

			w2occ.findfirst();
			w1occ.findfirst();
			
			/*
			 * In Merge Sort if the first list is finished its going to add the second list'
			 * same way here if first finished we going to check the second list
			 */
			
			
			// its a bit strange but if the same word occur twice example (omar data data java test), checkAdjacent("data","data")
			if(w1occ.equals(w2occ)) {
				WordOccurrence tmp = w1occ.retrieve();
				while(!w1occ.last()) {
					w1occ.findnext();
					if(tmp.getLineNum() == w1occ.retrieve().getLineNum() &&
							(tmp.getPos() - w1occ.retrieve().getPos() == 1 
							|| tmp.getPos() - w1occ.retrieve().getPos() == -1)) {
						return true;
					}
					tmp = w1occ.retrieve();
				}
				if(tmp.getLineNum() == w1occ.retrieve().getLineNum() &&
						(tmp.getPos() - w1occ.retrieve().getPos() == 1 
						|| tmp.getPos() - w1occ.retrieve().getPos() == -1)) {
					return true;
				}
				return false;
			}
			
			
			while(!flag) {
				
				//Check if last element in both list
				if(w1occ.last() && w2occ.last()) {
					flagw1 = true;
					flagw2 = true;
					break;
				}
				//Check if last element in first list
				if(w1occ.last()) {
					flagw1 = true;
					break;
					}
				//Check if last element in last list
				if(w2occ.last()) {
					flagw2 = true;
					break;
				}
				//Not in the same line
				/*
				 * So if first list line number is bigger we move second list until
				 *  they have same line number.
				 *  same with second list
				 */
				if(w1occ.retrieve().getLineNum() > w2occ.retrieve().getLineNum()) {
					w2occ.findnext();
					continue;
				}
				else if (w1occ.retrieve().getLineNum() < w2occ.retrieve().getLineNum()) {
					w1occ.findnext();
					continue;
				}
				else {
					
					 // Now they have same line number we just check if they are adjacent or not
					 
					if(w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == 1 
							|| w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == -1) {
						return true;
					}
					/* 
					 * its hard to explain these lines but take this example check(data,of)
					 * because w2 >w1 we only move w1
					 * so now the adjacent
					 * 1: w1          w2
					 * 2:         w1  w2
					 *   data ok data of are of
					 *   
					 *  and the second to check if its last becaue if data in other line it will move twice
					 *  and it might be last word so its going to be current.null 
					 *  thats why
					 */
					if(w1occ.retrieve().getPos() > w2occ.retrieve().getPos() && !w2occ.last())
						w2occ.findnext();
					if(w1occ.retrieve().getPos() < w2occ.retrieve().getPos() && !w1occ.last())
						w1occ.findnext();
				}
			}
			
			if(flagw1 && flagw2) {
				if(w2occ.retrieve().getLineNum() == w1occ.retrieve().getLineNum() &&
						w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == 1 
						|| w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == -1) 
					return true;
				return false;
			}
			else if(flagw1){
				while(!w2occ.last()) {
					if(w2occ.retrieve().getLineNum() == w1occ.retrieve().getLineNum() &&
							(w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == 1 
							|| w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == -1)) {
						return true;	
					}
					w2occ.findnext();
				}
				if(w2occ.retrieve().getLineNum() == w1occ.retrieve().getLineNum() &&
						(w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == 1 
						|| w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == -1)) 
					return true;
				return false;
			}
			else if(flagw2){
				while(!w1occ.last()) {
					if(w2occ.retrieve().getLineNum() == w1occ.retrieve().getLineNum() &&
							(w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == 1 
							|| w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == -1)) {
						return true;	
					}
					w1occ.findnext();
				}
				if(w2occ.retrieve().getLineNum() == w1occ.retrieve().getLineNum() &&
						(w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == 1 
						|| w2occ.retrieve().getPos() - w1occ.retrieve().getPos() == -1)) 
					return true;
				return false;
			}
			
			return flag;
		}
		

	}



