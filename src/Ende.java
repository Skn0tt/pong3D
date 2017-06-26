import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ende extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JLabel lblAusgabe;

    private Game game;

    public Ende(String text, Game pGame) {
        this.game = pGame;
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        lblAusgabe.setText(text);
        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }

        });
    }

    private void onOK() {
        game.beenden();
        dispose();
    }
/*
    public static void main(String[] args) {
        Ende dialog = new Ende();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
    */
}
