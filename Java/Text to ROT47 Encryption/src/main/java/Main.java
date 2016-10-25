import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.Scanner;

/**
 * Created by giovannilenguito on 25/10/2016.
 */
public class Main {
    private static Scanner sc;

    public static void main(String args[]){
        sc = new Scanner(System.in);
        String inputText;

        boolean isComplete = false;

        while(!isComplete) {
            System.out.println("Please enter the text you want to encrypt with ROT47:");

            inputText = sc.nextLine();

            if(inputText != ""){
                System.out.println("Result:");
                System.out.println(ROT47(inputText));

                System.out.println("Would you like to ROT47 another string? Y/N");
                String optionInput = sc.nextLine();
                if(optionInput.equalsIgnoreCase("N")){
                    System.out.println("Process ended");
                    isComplete = true;
                }

            }
        }
    }

    private static String ROT47(String inputText){
        int textLength = inputText.length();
        StringBuilder result = new StringBuilder();

        for(int i = 0; i < textLength; i++){
            //get the character from the current iteration
            char character = inputText.charAt(i);

            if(character != ' '){
                character += 47;
                if(character > '~'){
                    character -= 94;
                }
            }

            result.append(character);
        }

        return result.toString();
    }
}
