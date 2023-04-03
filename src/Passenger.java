import java.util.ArrayList;
import java.util.List;

    public class Passenger {
        private int passengerId;
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
            if (isNumber(data[Constants.PASSENGER_ID]) &&
            (data[Constants.SURVIVED].equals("0") || data[Constants.SURVIVED].equals("1")) &&
            (data[Constants.P_CLASS].equals("1") || data[Constants.P_CLASS].equals("2") || data[Constants.P_CLASS].equals("3")) &&
            (data[Constants.SEX].equals("female") || data[Constants.SEX].equals("male")) &&
            (data[Constants.AGE].equals("") || isNumber(data[Constants.AGE])) &&
            (isNumber(data[Constants.SIB_SP])) &&
            (isNumber(data[Constants.PARCH])) &&
            (isNumber(data[Constants.FARE])) &&
            (data[Constants.EMBARKED].length()<2)) {
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
            this.passengerId = Integer.parseInt(dataItems[Constants.PASSENGER_ID]);
            this.survived = dataItems[Constants.SURVIVED].equals("1");
            this.pClass = Integer.parseInt(dataItems[Constants.P_CLASS]);
            this.name = this.getFormattedName(dataItems[Constants.LAST_NAME],dataItems[Constants.FIRST_NAME]);

        }



        private String getFormattedName(String lastName, String firstName) {

        return null;
        }

    }
