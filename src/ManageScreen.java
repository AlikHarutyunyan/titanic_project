import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ManageScreen extends JPanel {
    private JComboBox<String> pClassComboBox;
    private JTextField minPassengerIdField;
    private JTextField maxPassengerIdField;
    private JTextField passengerNameField;

    private JComboBox<String> sexOfPassengerComboBox;

    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        if (file.exists()) {
            List<Passenger> allPassengers = removePotentialDuplicates(readData(file));
            System.out.println(allPassengers);
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);

            JLabel pClassLabel = new JLabel("Passenger Class: ");
            pClassLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, pClassLabel.getPreferredSize().width, Constants.LABEL_HEIGHT);
            this.add(pClassLabel);

            this.pClassComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
            this.pClassComboBox.setBounds(pClassLabel.getX() + pClassLabel.getWidth() + 1, pClassLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.pClassComboBox);

            JLabel minPassengerIdLabel = new JLabel("Minimum Passenger ID: ");
            minPassengerIdLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, this.pClassComboBox.getY() + Constants.MARGIN_FROM_TOP + this.pClassComboBox.getHeight(),
                    minPassengerIdLabel.getPreferredSize().width, Constants.LABEL_HEIGHT);
            this.add(minPassengerIdLabel);

            this.minPassengerIdField = new JTextField();
            this.minPassengerIdField.setBounds(minPassengerIdLabel.getX() + minPassengerIdLabel.getWidth() + 1, minPassengerIdLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(minPassengerIdField);

            JLabel maxPassengerIdLabel = new JLabel("Maximum Passenger ID: ");
            maxPassengerIdLabel.setBounds(this.minPassengerIdField.getX() + this.minPassengerIdField.getWidth() + Constants.MARGIN_FROM_LEFT, minPassengerIdLabel.getY(), maxPassengerIdLabel.getPreferredSize().width, Constants.LABEL_HEIGHT);
            this.add(maxPassengerIdLabel);

            this.maxPassengerIdField = new JTextField();
            this.maxPassengerIdField.setBounds(maxPassengerIdLabel.getX() + maxPassengerIdLabel.getWidth() + 1, maxPassengerIdLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.maxPassengerIdField);

            JLabel passengerNameLabel = new JLabel("Passenger Name: ");
            passengerNameLabel.setBounds(minPassengerIdLabel.getX(), this.minPassengerIdField.getY() + this.minPassengerIdField.getHeight() + Constants.MARGIN_FROM_TOP, passengerNameLabel.getPreferredSize().width, Constants.LABEL_HEIGHT);
            this.add(passengerNameLabel);

            this.passengerNameField = new JTextField();
            this.passengerNameField.setBounds(minPassengerIdLabel.getX() + passengerNameLabel.getWidth() + 1, passengerNameLabel.getY(), Constants.PASSENGER_NAME_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.passengerNameField);

            JLabel sexOfPassengerLabel = new JLabel("Sex of Passenger: ");
            sexOfPassengerLabel.setBounds(minPassengerIdLabel.getX() , this.passengerNameField.getY() + Constants.COMBO_BOX_HEIGHT + Constants.MARGIN_FROM_TOP , sexOfPassengerLabel.getPreferredSize().width, Constants.LABEL_HEIGHT);
            this.add(sexOfPassengerLabel);

            this.sexOfPassengerComboBox = new JComboBox<>(Constants.PASSENGER_SEX_OPTIONS);
            this.sexOfPassengerComboBox.setBounds(minPassengerIdLabel.getX() + sexOfPassengerLabel.getWidth() + 1, sexOfPassengerLabel.getY() , Constants.COMBO_BOX_WIDTH , Constants.COMBO_BOX_HEIGHT);
            this.add(sexOfPassengerComboBox);

            JLabel sibSpPassengerLabel = new JLabel("Siblings or Spouses Count: ");
            sibSpPassengerLabel.setBounds(minPassengerIdLabel.getX(), this.sexOfPassengerComboBox.getY() + sexOfPassengerComboBox.getHeight() + Constants.MARGIN_FROM_TOP , Constants.SIB_SP_PASSENGER_LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(sibSpPassengerLabel);


            this.pClassComboBox.addActionListener((e) -> {
                //do whatever you want on change
            });
        }
    }

    public static ArrayList<Passenger> readData (File file) {
        ArrayList<Passenger> result = new ArrayList<>();
        try {
            FileReader filereader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(filereader);
            String line = null;
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
