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
         String outPut = this.passengerId + ",";
         outPut += (this.survived)? "1,": "0,";
         outPut += this.pClass + ",";
         outPut += this.name + ",";
         outPut += (this.isMale)? "male,": "female,";
         if (this.age==null) {
             outPut += ",";
         } else {
             outPut += this.age + ",";
         }
         outPut += this.sibSp + "," + this.parch + "," + this.ticket + ",";
         outPut += this.fare + "," + this.cabin + "," + this.embarked;
         return outPut;
     }

     public int getPassengerId() {
            return passengerId;
        }

        public boolean passesFilters(String sibSpPassenger, String parchPassenger, String minPassengerTicketFare, String maxPassengerTicketFare, String pClass, String passengerName, String sexOfPassenger, String passengerCabin, String passengerTicket, String passengerEmbarked) {
            boolean result = false;
            if (this.matchesFilter(sibSpPassenger, this.sibSp + "") &&
            this.matchesFilter(parchPassenger, this.parch + "") &&
            this.matchesFilter(pClass, this.pClass + "") &&
            this.checkName(passengerName) &&
            this.matchesFilter(passengerCabin, this.cabin) &&
            this.matchesFilter(passengerEmbarked, this.embarked + "") &&
            this.matchesFilter(passengerTicket, this.ticket)) {
                if (this.checkPrice(minPassengerTicketFare, maxPassengerTicketFare)) {
                    if (this.checkSex(sexOfPassenger)) {
                        result = true;
                    }
                }
            }
            return result;
        }

        private boolean checkSex (String filter) {
            boolean result = false;
            String mySex;
            if (isMale) {
                mySex = "Male";
            } else {
                mySex = "Female";
            }
            if (filter.equals("") || filter.equals(mySex)) {
                result = true;
            }
            return result;
        }

        private boolean checkName(String name){
            return this.name.toLowerCase().contains(name.toLowerCase());
        }


        private boolean checkPrice (String min, String max) {
            boolean result = false;
            if (min.equals("") && max.equals("")) {
                result = true;
            } else {
                try {
                    if (!min.equals("")) {
                        result = this.fare>Double.parseDouble(min);
                    } else {
                        result = true;
                    }
                    if (result && !max.equals("")) {
                        result = this.fare<Double.parseDouble(max);
                    }
                } catch (Exception e) {
                    System.out.println("something went wrong");
                }
            }
            return result;
        }

        private boolean matchesFilter (String filter, String original) {
            boolean result = false;
            if (filter.equals("") || filter.equals(original)) {
                result = true;
            }
            return result;
        }

     public boolean isSurvived() {
         return survived;
     }

     public int getpClass() {
         return pClass;
     }

     public boolean isMale() {
         return isMale;
     }

     public boolean ageInRange(float min, float max) {
            boolean result = false;
         if (this.age != null) {
            result = this.age >= min && this.age < max;
         }
         return result;
     }

     public boolean checkFamilyMember(){
            return (this.sibSp + this.parch) > 0;
     }

     public boolean fareInRange(float min, float max){
            return this.fare >= min && this.fare < max;
     }

     public boolean matchesEmbarked(char embarked){
            return this.embarked == embarked;
     }



     public boolean compareByLetters(Passenger other){
            return this.name.compareTo(other.name) > 0;
     }
 }
