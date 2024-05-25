// Name: Zhien Yu
// USC NetID: zhienyu 
// CS 455 PA4
// Fall 2023

import java.util.ArrayList;

/**
   A ScoreTable which stores score for each letter.
   Upper case letter will have same score as lower case letter. 
   It can tell score of words. Only lettes would have score.
   Client can use it by new a ScoreTable object. 
   The corresponding scores of words can be returened by getter.
   This class is imutable. 
 */

public class ScoreTable {
   /**
      Representation invariant:
      only letters would have score. 
      scores.length() = 26
   */
   
   // the possible scores 
   private final int ONE_POINT = 1;
   private final int TWO_POINT = 2;
   private final int THREE_POINT = 3;
   private final int FOUR_POINT = 4;
   private final int FIVE_POINT = 5;
   private final int EIGHT_POINT = 8;
   private final int TEN_POINT = 10;
   
   private int[] scores; 
   /**
      Create a ScoreTable that stores the score of letters in a array.
      The index of the array corresponding to the rank of letters in alphabetical order. 
      
    */
   public ScoreTable(){
      
      scores = new int[26];
      // A, E, I, O, U, L, N, S, T, R -> 1
      scores[0] = ONE_POINT;
      scores[4] = ONE_POINT;
      scores[8] = ONE_POINT;
      scores[14] = ONE_POINT;
      scores[20] = ONE_POINT;
      scores[11] = ONE_POINT;
      scores[13] = ONE_POINT;
      scores[18] = ONE_POINT;
      scores[19] = ONE_POINT;
      scores[17] = ONE_POINT;
      // D, G -> 2
      scores[3] = TWO_POINT;
      scores[6] = TWO_POINT;
      // B, C, M, P -> 3
      scores[1] = THREE_POINT;
      scores[2] = THREE_POINT;
      scores[12] = THREE_POINT;
      scores[15] = THREE_POINT;
      // F, H, V, W, Y -> 4
      scores[5] = FOUR_POINT;
      scores[7] = FOUR_POINT;
      scores[21] = FOUR_POINT;
      scores[22] = FOUR_POINT;
      scores[24] = FOUR_POINT;
      // K -> 5
      scores[10] = FIVE_POINT;
      // J, X -> 8
      scores[9] = EIGHT_POINT;
      scores[23] = EIGHT_POINT;
      // Q, Z -> 10
      scores[16] = TEN_POINT;
      scores[25] = TEN_POINT;
   }
   
   
   /**
      Get the corresponding score of a word.
      The upper case and lower case letters would have same score. 
      Does not change anything.
      
      @param word is the word that we want to get the score.
         PRE: word can only contains letters. 
      @return a int that is the corresponing score. 
      
    */
   public int getWordScore(String word){
      // add up all the scores in the int score. 
      int score = 0;
      for(int i = 0; i < word.length(); i++){
         // add up all the scores for a word
         score += getScore(word.charAt(i));
      }
      return score;
   }
   
   /**
      Get the corresponding score of given letter.
      The upper case and lower case letters would have same score. 
      Does not change anything.
      
      @param letter is the letter we want to get the score.
         PRE: letter can only contains letters. No symbols. 
      @return a int that is the corresponing score. 
    */
   private int getScore(char letter){
      
      // turn all the letters to lower index
      char letterLower = Character.toLowerCase(letter);
      // find the index based on letter a
      int ind = letterLower - 'a';
      return scores[ind];
   }
   
   
}