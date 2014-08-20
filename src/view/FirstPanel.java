package view;

import control.ActionManager;

import javax.swing.*;
import java.awt.*;

public class FirstPanel extends JPanel {
    private ActionManager actionManager;

    public FirstPanel(ActionManager actionManager) {
        super(new BorderLayout());
        this.actionManager = actionManager;

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.updateComponentTreeUI(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

        addPanels();
    }

    private void addPanels() {
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.LIGHT_GRAY);
        JLabel title = new JLabel("FINAL INVESTIGACIÓN OPERATIVA");
        title.setForeground(Color.BLACK);
        titlePanel.add(title);

        JPanel centerPanel = new JPanel(new GridLayout(0, 1, 30, 30));
        centerPanel.setBackground(Color.decode("3107669"));
        centerPanel.setBorder(BorderFactory.createLineBorder(Color.decode("3107669"), 20));
        JButton firstBt = new JButton(actionManager.getSelectFirstSimulation());
        firstBt.setText("CASO H: INTERRUPCIÓN CON REINICIACIÓN E IMPACIENCIA");
        firstBt.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        firstBt.setContentAreaFilled(false);
        firstBt.setForeground(Color.white);
//        firstBt.setIcon(new ImageIcon((getClass().getResource("./images/PlayerBlue.png"))));

        JButton secondBt = new JButton(actionManager.getSelectSecondSimulation());
        secondBt.setContentAreaFilled(false);
        secondBt.setText("CASO F: INTERRUPCIÓN CON REANUDACIÓN E IMPACIENCIA");
        secondBt.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        secondBt.setForeground(Color.white);
//        secondBt.setIcon(new ImageIcon((getClass().getResource("./images/PlayerBlue.png"))));

        JButton exitBt = new JButton(actionManager.getExitAction());
        exitBt.setContentAreaFilled(false);
        exitBt.setText("SALIR");
        exitBt.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        exitBt.setForeground(Color.white);
//        exitBt.setIcon(new ImageIcon((getClass().getResource("./images/exitIcon.png"))));

        centerPanel.add(secondBt);
        centerPanel.add(firstBt);
        centerPanel.add(exitBt);

        this.add(titlePanel, BorderLayout.NORTH);
        this.add(centerPanel, BorderLayout.CENTER);

    }

}
