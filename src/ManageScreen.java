import javax.swing.*;
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
    private JTextField sibSpPassengerField;
    private JTextField passengerParchField;
    private JTextField minPassengerTicketFareField;
    private JTextField maxPassengerTicketFareField;
    private JTextField passengerTicketField;
    private JTextField passengerCabinField;
    private JComboBox<String> passengerEmbarkedField;
    private JComboBox<String> sexOfPassengerComboBox;
    private JButton sync;

    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        if (file.exists()) {
            List<Passenger> allPassengers = removePotentialDuplicates(readData(file));
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
            sexOfPassengerLabel.setBounds(minPassengerIdLabel.getX() , this.passengerNameField.getY() + Constants.COMBO_BOX_HEIGHT + Constants.MARGIN_FROM_TOP , sexOfPassengerLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(sexOfPassengerLabel);

            this.sexOfPassengerComboBox = new JComboBox<>(Constants.PASSENGER_SEX_OPTIONS);
            this.sexOfPassengerComboBox.setBounds(minPassengerIdLabel.getX() + sexOfPassengerLabel.getWidth() + 1, sexOfPassengerLabel.getY() , Constants.COMBO_BOX_WIDTH , Constants.COMBO_BOX_HEIGHT);
            this.add(sexOfPassengerComboBox);

            JLabel sibSpPassengerLabel = new JLabel("Siblings or Spouses Count: ");
            sibSpPassengerLabel.setBounds(minPassengerIdLabel.getX(), this.sexOfPassengerComboBox.getY() + sexOfPassengerComboBox.getHeight() + Constants.MARGIN_FROM_TOP , sibSpPassengerLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(sibSpPassengerLabel);

            this.sibSpPassengerField = new JTextField();
            this.sibSpPassengerField.setBounds(minPassengerIdLabel.getX() + sibSpPassengerLabel.getWidth() + 1, sibSpPassengerLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(sibSpPassengerField);

            JLabel parchPassengerLabel = new JLabel("Passenger Parch: ");
            parchPassengerLabel.setBounds(minPassengerIdLabel.getX(), sibSpPassengerField.getY() + sibSpPassengerField.getHeight() + Constants.MARGIN_FROM_TOP, parchPassengerLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT ,Constants.LABEL_HEIGHT);
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
            this.maxPassengerTicketFareField.setBounds( maxPassengerTicketFareLabel.getX() + maxPassengerTicketFareLabel.getWidth() + 1, maxPassengerTicketFareLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
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
            this.passengerCabinField.setBounds(passengerCabinLabel.getX() + passengerCabinLabel.getWidth() + 1,passengerCabinLabel.getY(), Constants.TEXT_FIELD_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(passengerCabinField);

            JLabel passengerEmbarkedLabel = new JLabel("Passenger Embarked: ");
            passengerEmbarkedLabel.setBounds(minPassengerIdLabel.getX(), passengerCabinField.getY() + passengerCabinField.getHeight() + Constants.MARGIN_FROM_TOP, passengerEmbarkedLabel.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT, Constants.LABEL_HEIGHT);
            this.add(passengerEmbarkedLabel);

            this.passengerEmbarkedField = new JComboBox<>(Constants.PASSENGER_EMBARKED_OPTIONS);
            this.passengerEmbarkedField.setBounds(minPassengerIdLabel.getX() + passengerEmbarkedLabel.getWidth() + 1, passengerEmbarkedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(passengerEmbarkedField);


            this.sync = new JButton("Synchronize");
            int buttonWidth = sync.getPreferredSize().width + Constants.LABEL_PADDING_RIGHT;
            this.sync.setBounds((this.getWidth()-buttonWidth)/2, passengerEmbarkedField.getY() + passengerEmbarkedField.getHeight() + Constants.MARGIN_FROM_TOP, buttonWidth, Constants.COMBO_BOX_HEIGHT);
            this.add(sync);

            this.sync.addActionListener((e) -> {
                System.out.println(pClassComboBox.getSelectedItem());
                System.out.println(minPassengerIdField.getText());
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
