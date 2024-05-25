// Name: Zhien Yu
// USC NetID: zhienyu
// CS 455 PA4
// Fall 2023

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.HashMap;
import java.io.*;
import java.util.Scanner;


/**
   Anagram Dictioinary class
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
   
   Clients can use the class to generate a AnagramDictionary. 
   Clients can new a AnagramDictionary object with a file, 
   all the words with same composition will be stored 
   in a same arrayList correspond to a same key. 
   This class does not have any mutator. 
 */
public class AnagramDictionary {
   /**
      Representation invariant:
      The input file for the constructor should contains only letters. 
      Exception will be thrown if no such file or duplicated words in the file. 
      There should only be letters in the input string of the getter. 
   */
   
   // a HashMap to Store the dictionary word. 
   // The ket String will contains the sorted string 
   // the TreeSet will contains all Anagrams of the key String in the dictionary. 
   // private HashMap<String, TreeSet<String>> dictionary;
   private HashMap<String, ArrayList<String>> dictionary;



   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
      @throws IllegalDictionaryException  if the dictionary has any duplicate words
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException,
                                                    IllegalDictionaryException {
                                                       
      
      File inFile = new File(fileName);
      dictionary = new HashMap<>();
      // try to make a Scanner based on the inFile. 
      // if fail, catch exception.                                                 
      try{
         Scanner in = new Scanner(inFile);
         // while there are words in the inFile
         while(in.hasNext()){
            // make a sorted string based on the word
            String word = in.next();
            char wordSortTemp[] = word.toCharArray();
            Arrays.sort(wordSortTemp);
            // use it as a key
            String wordArranged = new String(wordSortTemp);
            // if the key already exists
            if(dictionary.keySet().contains(wordArranged)){
               if(dictionary.get(wordArranged).contains(word)){
                  // if the word showed up second time
                  // throw exception
                 
                  throw new IllegalDictionaryException("ERROR: Illegal dictionary: dictionary file has a duplicate word: " + word);
                  
               }
               
            }else{
               // if there are no such key, put it
               dictionary.put(wordArranged, new ArrayList<String>());
            }
            // add the word
            dictionary.get(wordArranged).add(word);
         }
      }catch (FileNotFoundException exception){
         // if no such file, throw exception
         System.out.println("ERROR: Dictionary file \"" + fileName + "\" does not exist.");
         throw exception;
      }
                                                       

   }
   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param string to process the Anagrams
         PRE: string should contains only letters.
      @return a list of the anagrams of s, empty arraylist no such Anagrams
    */
   public ArrayList<String> getAnagramsOf(String string) {
      // sort the given string
      char wordSortTemp[] = string.toCharArray();
      Arrays.sort(wordSortTemp);
      String wordArranged = new String(wordSortTemp);
      // find the corresponding arraylist. 
      // do a defensive copy.
      ArrayList<String> words = new ArrayList<String>();
      ArrayList<String> wordsAnas = dictionary.get(wordArranged);
      // if there are Anagrams, add them in
      // if not, return an empty ArrayList
      if(wordsAnas != null){
         for(int i = 0; i < wordsAnas.size(); i++){
         
            words.add(wordsAnas.get(i));
         }
         
         
      }
      
      return words; 
   }
   
   
}
