import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ManageScreen extends JPanel {
    private JComboBox<String> pClassComboBox;
    private JTextField minPassengerIdField;
    private JTextField maxPassengerIdField;
    private JTextField passengerNameField;
    private JTextField sibSpPassengerField;
    private JTextField passengerParchField;
    private JTextField minPassengerTicketFareField;
    private JTextField maxPassengerTicketFareField;
    private JTextField passengerTicketField;
    private JTextField passengerCabinField;
    private JComboBox<String> passengerEmbarkedComboBox;
    private JComboBox<String> sexOfPassengerComboBox;
    private JButton sync;

    private JButton statistics;
    private ArrayList<Passenger> allPassengers;

    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        if (file.exists()) {
            this.allPassengers = removePotentialDuplicates(readData(file));
            System.out.println(allPassengers);
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);

            JLabel pClassLabel = new JLabel("Passenger Class: ");
            pClassLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, pClassLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(pClassLabel);

            this.pClassComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
            this.pClassComboBox.setBounds(pClassLabel.getX() + pClassLabel.getWidth() + 1, pClassLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.pClassComboBox);

            JLabel minPassengerIdLabel = new JLabel("Minimum Passenger ID: ");
            minPassengerIdLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, this.pClassComboBox.getY() + Constants.MARGIN_FROM_TOP + this.pClassComboBox.getHeight(),
                    minPassengerIdLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(minPassengerIdLabel);

            this.minPassengerIdField = new JTextField();
            this.minPassengerIdField.setBounds(minPassengerIdLabel.getX() + minPassengerIdLabel.getWidth() + 1, minPassengerIdLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(minPassengerIdField);

            JLabel maxPassengerIdLabel = new JLabel("Maximum Passenger ID: ");
            maxPassengerIdLabel.setBounds(this.minPassengerIdField.getX() + this.minPassengerIdField.getWidth() + Constants.MARGIN_FROM_LEFT, minPassengerIdLabel.getY(), maxPassengerIdLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(maxPassengerIdLabel);

            this.maxPassengerIdField = new JTextField();
            this.maxPassengerIdField.setBounds(maxPassengerIdLabel.getX() + maxPassengerIdLabel.getWidth() + 1, maxPassengerIdLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.maxPassengerIdField);

            JLabel passengerNameLabel = new JLabel("Passenger Name: ");
            passengerNameLabel.setBounds(minPassengerIdLabel.getX(), this.minPassengerIdField.getY() + this.minPassengerIdField.getHeight() + Constants.MARGIN_FROM_TOP, passengerNameLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(passengerNameLabel);

            this.passengerNameField = new JTextField();
            this.passengerNameField.setBounds(minPassengerIdLabel.getX() + passengerNameLabel.getWidth() + 1, passengerNameLabel.getY(), Constants.PASSENGER_NAME_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.passengerNameField);

            JLabel sexOfPassengerLabel = new JLabel("Sex of Passenger: ");
            sexOfPassengerLabel.setBounds(minPassengerIdLabel.getX(), this.passengerNameField.getY() + Constants.COMBO_BOX_HEIGHT + Constants.MARGIN_FROM_TOP, sexOfPassengerLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(sexOfPassengerLabel);

            this.sexOfPassengerComboBox = new JComboBox<>(Constants.PASSENGER_SEX_OPTIONS);
            this.sexOfPassengerComboBox.setBounds(minPassengerIdLabel.getX() + sexOfPassengerLabel.getWidth() + 1, sexOfPassengerLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(sexOfPassengerComboBox);

            JLabel sibSpPassengerLabel = new JLabel("Siblings or Spouses Count: ");
            sibSpPassengerLabel.setBounds(minPassengerIdLabel.getX(), this.sexOfPassengerComboBox.getY() + sexOfPassengerComboBox.getHeight() + Constants.MARGIN_FROM_TOP, sibSpPassengerLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(sibSpPassengerLabel);

            this.sibSpPassengerField = new JTextField();
            this.sibSpPassengerField.setBounds(minPassengerIdLabel.getX() + sibSpPassengerLabel.getWidth() + 1, sibSpPassengerLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(sibSpPassengerField);

            JLabel parchPassengerLabel = new JLabel("Passenger Parch: ");
            parchPassengerLabel.setBounds(minPassengerIdLabel.getX(), sibSpPassengerField.getY() + sibSpPassengerField.getHeight() + Constants.MARGIN_FROM_TOP, parchPassengerLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(parchPassengerLabel);

            this.passengerParchField = new JTextField();
            this.passengerParchField.setBounds(minPassengerIdLabel.getX() + parchPassengerLabel.getWidth() + 1, parchPassengerLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(passengerParchField);

            JLabel minPassengerTicketFareLabel = new JLabel("Minimum Ticket Cost: ");
            minPassengerTicketFareLabel.setBounds(minPassengerIdLabel.getX(), passengerParchField.getY() + passengerParchField.getHeight() + Constants.MARGIN_FROM_TOP, minPassengerTicketFareLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(minPassengerTicketFareLabel);

            this.minPassengerTicketFareField = new JTextField();
            this.minPassengerTicketFareField.setBounds(minPassengerIdLabel.getX() + minPassengerTicketFareLabel.getWidth() + 1, minPassengerTicketFareLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(minPassengerTicketFareField);

            JLabel maxPassengerTicketFareLabel = new JLabel("Maximum Ticket Cost: ");
            maxPassengerTicketFareLabel.setBounds(minPassengerTicketFareField.getX() + minPassengerTicketFareField.getWidth() + Constants.MARGIN_FROM_LEFT, minPassengerTicketFareField.getY(), maxPassengerTicketFareLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(maxPassengerTicketFareLabel);

            this.maxPassengerTicketFareField = new JTextField();
            this.maxPassengerTicketFareField.setBounds(maxPassengerTicketFareLabel.getX() + maxPassengerTicketFareLabel.getWidth() + 1, maxPassengerTicketFareLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(maxPassengerTicketFareField);

            JLabel passengerTicketLabel = new JLabel("Passenger Ticket Number: ");
            passengerTicketLabel.setBounds(minPassengerIdLabel.getX(), maxPassengerTicketFareField.getY() + maxPassengerTicketFareField.getHeight() + Constants.MARGIN_FROM_TOP, passengerTicketLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(passengerTicketLabel);

            this.passengerTicketField = new JTextField();
            this.passengerTicketField.setBounds(passengerTicketLabel.getX() + passengerTicketLabel.getWidth() + 1, passengerTicketLabel.getY(), Constants.PASSENGER_TICKET_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(passengerTicketField);

            JLabel passengerCabinLabel = new JLabel("Passenger cabin number: ");
            passengerCabinLabel.setBounds(minPassengerIdLabel.getX(), passengerTicketField.getY() + passengerTicketField.getHeight() + Constants.MARGIN_FROM_TOP, passengerCabinLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(passengerCabinLabel);

            this.passengerCabinField = new JTextField();
            this.passengerCabinField.setBounds(passengerCabinLabel.getX() + passengerCabinLabel.getWidth() + 1, passengerCabinLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(passengerCabinField);

            JLabel passengerEmbarkedLabel = new JLabel("Passenger Embarked: ");
            passengerEmbarkedLabel.setBounds(minPassengerIdLabel.getX(), passengerCabinField.getY() + passengerCabinField.getHeight() + Constants.MARGIN_FROM_TOP, passengerEmbarkedLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(passengerEmbarkedLabel);

            this.passengerEmbarkedComboBox = new JComboBox<>(Constants.PASSENGER_EMBARKED_OPTIONS);
            this.passengerEmbarkedComboBox.setBounds(minPassengerIdLabel.getX() + passengerEmbarkedLabel.getWidth() + 1, passengerEmbarkedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(passengerEmbarkedComboBox);


            this.sync = new JButton("Synchronize");
            int buttonWidth = sync.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT;
            this.sync.setBounds((this.getWidth() - buttonWidth) / 2, passengerEmbarkedComboBox.getY() + passengerEmbarkedComboBox.getHeight() + Constants.MARGIN_FROM_TOP, buttonWidth, Constants.COMBO_BOX_HEIGHT);
            this.add(sync);

            JLabel searchResultLabel = new JLabel("Total passengers: XXX (XXX survived, XXX did not)");
            int searchResultLabelWidth = searchResultLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT;
            searchResultLabel.setBounds((this.getWidth() - searchResultLabelWidth) / 2, sync.getY() + sync.getHeight(), searchResultLabelWidth, Constants.LABEL_HEIGHT);
            searchResultLabel.setVisible(false);
            this.add(searchResultLabel);

            this.sync.addActionListener((e) -> {
                String minPassengerId = this.minPassengerIdField.getText().trim();
                String maxPassengerId = this.maxPassengerIdField.getText().trim();
                String pClass = (String) this.pClassComboBox.getSelectedItem();
                String passengerName = this.passengerNameField.getText().trim();
                String sexOfPassenger = (String) this.sexOfPassengerComboBox.getSelectedItem();
                String sibSpPassenger = this.sibSpPassengerField.getText().trim();
                String parchPassenger = this.passengerParchField.getText().trim();
                String passengerTicket = this.passengerTicketField.getText().trim();
                String minPassengerTicketFare = this.minPassengerTicketFareField.getText().trim();
                String maxPassengerTicketFare = this.maxPassengerTicketFareField.getText().trim();
                String passengerCabin = this.passengerCabinField.getText().trim();
                String passengerEmbarked = (String) this.passengerEmbarkedComboBox.getSelectedItem();


                if (dataValidation(minPassengerId, maxPassengerId, sibSpPassenger, parchPassenger, minPassengerTicketFare, maxPassengerTicketFare)) {
                    int min = 0;
                    int max = this.allPassengers.size();
                    if (!minPassengerId.equals("") && Integer.parseInt(minPassengerId) > 0) {
                        min = Integer.parseInt(minPassengerId) - 1;
                    }
                    if (!maxPassengerId.equals("") && Integer.parseInt(maxPassengerId) < this.allPassengers.size()) {
                        max = Integer.parseInt(maxPassengerId);
                    }
                    ArrayList<Passenger> searchResult = this.performSearch(this.allPassengers.subList(min, max), sibSpPassenger, parchPassenger,
                            minPassengerTicketFare, maxPassengerTicketFare, pClass, passengerName, sexOfPassenger, passengerTicket, passengerCabin, passengerEmbarked);
                    System.out.println(searchResult);
                    int countSurvived = this.countSurvived(searchResult);
                    searchResultLabel.setText("Total passengers: " + searchResult.size() + " (" + countSurvived + " survived, " + (searchResult.size() - countSurvived) + " did not)");
                    searchResultLabel.setVisible(true);
                    this.createFile(searchResult);

                } else {
                    System.out.println("You Put Incorrect Values");
                }

            });

            this.statistics = new JButton("Create statistics file");
            int statisticsButtonWidth = statistics.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT;
            this.statistics.setBounds((this.getWidth() - statisticsButtonWidth) / 2, searchResultLabel.getY() + searchResultLabel.getHeight() + Constants.MARGIN_FROM_TOP, statisticsButtonWidth, Constants.COMBO_BOX_HEIGHT);
            this.add(statistics);

            this.statistics.addActionListener((e) -> {
                this.statistics.setEnabled(false);
                double[] survivedPercentageByClass = getSurvivedPercentageByClass(allPassengers);
                double[] survivedPercentageBySex = getSurvivedPercentageBySex(allPassengers);
                double[] survivedPercentageByAge = getSurvivedPercentageByAge(allPassengers);
                double[] survivedPercentageByFamilyMembers = getSurvivedPercentageByFamilyMembers(allPassengers);
                double[] survivedPercentageByFair = getSurvivedPercentageByFare(allPassengers);
                double[] survivedPercentageByEmbarked = getSurvivedPercentageEmbarked(allPassengers);
            });
        }
    }

    private double[] getSurvivedPercentageByClass(ArrayList<Passenger> allPassengers) {
        int[] allPassengersByClass = new int[Constants.PCLASS_THREE];
        int[] survivedPassengersByClass = new int[Constants.PCLASS_THREE];
        double[] result = new double[Constants.PCLASS_THREE];

        for (Passenger passenger : allPassengers) {
            if (passenger.getpClass() == Constants.PCLASS_ONE) {
                allPassengersByClass[Constants.PCLASS_ONE_INDEX]++;
                if (passenger.isSurvived()) {
                    survivedPassengersByClass[Constants.PCLASS_ONE_INDEX]++;
                }
            } else if (passenger.getpClass() == Constants.PCLASS_TWO) {
                allPassengersByClass[Constants.PCLASS_TWO_INDEX]++;
                if (passenger.isSurvived()) {
                    survivedPassengersByClass[Constants.PCLASS_TWO_INDEX]++;
                }
            } else if (passenger.getpClass() == Constants.PCLASS_THREE) {
                allPassengersByClass[Constants.PCLASS_THREE_INDEX]++;
                if (passenger.isSurvived()) {
                    survivedPassengersByClass[Constants.PCLASS_THREE_INDEX]++;
                }
            }
        }

        result[Constants.PCLASS_ONE_INDEX] = ((double) survivedPassengersByClass[Constants.PCLASS_ONE_INDEX] * Constants.MAXIMUM_PERCENTAGE) / (double) allPassengersByClass[Constants.PCLASS_ONE_INDEX];
        result[Constants.PCLASS_TWO_INDEX] = ((double) survivedPassengersByClass[Constants.PCLASS_TWO_INDEX] * Constants.MAXIMUM_PERCENTAGE / (double) allPassengersByClass[Constants.PCLASS_TWO_INDEX]);
        result[Constants.PCLASS_THREE_INDEX] = ((double) survivedPassengersByClass[Constants.PCLASS_THREE_INDEX] * Constants.MAXIMUM_PERCENTAGE / (double) allPassengersByClass[Constants.PCLASS_THREE_INDEX]);
        return result;
    }

    private double[] getSurvivedPercentageBySex(ArrayList<Passenger> allPassengers) {
        int[] allPassengersBySex = new int[Constants.TOTAL_SEXES];
        int[] survivedPassengersBySex = new int[Constants.TOTAL_SEXES];
        double[] result = new double[Constants.TOTAL_SEXES];

        for (Passenger passenger : allPassengers) {
            if (!passenger.isMale()) {
                allPassengersBySex[Constants.FEMALE_INDEX]++;
                if (passenger.isSurvived()) {
                    survivedPassengersBySex[Constants.FEMALE_INDEX]++;
                }
            } else {
                allPassengersBySex[Constants.MALE_INDEX]++;
                if (passenger.isSurvived()) {
                    survivedPassengersBySex[Constants.MALE_INDEX]++;
                }
            }
        }

        result[Constants.FEMALE_INDEX] = ((double) survivedPassengersBySex[Constants.FEMALE_INDEX] * Constants.MAXIMUM_PERCENTAGE) / (double) allPassengersBySex[Constants.FEMALE_INDEX];
        result[Constants.MALE_INDEX] = ((double) survivedPassengersBySex[Constants.MALE_INDEX] * Constants.MAXIMUM_PERCENTAGE) / (double) allPassengersBySex[Constants.MALE_INDEX];
        return result;
    }

    private double[] getSurvivedPercentageByAge(ArrayList<Passenger> allPassengers) {
        int[] allPassengersByAge = new int[Constants.TOTAL_RANGES];
        int[] survivedPassengersByAge = new int[Constants.TOTAL_RANGES];
        double[] result = new double[Constants.TOTAL_RANGES];
        for (Passenger passenger : allPassengers) {
            boolean b = this.checkAgeInRange(Constants.FIRST_RANGE_MIN, Constants.SECOND_RANGE_MIN, Constants.FIRST_RANGE_INDEX, passenger, allPassengersByAge, survivedPassengersByAge) ||
                    this.checkAgeInRange(Constants.SECOND_RANGE_MIN, Constants.THIRD_RANGE_MIN, Constants.SECOND_RANGE_INDEX, passenger, allPassengersByAge, survivedPassengersByAge) ||
                    this.checkAgeInRange(Constants.THIRD_RANGE_MIN, Constants.FOURTH_RANGE_MIN, Constants.THIRD_RANGE_INDEX, passenger, allPassengersByAge, survivedPassengersByAge) ||
                    this.checkAgeInRange(Constants.FOURTH_RANGE_MIN, Constants.FIFTH_RANGE_MIN, Constants.FOURTH_RANGE_INDEX, passenger, allPassengersByAge, survivedPassengersByAge) ||
                    this.checkAgeInRange(Constants.FIFTH_RANGE_MIN, Constants.LAST_RANGE_MIN, Constants.FIFTH_RANGE_INDEX, passenger, allPassengersByAge, survivedPassengersByAge) ||
                    this.checkAgeInRange(Constants.LAST_RANGE_MIN, Integer.MAX_VALUE, Constants.LAST_RANGE_INDEX, passenger, allPassengersByAge, survivedPassengersByAge);
        }
        for (int i = 0; i < Constants.TOTAL_RANGES; i++) {
            result[i] = ((double)survivedPassengersByAge[i]* Constants.MAXIMUM_PERCENTAGE)/(double)allPassengersByAge[i];
        }
        return result;
    }

        private double[] getSurvivedPercentageByFamilyMembers(ArrayList<Passenger> allPassengers){
            int[] allPassengersByFamilyMembers = new int[Constants.TOTAL_FAMILY_MEMBERS_OPTIONS];
            int[] survivedPassengersByFamilyMembers = new int[Constants.TOTAL_FAMILY_MEMBERS_OPTIONS];
            double[] result = new double[Constants.TOTAL_FAMILY_MEMBERS_OPTIONS];
            for (Passenger passenger : allPassengers) {
                if (passenger.checkFamilyMember()){
                    allPassengersByFamilyMembers[Constants.MORE_THAN_ZERO_FAMILY_MEMBERS_INDEX]++;
                    if (passenger.isSurvived()){
                        survivedPassengersByFamilyMembers[Constants.MORE_THAN_ZERO_FAMILY_MEMBERS_INDEX]++;
                    }
                }else{
                    allPassengersByFamilyMembers[Constants.ZERO_FAMILY_MEMBERS_INDEX]++;
                    if (passenger.isSurvived()){
                        survivedPassengersByFamilyMembers[Constants.ZERO_FAMILY_MEMBERS_INDEX]++;
                    }
                }
            }
            result[Constants.MORE_THAN_ZERO_FAMILY_MEMBERS_INDEX] = (survivedPassengersByFamilyMembers[Constants.MORE_THAN_ZERO_FAMILY_MEMBERS_INDEX]* Constants.MAXIMUM_PERCENTAGE)/allPassengersByFamilyMembers[Constants.MORE_THAN_ZERO_FAMILY_MEMBERS_INDEX];
            result[Constants.ZERO_FAMILY_MEMBERS_INDEX] = (survivedPassengersByFamilyMembers[Constants.ZERO_FAMILY_MEMBERS_INDEX]* Constants.MAXIMUM_PERCENTAGE)/allPassengersByFamilyMembers[Constants.ZERO_FAMILY_MEMBERS_INDEX];
            return result;
        }

        private double[] getSurvivedPercentageByFare(ArrayList<Passenger> allPassengers){
            int[] allPassengersByFare = new int[Constants.TOTAL_FARE_RANGES];
            int[] survivedPassengersByFare = new int[Constants.TOTAL_FARE_RANGES];
            double[] result = new double[Constants.TOTAL_FARE_RANGES];
            for (Passenger passenger : allPassengers) {
                boolean b = checkFareInRange(Constants.FIRST_FARE_RANGE_MIN, Constants.SECOND_FARE_RANGE_MIN,Constants.FIRST_FARE_RANGE_INDEX,passenger, allPassengersByFare, survivedPassengersByFare) ||
                        checkFareInRange(Constants.SECOND_FARE_RANGE_MIN,Constants.THIRD_FARE_RANGE_MIN,Constants.SECOND_FARE_RANGE_INDEX,passenger, allPassengersByFare, survivedPassengersByFare) ||
                        checkFareInRange(Constants.THIRD_FARE_RANGE_MIN, Integer.MAX_VALUE,Constants.THIRD_FARE_RANGE_INDEX, passenger, allPassengersByFare, survivedPassengersByFare);
            }
            for (int i = 0; i < Constants.TOTAL_FARE_RANGES; i++) {
                result[i] = ((double)survivedPassengersByFare[i]*Constants.MAXIMUM_PERCENTAGE)/(double)allPassengersByFare[i];
            }
            return result;
        }

        private double[] getSurvivedPercentageEmbarked(ArrayList<Passenger> allPassengers){
            int[] allPassengersByEmbarked = new int[Constants.TOTAL_EMBARKED_OPTIONS];
            int[] survivedPassengersByEmbarked = new int[Constants.TOTAL_EMBARKED_OPTIONS];
            double[] result = new double[Constants.TOTAL_EMBARKED_OPTIONS];
            for (Passenger passenger : allPassengers) {
               boolean b = checkEmbarked(Constants.C_EMBARKED,Constants.C_EMBARKED_INDEX,passenger, allPassengersByEmbarked, survivedPassengersByEmbarked) ||
                       checkEmbarked(Constants.Q_EMBARKED, Constants.Q_EMBARKED_INDEX, passenger, allPassengersByEmbarked, survivedPassengersByEmbarked) ||
                       checkEmbarked(Constants.S_EMBARKED, Constants.S_EMBARKED_INDEX, passenger, allPassengersByEmbarked, survivedPassengersByEmbarked);
                }
            for (int i = 0; i < Constants.TOTAL_EMBARKED_OPTIONS; i++) {
                result[i] = ((double)survivedPassengersByEmbarked[i]*Constants.MAXIMUM_PERCENTAGE)/(double)allPassengersByEmbarked[i];
            }
            return result;
    }


        private boolean checkEmbarked(char embarked, int index, Passenger passenger, int[] allPassengersByEmbarked, int[] survivedPassengersByEmbarked) {
        boolean result = false;
        if (passenger.matchesEmbarked(embarked)){
            allPassengersByEmbarked[index]++;
            if (passenger.isSurvived()){
                survivedPassengersByEmbarked[index]++;
            }
            result = true;
        }
        return result;
        }


        private boolean checkFareInRange(int min, int max, int index, Passenger passenger, int[] allPassengersByFare, int[] survivedPassengersByFare){
            boolean result = false;
            if (passenger.fareInRange(min, max)){
                allPassengersByFare[index]++;
                if (passenger.isSurvived()){
                    survivedPassengersByFare[index]++;
                }
                result = true;
            }
            return result;
        }

    private boolean checkAgeInRange(int min, int max, int index, Passenger passenger, int[] allPassengersByAge, int[] survivedPassengersByAge){
        boolean result = false;
        if (passenger.ageInRange(min, max)){
            allPassengersByAge[index]++;
            if (passenger.isSurvived()){
                survivedPassengersByAge[index]++;
            }
            result = true;
        }
        return result;
    }


    private void createFile (ArrayList<Passenger> searchList) {
        //TODO SORT THE ARRAY LIST BY NAME IF NECESSARY (CHECK LATER)
            File file = new File(Constants.CURRENT_NUMBER_FILE);
            boolean success = false;
            if (!file.exists()) {
                try {
                    success = file.createNewFile() && writeToFile(file, "1");
                } catch (Exception g) {
                    System.out.println("Error creating the file: " + g.getMessage());
                }
            }

            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(Constants.CURRENT_NUMBER_FILE));
                String name = bufferedReader.readLine();
                File newFile = new File(Constants.DATA_PATH + name + ".csv");
                newFile.createNewFile();
                writeToFile(newFile,this.createCsvSheet(searchList));
                writeToFile(file, Integer.parseInt(name)+1 + "");

            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    private boolean writeToFile (File file, String text) {
        boolean result = false;
        try {
            if (file != null && file.exists()) {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(text);
                fileWriter.close();
                result = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String createCsvSheet (ArrayList<Passenger> passengers) {
        String outPut = "PassengerId,Survived,Pclass,Name,Sex,Age,SibSp,Parch,Ticket,Fare,Cabin,Embarked";
        for (Passenger passenger: passengers) {
            outPut += "\n" + passenger.toString();
        }
        return outPut;
    }

    private int countSurvived(ArrayList<Passenger> searchResult) {
        int result=0;
        for (Passenger passenger: searchResult) {
            if(passenger.isSurvived()) {
                result++;
            }
        }
        return result;
    }


    private ArrayList<Passenger> performSearch(List<Passenger> subList, String sibSpPassenger, String parchPassenger, String minPassengerTicketFare, String maxPassengerTicketFare, String pClass, String passengerName, String sexOfPassenger, String passengerTicket, String passengerCabin, String passengerEmbarked) {
        ArrayList<Passenger> result = new ArrayList<>();
        if (pClass != null && pClass.equals("All")) {
            pClass = "";
        }
        if (sexOfPassenger != null && sexOfPassenger.equals("All")) {
            sexOfPassenger = "";
        }
        if (passengerEmbarked != null && passengerEmbarked.equals("All")) {
            passengerEmbarked = "";
        }
        for (Passenger passenger : subList) {
            if (passenger.passesFilters(sibSpPassenger, parchPassenger, minPassengerTicketFare, maxPassengerTicketFare, pClass, passengerName, sexOfPassenger, passengerCabin, passengerTicket, passengerEmbarked)) {
                result.add(passenger);
            }
        }
        return result;
    }



    private boolean dataValidation(String minPassengerId, String maxPassengerId, String sibSpPassenger, String parchPassenger, String minPassengerTicketFare, String maxPassengerTicketFare) {
        boolean result = false;
        if (isIntegerOrEmpty(sibSpPassenger) && (isIntegerOrEmpty(parchPassenger))) {
            if (isIntegerOrEmpty(minPassengerId) && isIntegerOrEmpty(maxPassengerId)) {

                if (isDoubleOrEmpty(minPassengerTicketFare) && isDoubleOrEmpty(maxPassengerTicketFare)) {
                    if (!minPassengerTicketFare.equals("") && !maxPassengerTicketFare.equals("")){
                        result = minMaxValidation(minPassengerTicketFare, maxPassengerTicketFare);
                    } else{
                        result = true;
                    }
                    if(result){
                        if (!minPassengerId.equals("") && Integer.parseInt(minPassengerId) < this.allPassengers.size()){
                            if (!maxPassengerId.equals("")){
                                result = minMaxValidation(minPassengerId, maxPassengerId);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private boolean minMaxValidation (String min, String max){
        boolean result = false;
        try{
            result = Double.parseDouble(max) > Double.parseDouble(min);
        }
        catch(Exception e){
            System.out.println("Something Went Wrong");
        }
        return result;
    }



    private boolean isIntegerOrEmpty(String num){
        boolean result = true;
            if (!num.equals("")){
                try{
                    int number = Integer.parseInt(num);
                    result = number>=0;
                }
                catch(Exception e){
                    result = false;
                }
            }
            return result;
    }


    private boolean isDoubleOrEmpty(String num){
        boolean result = true;
        if (!num.equals("")){
            try{
                double number = Double.parseDouble(num);
                result = number>=0;
            }
            catch(Exception e){
                result = false;
            }
        }
        return result;
    }



    public ArrayList<Passenger> readData (File file) {
        ArrayList<Passenger> result = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(filereader);
            String line;
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                Passenger newPassenger = createPassenger(line);
                if (newPassenger != null) {
                    result.add(newPassenger);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static Passenger createPassenger (String line) {
        Passenger passenger = null;
        if (Passenger.lineValidation(line)) {
            passenger = new Passenger(line);
        }
        return passenger;
    }

    private ArrayList<Passenger> removePotentialDuplicates (ArrayList<Passenger> passengers) {
        ArrayList<Passenger> result = new ArrayList<>();
        HashSet<Integer> ids = new HashSet<>();
        for (Passenger passenger : passengers) {
            if (ids.add(passenger.getPassengerId())) {
                result.add(passenger);
            }
        }
        return result;
    }



}
