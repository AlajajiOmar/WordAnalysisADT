# WordAnalysisADT
It was my Project for Data Structure course at King Saud University Using JAVA

In this project, we made a program that can analyze data and perform specific giving operations by the Professor using only LinkedList and without using libraries so basically making everything from scratch, and the operations need to have the same Big-Ohh. 

The Design:

  ![image](https://user-images.githubusercontent.com/72312883/204819036-fd8be949-34f2-43a8-bb59-68762052fd79.png)




The Operations are:
  1. Method readFileAndAnalyse (filename f)                                             
    Input: filename f                                    
    Requires: f should be an existing file with valid filename.                                    
    Results: This operation will open file f as input, read the text in the file word                                    
    by word and analyze the text as follows: When a word w of length x is read,                                    
    the list for words of length x is searched for word w. If w is found, a new node                                    
    is inserted for its occurrence, containing w’s line number and word position in                                    
    the line. If w is not found, a new node is inserted in the list for words of length                                    
    x, containing the string for the word and a new node is inserted for in its                                    
    occurrence list, containing w’s line number and word position.                                    
  
  2. Method documentLength (int l)                                    
    Results: The method will return the length of document (i.e., total number of                                    
    all words).
    Output: l
  
  3. Method uniqueWords (int u)                                    
    Results: The method will return the number of unique words in the document.                                    
    Output: u                                    
    
  4. Method totalOccurancesForWord (String w, int t)                                    
    Input: w                                    
    Results: The method will return the size of the occurrences list for a particular                                    
    word w.                                                                                                                                                
    Output: t                                                                                                           
    
  5. Method totalWordsForLength (int l, int t)                                                                        
    Input: l                                                                                                                                                
    Results: The method will return the size of the word list for words with length                                                                        
    l including duplicates.                                                                        
    Output: t                                                                        
                                        
  6. Method displayUniqueWords ()                                                                                                            
    Results: The method will display the unique words in the file sorted by the                                    
    total occurrences of each word (from the most frequent to the least). If no                                    
    sorting is performed during the readFileAndAnalyse operation, then this                                    
    method shall perform sorting of the unique words (extra cost).                                    
    
  7. Method occurrences (String w, LinkedList<WordOccurence> l)                                    
    Input: w                                    
    Results: The method will check the length of word w and then the word is                                    
    searched in the word list of that length. If the word is not found, then null is                                    
    returned otherwise the occurrence list for the word is returned as l.                                    
    Output: l                                    
    
  8. Method checkAdjacent (String w1, String w2, boolean res)                                    
    Input: w1, w2                                    
    Results: The method will check if both word w1 and word w2 are adjacent to                                    
    each other by retrieving the occurrence information for each word and                                    
    verifying the positions of both words. If they are adjacent, then return true                                    
    otherwise return false (use a way similar to merge in merge-sort to perform it                                    
    in O(n)).                                    
    Output: res                                    
    


Assume that the length of the text                                    
file is n, the number of unique words is m, and the longest word in the file has a                                    
length of k characters.                                    

The time complexity (worst case analysis) for all the operations discussed                                                                        
above using Big O notation but in operation 3 and 4 the Professor asked us to answer two cases: the                                    
first case, when the words in the text file have lengths that are evenly distributed                                    
among different lengths (i.e., the words should have different lengths starting from                                    
1 to the longest with k characters), and the second case, when the lengths of words                                    
are not evenly distributed (also we are not going to include reading):                                    

  1 = O(1)                                                                         
  2 = O(1)                                                                        
  3 = Case 1 = O(m/k), Case 2 = O(m) or O(n)                                                                        
  4 = Case 1= O(1), Case 2 = O(1)                                                                         
  5 = O(m)                                                                        
  6 = O(n)                                                                        
  7 = O(n)                                                                        
  
