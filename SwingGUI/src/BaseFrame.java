import java.awt.FlowLayout;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * The BaseFrame class provides the GUI for the base conversion application.
 * It extends JFrame and contains fields for input number, input base, desired base, and a convert button.
 */
public class BaseFrame extends JFrame {
    private final JLabel inputNumberLabel;
    private final JLabel inputBaseLabel;
    private final JLabel desiredBaseLabel;
    private final JTextField inputNumberField;
    private final JTextField inputBaseField;
    private final JTextField desiredBaseField;
    private final JButton convertButton;
    private final JLabel answerLabel;

    /**
     * Constructor that initializes the GUI components for the base conversion application.
     */
    public BaseFrame () {
        super("Testing JLabel");
        setLayout(new FlowLayout());

        inputNumberLabel = new JLabel("Input Number: ");
        inputNumberField = new JTextField(20);

        inputBaseLabel = new JLabel("Input Base: ");
        inputBaseField = new JTextField(20);

        desiredBaseLabel = new JLabel("Desired Base: ");
        desiredBaseField = new JTextField(20);

        convertButton = new JButton("Convert");

        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String inputNum = inputNumberField.getText();
                    int currBase = Integer.parseInt(inputBaseField.getText());
                    int desiredBase = Integer.parseInt(desiredBaseField.getText());

                    Main mainInstance = new Main();
                    String result = mainInstance.convertToBase(inputNum, currBase, desiredBase);

                    answerLabel.setText(result);
                    answerLabel.repaint();

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Please enter valid numbers for bases.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        answerLabel = new JLabel("");

        add(inputNumberLabel);
        inputNumberField.setToolTipText("Enter the number you want to convert here.");
        add(inputNumberField);

        add(inputBaseLabel);
        inputBaseField.setToolTipText("Enter your number's base here.");
        add(inputBaseField);

        add(desiredBaseLabel);
        desiredBaseField.setToolTipText("Enter the base you want to convert to here.");
        add(desiredBaseField);

        add(convertButton);

        add(answerLabel);

    }
}