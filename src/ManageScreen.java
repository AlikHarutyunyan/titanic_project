import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManageScreen extends JPanel {
    private JComboBox<String> survivedComboBox;

    public ManageScreen(int x, int y, int width, int height) {
        File file = new File(Constants.PATH_TO_DATA_FILE); //this is the path to the data file
        if (file.exists()) {
            List<Passenger> allPassengers = readData(file);
            System.out.println(allPassengers);
            this.setLayout(null);
            this.setBounds(x, y + Constants.MARGIN_FROM_TOP, width, height);
            JLabel survivedLabel = new JLabel("Passenger Class: ");
            survivedLabel.setBounds(x + Constants.MARGIN_FROM_LEFT, y, Constants.LABEL_WIDTH, Constants.LABEL_HEIGHT);
            this.add(survivedLabel);
            this.survivedComboBox = new JComboBox<>(Constants.PASSENGER_CLASS_OPTIONS);
            this.survivedComboBox.setBounds(survivedLabel.getX() + survivedLabel.getWidth() + 1, survivedLabel.getY(), Constants.COMBO_BOX_WIDTH, Constants.COMBO_BOX_HEIGHT);
            this.add(this.survivedComboBox);
            this.survivedComboBox.addActionListener((e) -> {
                //do whatever you want on change
            });
        }
    }

    public static List<Passenger> readData (File file) {
        List<Passenger> result = new ArrayList<>();
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

}
