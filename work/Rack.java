// Name: Zhien Yu
// USC NetID: zhienyu 
// CS 455 PA4
// Fall 2023

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

/**
   A Rack of Scrabble tiles.
   This class creat and can prints all the legit subsets for a rack.
   Clients can creat a Rack based on their letters on Rack. 
   This class can prints all the subsets of words based on letters and given dictionary. 
   The score will be shown too. 
   It uses a ScoreTable to check the score. 
   To use it, new a object of Rack with letters and AnagramDictionary.
 */

public class Rack {
   /**
      Representation invariant:
      letters for the constructor can contains letters and non-letters, 
      but only letters would be saved.
      rackMult.length must be at least as big as rackUnique.length()

   */
   
   // rackUnique stores the unique letters
   
   String rackUnique;
   
   // rackMult stores the multiplicity of each letter from rackUnique
   int[] rackMult;
   
   // store the original word
   String rackWord;
   // Dictionary we will be using 
   AnagramDictionary dictionary;
   // scoretable we will be using 
   ScoreTable scoreTable; 
   
   /**
      Create a Rack that has the letters with the given string.
      @param letters store all letters in rack
    */
   public Rack(String letters, AnagramDictionary dictionary){
      // eliminate all the non-letters char
      String lettersOnlyAlpha = letters.replaceAll("[^a-zA-Z ]", "");;
      
      rackMult = new int[lettersOnlyAlpha.length()];
      rackWord = letters;
      this.dictionary = dictionary;
      scoreTable = new ScoreTable();
      rackUnique = "";
      // total number of chars that have been counted
      int numChar = 0;
      for(int i = 0; i < lettersOnlyAlpha.length(); i++){
         int ind = rackUnique.indexOf(lettersOnlyAlpha.charAt(i));
         // if the char exists
         if(ind >= 0){
            
            rackMult[ind] = rackMult[ind] + 1;
            
         }else{
            // if not, add the char in
            rackUnique += lettersOnlyAlpha.charAt(i);
            rackMult[numChar] = rackMult[numChar] + 1;
            numChar++;
            
         }
         
      }
      
   }
   
   
   /**
      Finds all subsets of the multiset which are in the dictionary. 
      The score and the words will be printed line by line. 
      Also, it will print the total number of words and the sorting method. 
      Nothing will be changed. 
      
      @return Nothing.
    */
   public void printAllSubsets(){
      // find all the subsets that the given Rack can have
      ArrayList<String> subSets = allSubsets(rackUnique, rackMult, 0);
      // the legit subsets and the scores will be stored in the wordSets
      TreeMap<String, Integer> wordSets = new TreeMap<>();
      // for all the words in the subsets
      for(int i = 0; i < subSets.size(); i++){
         String word = subSets.get(i);
         // find all the anagrams
         ArrayList<String> anagrams = dictionary.getAnagramsOf(word);
         // calculate the score, all the Anagrams will have same score
         int score = scoreTable.getWordScore(subSets.get(i));
         for(int j = 0; j < anagrams.size(); j++){
            
            // put the anagrams and the score. 
            wordSets.put(anagrams.get(j), score);
         }
      }
      
      // sort the Map to print in the order of the DESC of score
      ArrayList<Map.Entry<String, Integer>> arrayListFroSort = new ArrayList<>(wordSets.entrySet());
      scoreComparator myComparator = new scoreComparator();
      Collections.sort(arrayListFroSort, myComparator);
      
      
      int numWords = wordSets.size();
      System.out.println("We can make " +numWords + " words from \"" + rackWord + "\"");
      if(numWords != 0){
         
         System.out.println("All of the words with their scores (sorted by score):");
      }
      // prints all the words
      for (Map.Entry<String, Integer> entry : arrayListFroSort){
         System.out.println(entry.getValue()+ ": " + entry.getKey() );
         
      }
      
     
      
   }



   /**
      Finds all subsets of the multiset starting at position k in unique and mult.
      unique and mult describe a multiset such that mult[i] is the multiplicity of the char
           unique.charAt(i).
      PRE: mult.length must be at least as big as unique.length()
           0 <= k <= unique.length()
      @param unique a string of unique letters
      @param mult the multiplicity of each letter from unique.  
      @param k the smallest index of unique and mult to consider.
      @return all subsets of the indicated multiset.  Unlike the multiset in the parameters,
      each subset is represented as a String that can have repeated characters in it.
      @author Claire Bono
    */
   private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
      ArrayList<String> allCombos = new ArrayList<>();
      
      if (k == unique.length()) {  // multiset is empty
         allCombos.add("");
         return allCombos;
      }
      
      // get all subsets of the multiset without the first unique char
      ArrayList<String> restCombos = allSubsets(unique, mult, k+1);
      
      // prepend all possible numbers of the first char (i.e., the one at position k) 
      // to the front of each string in restCombos.  Suppose that char is 'a'...
      
      String firstPart = "";          // in outer loop firstPart takes on the values: "", "a", "aa", ...
      for (int n = 0; n <= mult[k]; n++) {   
         for (int i = 0; i < restCombos.size(); i++) {  // for each of the subsets 
                                                        // we found in the recursive call
            // create and add a new string with n 'a's in front of that subset
            allCombos.add(firstPart + restCombos.get(i));  
         }
         firstPart += unique.charAt(k);  // append another instance of 'a' to the first part
      }
      
      return allCombos;
   }

   
}


/**
   This scoreComparator helps to sort the wordset for a desc order of score. 
   It implements the Comparator.
   This class is for sorting based on Integer. 
   Client can use it by new a scoreComparator object then 
   use Collection.sort(ArrayList<Map.Entry<String, Integer>> arr, new scoreComparator());
 */
class scoreComparator implements Comparator<Map.Entry<String, Integer>> { 
   /**
      Representation invariant:
      all pairs of <String, Integer> are allowed. 
      

   */
   public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) { 
         return  e2.getValue() - e1.getValue() ;
   } 
}
