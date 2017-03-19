package keygenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Generate a list of random keys
 * 
 * @author Jeff Manning
 * @version 1.0.0
 */
public class GenerateKeys
{
    public static final int DEFAULT_NUMBER_OF_KEYS = 1000;
    public static final int DEFAULT_KEY_LENGTH = 10;
    public static final String DEFAULT_KEY_CHOICES = "abcdefghijklmnopqrstuvwxyz01234567890";
    
    private List<String> keys;
    
    /**
     * Generate a new set of keys using default values
     */
    public GenerateKeys() {
        this.generator(DEFAULT_NUMBER_OF_KEYS,DEFAULT_KEY_LENGTH, DEFAULT_KEY_CHOICES);
    }
    
     public GenerateKeys(int numKeys) {
        this.generator(numKeys, DEFAULT_KEY_LENGTH, DEFAULT_KEY_CHOICES);
    }
    
    public GenerateKeys(int numKeys, int keyLength) {
        this.generator(numKeys, keyLength, DEFAULT_KEY_CHOICES);
    }
    
    public GenerateKeys(int numKeys, int keyLength, String choices) {
        this.generator(numKeys, keyLength, choices);
    }
    
   private void generator(int numKeys, int keyLength, String choices) {
       Random pos = new Random();
        this.keys = new ArrayList<>();
        String[] choiceArray = this.RandomizeArray(choices.split(""));
        int a = 0,b = 0;
        while(a < numKeys) {
            String newKey = "";
            for(int len = 0;len < keyLength;len++) {
                newKey += choiceArray[pos.nextInt(choiceArray.length)];
            }
            if(!this.keys.contains(newKey)) {
               this.keys.add(newKey);
                a++;
            }
            b++; //sanity check, this should not exceed numKeys*3
            if(b > (numKeys*3)) {
                System.out.println("Terminating process...");
                break;
            }
        }
   }
   private  String[] RandomizeArray(String[] array){
        Random rgen = new Random();  // Random number generator         

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            String temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
    
    /**
     * return the raw list of keys
     * @return 
     */
    public List<String> getKeys() {
        return keys;
    }
    
    /**
     * return this list of keys separated by separator specified at every position number of characters
     * @param seperator
     * @param position
     * @return 
     */
    public List<String> getKeys(String seperator, int position) {
       List<String> formattedKeys = new ArrayList<>();
       
        if(position >= this.keys.get(0).length()) {
            return keys; //can't seperate a string that is to short
        }
        
        ListIterator<String> key = this.keys.listIterator();
        
        while(key.hasNext()) {
            String formattedKey = "";
            String currentKey = key.next();
            int a = 0;
            while(true) {
                 if((a+position) > currentKey.length()) {
                     formattedKey += currentKey.substring(a,currentKey.length()) + seperator;
                     break;
                 }
                 else {
                     formattedKey += currentKey.substring(a,a+position) + seperator;
                     a+=position;
                 }  
            }
            formattedKeys.add(formattedKey.substring(0,formattedKey.length()-2));
        } 
        return formattedKeys;
    }
}