package com.Dilina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class ViewRacesFrame extends JFrame implements ActionListener, Serializable {
    Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
    private final JButton buttonHome;
    ViewRacesFrame(){
        // load program data
        championshipManager.loadData("championshipData.ser");

        // initialize and load home/back button icon image
        ImageIcon iconHome = new ImageIcon("home.png");

        // initialize and modify home/back button
        buttonHome = new JButton("HOME");
        buttonHome.setBounds(10,10,80,40);
        buttonHome.addActionListener(this);
        buttonHome.setFocusable(false);
        buttonHome.setIcon(iconHome);
        buttonHome.setHorizontalTextPosition(JButton.RIGHT);
        buttonHome.setVerticalTextPosition(JButton.CENTER);
        buttonHome.setFont(new Font("Comic Sans",Font.BOLD,12));
        buttonHome.setIconTextGap(2);
        buttonHome.setForeground(new Color(255,195,0));
        buttonHome.setBackground(new Color(0,0,50));
        buttonHome.setBorder(BorderFactory.createLineBorder(new Color(255,195,0)));

        // frame top heading
        JLabel topLabel = new JLabel("ALL FORMULA1 RACE DETAILS");
        topLabel.setBounds(50,60,600,30);
        topLabel.setForeground(Color.white);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setVerticalAlignment(JLabel.CENTER);
        topLabel.setFont(new Font("Comic Sans",Font.BOLD,25));

        // initialize and load frame icon image
        ImageIcon icon = new ImageIcon("frameIcon.png");

        // set main frame properties and add above initialized components
        this.setTitle("View Race Details");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,400);
        this.setIconImage(icon.getImage());
        Container pane = this.getContentPane();
        pane.setBackground(new Color(0,0,50));
        this.add(topLabel);
        this.setResizable(false);
        this.add(setDetails());
        this.setLayout(null);
        this.add(buttonHome);
        this.setVisible(true);
        centeredFrame(this);
    }

    // method to center frame on screen
    public void centeredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int cordX = (objDimension.width - objFrame.getWidth()) / 2;
        int cordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(cordX, cordY);
    }

    // this method set race details and set view to grid panel and return
    public JPanel setDetails(){

        // load program data
        championshipManager.loadData("championshipData.ser");

        // this method returns a manually created grid panel with updated race data

        // date header
        JLabel date = new JLabel("RACE DATE");
        date.setOpaque(true);
        date.setVerticalAlignment(JLabel.CENTER);
        date.setHorizontalAlignment(JLabel.CENTER);
        date.setBorder(BorderFactory.createLineBorder(Color.red));
        date.setBackground(new Color(255,195,0));

        // total drivers header
        JLabel totalDrivers = new JLabel("TOTAL DRIVERS");
        totalDrivers.setOpaque(true);
        totalDrivers.setVerticalAlignment(JLabel.CENTER);
        totalDrivers.setHorizontalAlignment(JLabel.CENTER);
        totalDrivers.setBorder(BorderFactory.createLineBorder(Color.red));
        totalDrivers.setForeground(Color.WHITE);
        totalDrivers.setBackground(Color.red);

        // race winner header
        JLabel winnerName = new JLabel("WINNER");
        winnerName.setOpaque(true);
        winnerName.setVerticalAlignment(JLabel.CENTER);
        winnerName.setHorizontalAlignment(JLabel.CENTER);
        winnerName.setBorder(BorderFactory.createLineBorder(Color.red));
        winnerName.setBackground(new Color(255,195,0));

        // race 2nd place header
        JLabel secondPlace = new JLabel("SECOND PLACE");
        secondPlace.setOpaque(true);
        secondPlace.setVerticalAlignment(JLabel.CENTER);
        secondPlace.setHorizontalAlignment(JLabel.CENTER);
        secondPlace.setBorder(BorderFactory.createLineBorder(Color.red));
        secondPlace.setForeground(Color.WHITE);
        secondPlace.setBackground(Color.red);

        // race 3rd place header
        JLabel thirdPlace = new JLabel("THIRD PLACE");
        thirdPlace.setOpaque(true);
        thirdPlace.setVerticalAlignment(JLabel.CENTER);
        thirdPlace.setHorizontalAlignment(JLabel.CENTER);
        thirdPlace.setBorder(BorderFactory.createLineBorder(Color.red));
        thirdPlace.setBackground(new Color(255,195,0));

        // this grid layout contains all above headers
        // set a table view using grid layout
        JPanel panelHead = new JPanel();
        panelHead.setLayout(new GridLayout(1,6));
        panelHead.add(date);
        panelHead.add(totalDrivers);
        panelHead.add(winnerName);
        panelHead.add(secondPlace);
        panelHead.add(thirdPlace);

        // the main grid panel contains data and headers
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,1));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(100,100,500,300);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(panelHead);

        // check least one race happened
        if (!championshipManager.getRaces().isEmpty()){
            for (Race race:championshipManager.getRaces()){

                // retrieve race date and set
                JLabel dateLabel = new JLabel(race.getRaceDate().toString());
                dateLabel.setVerticalAlignment(JLabel.CENTER);
                dateLabel.setHorizontalAlignment(JLabel.CENTER);
                dateLabel.setBorder(BorderFactory.createEtchedBorder());
                dateLabel.setBackground(Color.cyan);

                // retrieve race total participants and set
                JLabel participation = new JLabel(String.valueOf(race.getParticipatedDrivers().size()));
                participation.setVerticalAlignment(JLabel.CENTER);
                participation.setHorizontalAlignment(JLabel.CENTER);
                participation.setBorder(BorderFactory.createEtchedBorder());
                participation.setBackground(Color.cyan);

                // retrieve race winner and set
                JLabel winner = new JLabel(race.getWinner());
                winner.setVerticalAlignment(JLabel.CENTER);
                winner.setHorizontalAlignment(JLabel.CENTER);
                winner.setBorder(BorderFactory.createEtchedBorder());
                winner.setBackground(Color.cyan);

                // retrieve race 2nd place and set
                JLabel second = new JLabel(race.getSecond());
                second.setVerticalAlignment(JLabel.CENTER);
                second.setHorizontalAlignment(JLabel.CENTER);
                second.setBorder(BorderFactory.createEtchedBorder());
                second.setBackground(Color.cyan);

                // retrieve race 3rd place and set
                JLabel third = new JLabel(race.getThird());
                third.setVerticalAlignment(JLabel.CENTER);
                third.setHorizontalAlignment(JLabel.CENTER);
                third.setBorder(BorderFactory.createEtchedBorder());
                third.setBackground(Color.cyan);

                // adding all updated data labels to main panel
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(1,6));
                panel.add(dateLabel);
                panel.add(participation);
                panel.add(winner);
                panel.add(second);
                panel.add(third);

                mainPanel.setBounds(100,100,500,20*championshipManager.getRaces().size()+30);
                mainPanel.add(panel);

            }
            return mainPanel;
        }
        // if there are no races happened upto
        else {
            // set label to display no races happened
            mainPanel.setBounds(50,100,600,100);
            JLabel label = new JLabel("CURRENTLY THERE ARE NO RACE DETAILS TO SHOW!");
            label.setFont(new Font("Comic Sans",Font.BOLD,20));
            label.setBounds(10,5,50,100);
            label.setOpaque(true);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            mainPanel.add(label);
            return mainPanel;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // validate back/home button
        if (e.getSource()==buttonHome){
            this.dispose();
            MyFrame frame = new MyFrame();
        }
    }
}
