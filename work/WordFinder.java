// Name: Zhien Yu
// USC NetID: zhienyu 
// CS 455 PA4
// Fall 2023
import java.io.*;
import java.util.Scanner;

/**
   WordFinder class is to prompt the Rack game 
   to print the Anagram words for the letters on rack.
   The client can provide a dictionary they want to use. 
   If not, the default dictionary "sowpods.txt" will be using.
   Only letter on the rack will be processing. 
   Client can ran it in the console. Follow the prompt to use the program. 
   While there are no such file for the dictionary or there are duplicated words,
   exception will be thrown. 
 */

public class WordFinder {
   /**
      Representation invariant:
      user can input symbols, but only letters will be processed. 

   */
   public static void main(String[] args) {
      // default dictionary is "sowpods.txt"
      String dictionaryFile = "sowpods.txt";
      AnagramDictionary myDictionary;
      if(args.length > 0){
         // if we have a input dictionary, use the input one
         dictionaryFile = args[0];
      }
      try{
         // initialize the Dictionary
         myDictionary = new AnagramDictionary(dictionaryFile);
      }catch(FileNotFoundException exception){
         // if exception, Exit program
         System.out.println("Exiting program.");
         return ; 
         
      }catch(IllegalDictionaryException exception){
         // if exception, Exit program
         System.out.println(exception.getMessage());
         System.out.println("Exiting program.");
         return ;
         
      }
      
      Scanner in = new Scanner(System.in);
      // if input ., quit the program
      System.out.println("Type . to quit.");
      Rack myRack;
      while(in.hasNext()){
         // if client type . , quit
         String input = in.next();
         System.out.print("Rack? ");
         if(input.equals(".")){
            return ;
         }else{
            // print all the subsets
            myRack = new Rack(input, myDictionary);
            myRack.printAllSubsets();
         }
         
         
      }
      
   }
   
   
}