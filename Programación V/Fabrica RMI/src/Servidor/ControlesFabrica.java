import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlesFabrica extends JFrame implements ActionListener
{
    //Controls
    public JTextField boxesInputField;
    public JTextField paperInputField;
    public JButton startButton;
    public JButton cleanButton;

    private Factory fab;

    public ControlesFabrica(Factory fab)
    {
        this.fab = fab;

        JFrame frameControles = new JFrame("Fábrica - Cliente");
        frameControles.setLayout(new BorderLayout(5,10));

        JLabel title = new JLabel("Fábrica de Papel", SwingConstants.CENTER);

        JPanel boxesPanel = new JPanel();
        boxesPanel.setLayout(new GridLayout(0,2));
        JLabel boxesInputLabel = new JLabel("Número de cajas:", SwingConstants.CENTER);
        boxesPanel.add(boxesInputLabel);
        boxesInputField = new JTextField();
        boxesPanel.add(boxesInputField);

        JPanel inputsPanel = new JPanel(new GridLayout(2,0));

        JPanel paperPanel = new JPanel();
        paperPanel.setLayout(new GridLayout(0,2));
        JLabel paperInputLabel = new JLabel("Papel por caja:", SwingConstants.CENTER);
        paperPanel.add(paperInputLabel);
        paperInputField = new JTextField();
        paperPanel.add(paperInputField);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,0));

        startButton = new JButton("Fabricar");
        startButton.setActionCommand("fabricar");
        startButton.addActionListener(this);
        buttonsPanel.add(startButton);

        cleanButton = new JButton("Limpiar");
        cleanButton.setActionCommand("limpiar");
        cleanButton.addActionListener(this);
        buttonsPanel.add(cleanButton);

        inputsPanel.add(boxesPanel);
        inputsPanel.add(paperPanel);

        frameControles.add(title, BorderLayout.NORTH);
        frameControles.add(inputsPanel, BorderLayout.CENTER);
        frameControles.add(buttonsPanel, BorderLayout.SOUTH);

        frameControles.setSize(300,250);
        frameControles.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        String opc = e.getActionCommand();

        switch (opc)
        {
            case "fabricar":
                try
                {
                    int boxAmount = Integer.parseInt(this.boxesInputField.getText());
                    int paperAmount = Integer.parseInt(this.paperInputField.getText());

                    fab.startFactory(boxAmount, paperAmount);
                }
                catch (Exception exc)
                {
                    JOptionPane.showMessageDialog(null, "Parsing error: Input must be of type Integer.");
                }
                break;

            case "limpiar":
                try
                {
                    boxesInputField.setText("");
                    paperInputField.setText("");
                    fab.cleanFields();
                }
                catch (Exception exc)
                {
                    exc.printStackTrace();
                }

                break;
        }
    }
}
