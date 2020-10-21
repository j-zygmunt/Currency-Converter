import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConverterView extends JFrame implements ViewInterface{
    private JTextField amountTextField;
    private JLabel rateValueLabel;
    private JComboBox<String> fromComboBox, toComboBox;
    private JButton convertButton;
    private JTextArea afterConversionTextArea;

    public ConverterView() {
        JPanel mainPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 300);
        this.setResizable(false);
        this.setBackground(Color.darkGray);

        mainPanel.setLayout(null);
        mainPanel.setBackground(Color.darkGray);

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setForeground(Color.white);
        fromLabel.setBounds(50, 25, 80, 25);
        mainPanel.add(fromLabel);

        fromComboBox = new JComboBox<String>();
        fromComboBox.setOpaque(true);
        fromComboBox.setBorder(BorderFactory.createEmptyBorder());
        fromComboBox.setBounds(40, 50, 165, 25);
        fromComboBox.setForeground(Color.black);
        fromComboBox.setBackground(Color.gray);
        mainPanel.add(fromComboBox);

        JLabel toLabel = new JLabel("To:");
        toLabel.setForeground(Color.white);
        toLabel.setBounds(50, 155, 80, 25);
        mainPanel.add(toLabel);

        toComboBox = new JComboBox<String>();
        toComboBox.setOpaque(true);
        toComboBox.setBorder(BorderFactory.createEmptyBorder());
        toComboBox.setBounds(40, 180, 165, 25);
        toComboBox.setForeground(Color.black);
        toComboBox.setBackground(Color.gray);
        mainPanel.add(toComboBox);

        JLabel rateLabel = new JLabel("Exchange Rate:");
        rateLabel.setForeground(Color.white);
        rateLabel.setBounds(50, 110, 165, 25);
        mainPanel.add(rateLabel);

        rateValueLabel = new JLabel();
        rateValueLabel.setForeground(Color.white);
        rateValueLabel.setBounds(145, 110, 80, 25);
        mainPanel.add(rateValueLabel);

        amountTextField = new JTextField();
        amountTextField.setBackground(Color.gray);
        amountTextField.setBorder(BorderFactory.createEmptyBorder());
        amountTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                Runnable format = new Runnable() {
                    @Override
                    public void run() {
                        String text = amountTextField.getText();
                        if (!text.matches("\\d*(\\.\\d{0,2})?") || text.length() > 20) {
                            amountTextField.setText(text.substring(0, text.length() - 1));
                        }
                    }
                };
                SwingUtilities.invokeLater(format);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });

        amountTextField.setForeground(Color.black);
        amountTextField.setFont(amountTextField.getFont().deriveFont(Font.BOLD));
        amountTextField.setBounds(280, 50, 165, 25);
        mainPanel.add(amountTextField);

        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setForeground(Color.white);
        amountLabel.setBounds(290, 25, 80, 25);
        mainPanel.add(amountLabel);

        afterConversionTextArea = new JTextArea();
        afterConversionTextArea.setBackground(Color.gray);
        afterConversionTextArea.setLineWrap(true);
        afterConversionTextArea.setBorder(BorderFactory.createEmptyBorder());
        afterConversionTextArea.setForeground(Color.black);
        afterConversionTextArea.setFont(afterConversionTextArea.getFont().deriveFont(Font.BOLD));
        afterConversionTextArea.setBounds(280, 180, 165, 25);
        afterConversionTextArea.setEditable(false);
        mainPanel.add(afterConversionTextArea);

        JLabel afterConversionLabel = new JLabel("After Conversion:");
        afterConversionLabel.setForeground(Color.white);
        afterConversionLabel.setBounds(290, 155, 165, 25);
        mainPanel.add(afterConversionLabel);

        convertButton = new JButton("Convert");
        convertButton.setOpaque(true);
        convertButton.setForeground(Color.black);
        convertButton.setBackground(Color.gray);
        convertButton.setBounds(325, 110, 80, 25);
        mainPanel.add(convertButton);

        mainPanel.add(convertButton);
        this.add(mainPanel);
        this.setVisible(true);
    }

    public void showErrorPopup(String error) {
        JDialog errorDialog = new JDialog(this, true);
        errorDialog.getContentPane().setBackground(Color.darkGray);
        errorDialog.setTitle("Error");
        errorDialog.setSize(300,100);
        errorDialog.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        JLabel errorLabel = new JLabel(error, SwingConstants.CENTER);
        errorLabel.setForeground(Color.black);

        JButton closeButton = new JButton("close");
        closeButton.setForeground(Color.black);
        closeButton.setBackground(Color.gray);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        errorDialog.add(errorLabel, BorderLayout.CENTER);
        errorDialog.add(closeButton, BorderLayout.SOUTH);
        errorDialog.setResizable(false);
        errorDialog.setLocationRelativeTo(this);
        errorDialog.setVisible(true);
    }

    public void setRateTextArea(String rate) {
        this.rateValueLabel.setText(rate);
    }

    public void setAfterConversionTextArea(String result) {
        this.afterConversionTextArea.setText(result);
    }

    public void fillComboBox(String item) {
        fromComboBox.addItem(item);
        toComboBox.addItem(item);
    }

    public int getFromIndex() {
        return fromComboBox.getSelectedIndex();
    }

    public int getToIndex() {
        return toComboBox.getSelectedIndex();
    }

    public double getAmount() {
        return Double.parseDouble(amountTextField.getText());
    }

    public void addConvertListener(ActionListener click) {
        convertButton.addActionListener(click);
    }
}
