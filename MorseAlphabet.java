import java.util.HashMap;

class MorseAlphabet {
  private static HashMap<String, String> mapMorseToText = new HashMap<>();
  private static HashMap<String, String> mapTextToMorse = new HashMap<>();
  private static final String[] ENG_UPPERCASE_ALPHABET= "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
  private static final String[] MORZE_LETTERS_CODE = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
  private static final String[] MORSE_NUMBERS_CODE = {"-----", ".----","..---", "...--", "....-", ".....", "-....", "--...", "---..", "----."};

  MorseAlphabet() {
    // Generating maps for letters
    for (int i = 0; i < MORZE_LETTERS_CODE.length; i++) {
      mapMorseToText.put(MORZE_LETTERS_CODE[i], ENG_UPPERCASE_ALPHABET[i]);
      mapTextToMorse.put(ENG_UPPERCASE_ALPHABET[i], MORZE_LETTERS_CODE[i]);
    }
    // Generating maps for numbers
    for (int i = 0; i < MORSE_NUMBERS_CODE.length; i++) {
      mapMorseToText.put(MORSE_NUMBERS_CODE[i], i + "");
      mapTextToMorse.put(i + "", MORSE_NUMBERS_CODE[i]);
    }
    // Special "SOS" command code
    mapMorseToText.put("...---...", "SOS");
    mapTextToMorse.put("SOS", "...---...");
  }

  public String getSymbol(String charCode) {
    return mapMorseToText.containsKey(charCode)
      ? mapMorseToText.get(charCode)
      : " Incorrect Morse Code! ";
  }

  public String getCode(String symbol) {
    return mapTextToMorse.containsKey(symbol)
      ? mapTextToMorse.get(symbol)
      : " Incorrect text! ";
  }
};