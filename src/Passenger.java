import java.util.ArrayList;
import java.util.List;

    public class Passenger {
        private int passengerId;
        private boolean survived;
        private int pClass;
        private String name;
        private boolean isMale;
        private Float age;
        private int sibSp;
        private int parch;
        private String ticket;
        private double fare;
        private String cabin;
        private char embarked;

        public static boolean lineValidation (String line) {
            String[] data = line.split(",");
            boolean result = false;
            try {
                if (isNumber(data[Constants.PASSENGER_ID]) &&
                        (data[Constants.SURVIVED].equals("0") || data[Constants.SURVIVED].equals("1")) &&
                        (data[Constants.P_CLASS].equals("1") || data[Constants.P_CLASS].equals("2") || data[Constants.P_CLASS].equals("3")) &&
                        (data[Constants.SEX].equals("female") || data[Constants.SEX].equals("male")) &&
                        (data[Constants.AGE].equals("") || isNumber(data[Constants.AGE])) &&
                        (isNumber(data[Constants.SIB_SP])) &&
                        (isNumber(data[Constants.PARCH])) &&
                        (isNumber(data[Constants.FARE])) ) {

                    if(data.length > Constants.EMBARKED){
                       if(data[Constants.EMBARKED].length() < 2){
                           result = true;
                        }
                    }else{
                        result = true;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println(data[Constants.LAST_NAME] +  " " + data[Constants.FIRST_NAME] + " " + data[Constants.PASSENGER_ID]);
            }
            return result;
        }

        private static boolean isNumber (String number) {
            boolean result = true;
            if (number.contains(".")) {
                number = number.replaceFirst("\\.", "");
            }
            if (number.length() > 0) {
                for (int i = 0; i < number.length(); i++) {
                    char c = number.charAt(i);
                    if (c < '0' || c > '9') {
                        result = false;
                        break;
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
            this.isMale = dataItems[Constants.SEX].equals("male");
            this.age = (dataItems[Constants.AGE].equals(""))?null : Float.parseFloat(dataItems[Constants.AGE]);
            this.sibSp = Integer.parseInt(dataItems[Constants.SIB_SP]);
            this.parch = Integer.parseInt(dataItems[Constants.PARCH]);
            this.ticket = dataItems[Constants.TICKET];
            this.fare = Double.parseDouble(dataItems[Constants.FARE]);
            this.cabin = dataItems[Constants.CABIN];
            if(dataItems.length > 12){
                this.embarked = dataItems[Constants.EMBARKED].charAt(0);
            }else{
                this.embarked = ' ';
            }

        }



        private String getFormattedName(String lastName, String firstName) {
            firstName = firstName.substring(firstName.indexOf('.')+1).trim();
            String fullName = firstName.substring(0,firstName.length()-1) + " " + lastName.substring(1);
            fullName = fullName.replace("\"\"", "\"");
            return fullName;
        }

        @Override
        public String toString() {
            return
                    "\npassengerId = " + passengerId +
                    " name = " + name;
        }

        public int getPassengerId() {
            return passengerId;
        }
    }
