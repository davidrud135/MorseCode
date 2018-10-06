import java.io.*;
import java.util.ArrayList;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

public class MorseCode {
  public static void main(String[] args) throws IOException {
    MorseCode morseCode = new MorseCode();
    BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
    boolean repeatMenu = true;
    String userMethod;
    do {
      morseCode.showMenu();
      userMethod = consoleReader.readLine().trim();
      if (userMethod.equals("1")) {
        System.out.println("Write Morse Code which will be decoded to normal text and press Enter:");
        String userMorseCode = consoleReader.readLine().trim();
        String resultText = morseCode.decodeFromMorseCode(userMorseCode);
        System.out.println(resultText);
      } else if (userMethod.equals("2")) {
        System.out.println("Write text which will be encoded to the Morse Code and press Enter:");
        String userText = consoleReader.readLine().trim().toUpperCase();
        String resultCode = morseCode.encodeToMorseCode(userText);
        morseCode.copyToClipboard(resultCode);
        System.out.println(resultCode);
        System.out.println("(result automatically copied to clipboard)");
      } else {
        repeatMenu = false;
      }
    } while (repeatMenu);
  }

  public void showMenu() {
    System.out.println(" ____________________________");
    System.out.println("|                            |");
    System.out.println("| 1 - Decode from Morse Code |");
    System.out.println("| 2 - Encode to Morse Code   |");
    System.out.println("| 3 - Exit                   |");
    System.out.println("|____________________________|");
  }

  public String encodeToMorseCode(String userText) {
    MorseAlphabet morseAlphabet = new MorseAlphabet();
    String tripleSpace = "   ";
    String[] userWords = userText.split(" ");
    ArrayList<String> resultWordsCode = new ArrayList<>();

    for (String word : userWords) {
      String[] wordChars = word.split("");
      ArrayList<String> wordCode = new ArrayList<>();
      for (String symbol : wordChars) {
        String encodedCharCode = morseAlphabet.getCode(symbol);
        wordCode.add(encodedCharCode);
      }
      resultWordsCode.add(String.join(" ", wordCode));
    }
    String resultMorseCode = String.join(tripleSpace, resultWordsCode);
    return resultMorseCode;
  }


  public String decodeFromMorseCode(String userMorseCode) {
    MorseAlphabet morseAlphabet = new MorseAlphabet();
    String tripleSpace = "   ";
    String[] userWordsCode = userMorseCode.split(tripleSpace);
    ArrayList<String> resultWords = new ArrayList<>();

    for (String word : userWordsCode) {
      String[] wordSymbolsCode = word.split(" ");
      ArrayList<String> decodedWord = new ArrayList<>();
      for (String symbolCode : wordSymbolsCode) {
        String decodedChar = morseAlphabet.getSymbol(symbolCode);
        decodedWord.add(decodedChar);
      }
      resultWords.add(String.join("", decodedWord));
    }
    String resultText =  String.join(" ", resultWords);
    return resultText;
  }

  public void copyToClipboard(String text) {
    StringSelection stringSelection = new StringSelection(text);
    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
    clipboard.setContents(stringSelection, null);
  }
};