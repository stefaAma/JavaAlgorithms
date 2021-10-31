package StringAlgorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {

    private static String longestSubstring(String string) {
        Map<Character, Integer> characterMap = new HashMap<>();
        int final_i = 0, final_j = -1, i = 0, j = 0;
        while (j < string.length()) {
            if (characterMap.containsKey(string.charAt(j)))
                i = Math.max(i, characterMap.get(string.charAt(j)));
            if (j - i > final_j - final_i) {
                final_i = i;
                final_j = j;
            }
            characterMap.put(string.charAt(j), j + 1);
            j++;
        }
        return string.substring(final_i, final_j + 1);
    }

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Insert a string:  ");
        try {
            String string = in.readLine();
            String substring = longestSubstring(string);
            if (!substring.equals(""))
                System.out.println("The longest substring without repeating characters is:  " + substring);
            else
                System.out.println("Empty String!");
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

}
