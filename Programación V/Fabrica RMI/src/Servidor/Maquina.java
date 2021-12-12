import javax.swing.*;
import java.awt.*;

class Machine extends Thread
{
    public JFrame frame;
    public JPanel mainPanel;
    public JPanel inputPanel;
    public JPanel resultsPanel;
    public JLabel boxesInputLabel;
    public JLabel paperInputLabel;
    public JTextField boxesInputField;
    public JTextField paperInputField;
    public JTextArea outputField;
    public JLabel producedPaperLabel;//Current + "out of" + Total
    public JLabel paperAmountLabel;
    public JLabel boxesAmountLabel;
    public JLabel activeBoxLabel;
    public JLabel currentPaperLabel;
    public JScrollPane scrollOutputPane;
    public JButton startButton;
    public JButton cleanButton;

    public void drawFrame(int width, int height, String title)
    {
        frame = new JFrame(title);

        mainPanel = new JPanel();
        mainPanel.setLayout(null);
        mainPanel.setBackground(new Color(0x2b2b2b));

        outputField = new JTextArea();
        boxesInputField = new JTextField();
        paperInputField = new JTextField();

        //region Result Panel
        resultsPanel = new JPanel();
        resultsPanel.setLayout(null);
        resultsPanel.setBackground(new Color(0x323335));
        resultsPanel.setBounds(5, 9, 677, 65);
        resultsPanel.setBorder(BorderFactory.createLineBorder(new Color(0x3b3f42)));

        boxesAmountLabel = new JLabel("Cantidad de cajas ");
        boxesAmountLabel.setForeground(Color.LIGHT_GRAY);
        boxesAmountLabel.setFont(new Font("roboto", Font.PLAIN, 14));
        boxesAmountLabel.setBounds(95, 1, 200, 20);
        resultsPanel.add(boxesAmountLabel);

        paperAmountLabel = new JLabel("Papel por caja ");
        paperAmountLabel.setForeground(Color.LIGHT_GRAY);
        paperAmountLabel.setFont(new Font("roboto", Font.PLAIN, 14));
        paperAmountLabel.setBounds(320, 1, 200, 20);
        resultsPanel.add(paperAmountLabel);

        activeBoxLabel = new JLabel("Caja en proceso ");
        activeBoxLabel.setForeground(Color.LIGHT_GRAY);
        activeBoxLabel.setFont(new Font("roboto", Font.PLAIN, 14));
        activeBoxLabel.setBounds(95, 32, 200, 30);
        resultsPanel.add(activeBoxLabel);

        currentPaperLabel = new JLabel("Papel cargado ");
        currentPaperLabel.setForeground(Color.LIGHT_GRAY);
        currentPaperLabel.setFont(new Font("roboto", Font.PLAIN, 14));
        currentPaperLabel.setBounds(320, 32, 200, 30);
        resultsPanel.add(currentPaperLabel);

        producedPaperLabel = new JLabel("Procesadas ");
        producedPaperLabel.setForeground(Color.LIGHT_GRAY);
        producedPaperLabel.setFont(new Font("roboto", Font.PLAIN, 14));
        producedPaperLabel.setBounds(500, 16, 200, 30);
        resultsPanel.add(producedPaperLabel);

        mainPanel.add(resultsPanel);
        //endregion

        //region Output Label
        outputField = new JTextArea();
        outputField.setOpaque(true);
        outputField.setEditable(false);
        outputField.setFont(new Font("roboto", Font.PLAIN, 16));
        outputField.setForeground(Color.LIGHT_GRAY);
        outputField.setBackground(new Color(0x3b3f42));

        scrollOutputPane = new JScrollPane(outputField);
        scrollOutputPane.setBounds(5, 80, 680, 332);
        scrollOutputPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollOutputPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        mainPanel.add(scrollOutputPane);
        //endregion

        frame.add(mainPanel);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public void startFactory(int boxAmount, int paperAmount)
    {
        try
        {
            producedPaperLabel.setText("Procesadas ");
            paperAmountLabel.setText("Papel por caja ");
            boxesAmountLabel.setText("Cantidad de cajas ");
            activeBoxLabel.setText("Caja en proceso ");
            currentPaperLabel.setText("Papel cargado ");

            Hilo hilo = new Hilo(boxAmount, paperAmount, this);
            hilo.start();
        }
        catch (Exception exc)
        {
            JOptionPane.showMessageDialog(null, "Parsing error: Input must be of type Integer.");
        }
    }

    public void cleanFields()
    {
        outputField.setText("");
        boxesInputField.setText("");
        paperInputField.setText("");
    }
}
