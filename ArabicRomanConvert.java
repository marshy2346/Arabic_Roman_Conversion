import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ArabicRomanConvert extends JFrame {
    private char[] arabicArray = new char[4];
    private JTextField arabicField = new JTextField();
    private JTextField romanField = new JTextField();
    private final JLabel arabicLabel = new JLabel("Arabic Numeral: ");
    private final JLabel romanLabel = new JLabel("Roman Numeral: ");

    public ArabicRomanConvert() {
        super("Arabic <--> Roman");
        setSize(300, 100);
        setLayout(new GridLayout(2, 2));
        add(arabicLabel);
        add(arabicField);
        add(romanLabel);
        add(romanField);
        arabicField.addKeyListener(new keyHandler());
        romanField.addKeyListener(new keyHandler());
    }

    /**
     * Converts the arabic number that as if it was in the one's place into the
     * equivalent roman character or characters
     * 
     * @param arabicNum
     *            The arabic number to be converted to Roman
     * @return Returns the Roman Numeral character equal to the arabic number
     */
    private String parseArabicOnes(int arabicNum) {
        if (arabicNum == 1) {
            return "I";
        } else if (arabicNum == 2) {
            return "II";
        } else if (arabicNum == 3) {
            return "III";
        } else if (arabicNum == 4) {
            return "IV";
        } else if (arabicNum == 5) {
            return "V";
        } else if (arabicNum > 5 && arabicNum < 9) {
            return parseArabicOnes(5) + parseArabicOnes(arabicNum - 5);
        } else if (arabicNum == 9) {
            return "IX";
        } else {
            return "";
        }
    }

    /**
     * Converts the arabic number that as if it was in the ten's place into the
     * equivalent roman character or characters
     * 
     * @param arabicNum
     *            The arabic number to be converted to Roman
     * @return Returns the Roman Numeral character equal to the arabic number
     */
    private String parseArabicTens(int arabicNum) {
        if (arabicNum == 1) {
            return "X";
        } else if (arabicNum == 2) {
            return "XX";
        } else if (arabicNum == 3) {
            return "XXX";
        } else if (arabicNum == 4) {
            return "XL";
        } else if (arabicNum == 5) {
            return "L";
        } else if (arabicNum > 5 && arabicNum < 9) {
            return parseArabicTens(5) + parseArabicTens(arabicNum - 5);
        } else if (arabicNum == 9) {
            return "XC";
        } else {
            return "";
        }
    }

    /**
     * Converts the arabic number that as if it was in the hundred's place into
     * the equivalent roman character or characters
     * 
     * @param arabicNum
     *            The arabic number to be converted to Roman
     * @return Returns the Roman Numeral character equal to the arabic number
     */
    private String parseArabicHund(int arabicNum) {
        if (arabicNum == 1) {
            return "C";
        } else if (arabicNum == 2) {
            return "CC";
        } else if (arabicNum == 3) {
            return "CCC";
        } else if (arabicNum == 4) {
            return "CD";
        } else if (arabicNum == 5) {
            return "D";
        } else if (arabicNum > 5 && arabicNum < 9) {
            return parseArabicHund(5) + parseArabicHund(arabicNum - 5);
        } else if (arabicNum == 9) {
            return "CM";
        } else {
            return "";
        }
    }

    /**
     * Converts the arabic number that as if it was in the thousand's place into
     * the equivalent roman character or characters
     * 
     * @param arabicNum
     *            The arabic number to be converted to Roman
     * @return Returns the Roman Numeral character equal to the arabic number
     */
    private String parseArabicThou(int arabicNum) {
        if (arabicNum == 1) {
            return "M";
        } else if (arabicNum == 2) {
            return "MM";
        } else if (arabicNum == 3) {
            return "MMM";
        } else {
            return "";
        }
    }

    private boolean isNumeric(String str) {
        char[] charArr = str.toCharArray();
        for (int i = 0; i < str.length(); i++) {
            if (charArr[i] < 48 || charArr[i] > 57) {
                return false;
            }
        }
        return true;
    }

    private boolean isRoman(String str) {
        char[] charArr = str.toCharArray();
        boolean trueRoman = false;
        for (int i = 0; i < str.length(); i++) {
            if (charArr[i] == 'M' || charArr[i] == 'm' || charArr[i] == 'D'
                    || charArr[i] == 'd' || charArr[i] == 'C'
                    || charArr[i] == 'c' || charArr[i] == 'L'
                    || charArr[i] == 'l' || charArr[i] == 'X'
                    || charArr[i] == 'x' || charArr[i] == 'V'
                    || charArr[i] == 'v' || charArr[i] == 'I'
                    || charArr[i] == 'i') {
                trueRoman = true;
            } else {
                return false;
            }
        }
        return trueRoman;
    }

    private class keyHandler implements KeyListener {

        /**
         * Function checks the source of the key event and has separate logic
         * for each scenario
         */
        @Override
        public void keyReleased(KeyEvent event) {
            String str = new String();
            if (event.getSource() == arabicField) {
                String tempRoman = new String();
                str = arabicField.getText();
                // Checks if the first character is a zero, if so deletes the
                // character
                for (int i = 0; i < str.length(); i++) {
                    while (str.length() > 0 && str.charAt(0) == '0') {
                        str = str.substring(1);
                        arabicField.setText(str);
                    }
                    // If: checks if the input is 4 or less characters, if not
                    // truncate to 4
                    // Else if: loops through to see if any character is not a
                    // number, if so remove the character
                    // Else if 2: Checks if the value of the string is greater
                    // 3999, if so delete the last character to make it smaller.
                    if (str.length() > 4) {
                        str = str.substring(0, 4);
                        arabicField.setText(str);
                    } else if (!isNumeric(str)) {
                        while (!isNumeric(str)) {
                            str = str.substring(0, str.length() - 1);
                            arabicField.setText(str);
                        }
                    } else if (str.length() > 0
                            && Integer.parseInt(str) > 3999) {
                        str = str.substring(0, 3);
                        arabicField.setText(str);
                    }
                }
                // Store the input string into a char array
                arabicArray = str.toCharArray();
                // checks the length of the input and calls the appropriate
                // Conversion function(s)
                if (str.length() == 1) {
                    tempRoman = parseArabicOnes(arabicArray[0] - '0');
                } else if (str.length() == 2) {
                    tempRoman = parseArabicTens(arabicArray[0] - '0')
                            + parseArabicOnes(arabicArray[1] - '0');
                } else if (str.length() == 3) {
                    tempRoman = parseArabicHund(arabicArray[0] - '0')
                            + parseArabicTens(arabicArray[1] - '0')
                            + parseArabicOnes(arabicArray[2] - '0');
                } else if (str.length() == 4) {
                    tempRoman = parseArabicThou(arabicArray[0] - '0')
                            + parseArabicHund(arabicArray[1] - '0')
                            + parseArabicTens(arabicArray[2] - '0')
                            + parseArabicOnes(arabicArray[3] - '0');
                }
                //Set the Roman text field to the newly converted string
                romanField.setText(tempRoman);
            } else if (event.getSource() == romanField) {
                str = romanField.getText().toUpperCase();
                int arabicValue = 0;
                while (!isRoman(str) && str.length() > 0) {
                    str = str.substring(0, str.length() - 1);
                    romanField.setText(str);
                }
                if (str.length() > 0) {
                    char[] charArr = str.toCharArray();
                    for (int i = 0; i < charArr.length; i++) {
                        if (charArr[i] == 'I') {
                            if ((i + 1) < charArr.length
                                    && charArr[i + 1] == 'V') {
                                arabicValue += 4;
                                i++;
                            } else if ((i + 1) < charArr.length 
                                    && charArr[i + 1] == 'X') {
                                arabicValue += 9;
                                i++;
                            } else {
                                arabicValue += 1;
                            }
                        } else if (charArr[i] == 'V') {
                            arabicValue += 5;
                        } else if (charArr[i] == 'X') {
                            if ((i + 1) < charArr.length
                                    && charArr[i + 1] == 'L') {
                                arabicValue += 40;
                                i++;
                            } else if ((i + 1) < charArr.length
                                    && charArr[i + 1] == 'C') {
                                arabicValue += 90;
                                i++;
                            } else {
                                arabicValue += 10;
                            }
                        } else if (charArr[i] == 'L') {
                            arabicValue += 50;
                        } else if (charArr[i] == 'C') {
                            if ((i + 1) < charArr.length
                                    && charArr[i + 1] == 'D') {
                                arabicValue += 400;
                                i++;
                            } else if ((i + 1) < charArr.length
                                    && charArr[i + 1] == 'M') {
                                arabicValue += 900;
                                i++;
                            } else {
                                arabicValue += 100;
                            }
                        } else if (charArr[i] == 'D') {
                            arabicValue += 500;
                        } else if (charArr[i] == 'M') {
                            arabicValue += 1000;
                        }
                        arabicField.setText(Integer.toString(arabicValue));
                    }
                } else {
                    arabicField.setText(str);
                }
            }
        }

        @Override
        public void keyTyped(KeyEvent event) {
        } // empty method

        @Override
        public void keyPressed(KeyEvent event) {
        } // empty method
    }
}
