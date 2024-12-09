package businesslogic.JFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class getFrqme extends JFrame {

    private JButton jButton;
    private  JPanel jPanel;

    public getFrqme() throws HeadlessException {
        setTitle("Wekcome");
        setContentPane(jPanel);
        setMinimumSize(new Dimension(400,750));
        setSize(1200,700);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
               System.out.println("jButton");
            }
        });

    }


}
