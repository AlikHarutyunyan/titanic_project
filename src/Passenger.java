import java.util.ArrayList;
import java.util.List;

    public class Passenger {
        private int passengerLd;
        private boolean survived;
        private int pClass;
        private String name;
        private boolean isMale;
        private float age;
        private int sibSp;
        private int parch;
        private String ticket;
        private double fare;
        private String cabin;
        private char embarked;

        public static boolean lineValidation (String line) {
            String[] data = line.split(",");
            boolean result = false;
            if (isNumber(data[0]) &&
            (data[1].equals("0") || data[1].equals("1")) &&
            (data[2].equals("1") || data[2].equals("2") || data[2].equals("3")) &&
            (data[5].equals("female") || data[5].equals("male")) &&
            (data[6].equals("") || isNumber(data[6])) &&
            (isNumber(data[7])) &&
            (isNumber(data[8])) &&
            (isNumber(data[10])) &&
            (data[12].length()<2)) {
                result = true;
            }
            return result;
        }

        private static boolean isNumber (String number) {
            boolean result = true;
            if (number.contains(".")) {
                number=number.replaceFirst(".", "");
            }
            if (number.length()>0) {
                for (int i = 0; i < number.length(); i++) {
                    for (int j = 0; j <= 9; j++) {
                        if (number.charAt(i) - '0' != j && j == 9) {
                            result = false;
                            break;
                        }
                    }
                }
            } else {
                result = false;
            }
            return result;
        }


        public Passenger (String line) {
            String[] dataItems = line.split(",");

        }



        //private String getFormattedName(String firstPart, String secondPart) {


        //}

    }
