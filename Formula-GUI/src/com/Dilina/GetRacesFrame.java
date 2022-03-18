package com.Dilina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GetRacesFrame extends JFrame implements ActionListener, Serializable {
    Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
    private final ArrayList<Integer> positions = new ArrayList<>();
    private final JButton buttonUpdate;
    private final JButton buttonHome;
    private final JTextField yearField,monthField,dayField;

    GetRacesFrame(){
        // deserialize/load program data
        championshipManager.loadData("championshipData.ser");

        // initialize and modify date update button
        buttonUpdate = new JButton("UPDATE SCORES");
        buttonUpdate.setBounds(415,330,170,30);
        buttonUpdate.addActionListener(this);
        buttonUpdate.setFocusable(false);
        buttonUpdate.setHorizontalTextPosition(JButton.RIGHT);
        buttonUpdate.setVerticalTextPosition(JButton.CENTER);
        buttonUpdate.setFont(new Font("Comic Sans",Font.BOLD,12));
        buttonUpdate.setIconTextGap(25);
        buttonUpdate.setForeground(new Color(255,195,0));
        buttonUpdate.setBackground(new Color(0,0,50));
        buttonUpdate.setBorder(BorderFactory.createLineBorder(new Color(255,195,0)));

        // initialize and load home button icon
        ImageIcon iconHome = new ImageIcon("home.png");

        // initialize and modify home button
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

        // initialize and modify year,month,date labels and text fields
        JLabel yearLabel = new JLabel("Year:");
        yearLabel.setOpaque(true);
        yearLabel.setBackground(new Color(255,195,0));
        yearLabel.setHorizontalAlignment(JLabel.CENTER);
        yearField = new JTextField();
        yearField.setHorizontalAlignment(JLabel.CENTER);
        JLabel monthLabel = new JLabel("Month:");
        monthLabel.setOpaque(true);
        monthLabel.setBackground(new Color(255,195,0));
        monthLabel.setHorizontalAlignment(JLabel.CENTER);
        monthField = new JTextField();
        monthField.setHorizontalAlignment(JLabel.CENTER);
        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setOpaque(true);
        dayLabel.setBackground(new Color(255,195,0));
        dayLabel.setHorizontalAlignment(JLabel.CENTER);
        dayField = new JTextField();
        dayField.setHorizontalAlignment(JLabel.CENTER);

        // this panel contains year,month,date labels and text fields
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(0,1));
        datePanel.setBounds(400,100,200,200);
        datePanel.setBackground(new Color(0,0,50));
        datePanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        datePanel.add(yearLabel);
        datePanel.add(yearField);
        datePanel.add(monthLabel);
        datePanel.add(monthField);
        datePanel.add(dayLabel);
        datePanel.add(dayField);

        // frame heading label
        JLabel topLabel = new JLabel("UPDATE LATEST RACE SCORES");
        topLabel.setBounds(50,40,600,50);
        topLabel.setForeground(Color.white);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setVerticalAlignment(JLabel.CENTER);
        topLabel.setFont(new Font("Comic Sans",Font.BOLD,32));

        // initialize and load frame icon
        ImageIcon icon = new ImageIcon("frameIcon.png");

        // set main frame properties and add above initialized components
        this.setTitle("Manage Formula Races");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(700,500);
        this.setIconImage(icon.getImage());
        this.add(topLabel);
        this.setResizable(false);
        this.setLayout(null);
        this.add(setDetails());
        this.add(datePanel);
        this.add(buttonHome);
        this.add(buttonUpdate);
        Container pane = this.getContentPane();
        pane.setBackground(new Color(0,0,50));
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

    // this method generates a unique number everytime
    public int uniquePositionGenerator(int x, ArrayList<Integer> positions){
        int number;
        do {
            Random random = new Random();
             number = random.nextInt(x)+1;
        }
        while (positions.contains(number));
        positions.add(number);
        return number;
    }

    // this method update all the race details and set view
    public JPanel setDetails(){
        // load program data
        championshipManager.loadData("championshipData.ser");

        // this method returns a manually created grid panel with updated race data

        // name header
        JLabel name = new JLabel("NAME");
        name.setOpaque(true);
        name.setVerticalAlignment(JLabel.CENTER);
        name.setHorizontalAlignment(JLabel.CENTER);
        name.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        name.setBackground(new Color(192,0,0));
        name.setForeground(Color.WHITE);

        // position header
        JLabel position = new JLabel("POSITION");
        position.setOpaque(true);
        position.setVerticalAlignment(JLabel.CENTER);
        position.setHorizontalAlignment(JLabel.CENTER);
        position.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        position.setBackground(new Color(192,0,0));
        position.setForeground(Color.WHITE);

        // this panel contains name, position headers
        JPanel panelHead = new JPanel();
        panelHead.setLayout(new GridLayout(1,2,5,0));
        panelHead.setBackground(new Color(0,0,50));
        panelHead.add(name);
        panelHead.add(position);

        // this panel contains all the manually created head panels and detail labels
        // note-this contains a grid layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0,1,0,10));
        mainPanel.setBackground(Color.BLACK);
        mainPanel.setBounds(50,100,300,300);
        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(panelHead);

        // make sure drivers are in the competition
       if (!championshipManager.getFormula1Drivers().isEmpty()){
           for (Formula1Driver driver:championshipManager.getFormula1Drivers()){

               // set name label for each driver
               JLabel nameLabel = new JLabel(driver.getDriverName());
               nameLabel.setVerticalAlignment(JLabel.CENTER);
               nameLabel.setHorizontalAlignment(JLabel.CENTER);
               nameLabel.setForeground(new Color(255,195,0));
               nameLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));

               // set position inside text field for each driver
               // this method returns a unique number every time
               int pos = uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),positions);
               JTextField positionField = new JTextField();
               positionField.setText(String.valueOf(pos));
               positionField.setHorizontalAlignment(JTextField.CENTER);
               position.setBorder(BorderFactory.createLineBorder(Color.WHITE));
               positionField.setEditable(false);

               // adding updated name,position to main panel
               JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(1,2,5,0));
               panel.setBackground(new Color(0,0,50));
               panel.add(nameLabel);
               panel.add(positionField);

               mainPanel.setBounds(50,100,300,30*championshipManager.getFormula1Drivers().size()+30);
               mainPanel.add(panel);

           }
           return mainPanel;
       }
       // if no drivers in the competition
       else {
           yearField.setEnabled(false);
           monthField.setEnabled(false);
           dayField.setEnabled(false);
           buttonUpdate.setEnabled(false);

           // set view to no drivers
           mainPanel.setBounds(50,100,300,100);
           JLabel label = new JLabel("THERE ARE NO DRIVERS IN THE COMPETITION!");
           label.setBounds(10,5,50,100);
           label.setOpaque(true);
           label.setHorizontalAlignment(JLabel.CENTER);
           label.setVerticalAlignment(JLabel.CENTER);
           mainPanel.add(label);
           return mainPanel;
       }
    }

    // this class is implements ActionListener interface
    // override method to handle button events
    @Override
    public void actionPerformed(ActionEvent e) {
        // validate home/back button
        if (e.getSource() == buttonHome){
            // user should update race date before leave
            if (buttonUpdate.isEnabled()){
                JOptionPane.showMessageDialog(null,"Please Update Scores!","Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            // after updating date details
            else {
                this.dispose();
                MyFrame frame = new MyFrame();
            }
        }
        // button to update race details and scores
        else if (e.getSource()==buttonUpdate){
            // calling method to update all race details
            onClickUpdateButton();
        }
    }

    // this method updates all the race details and scores
    public void onClickUpdateButton(){
        // check if year,month,day fields are not empty
        if (!yearField.getText().isEmpty() && !monthField.getText().isEmpty() && !dayField.getText().isEmpty()){
            try {
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int day = Integer.parseInt(dayField.getText());

                // validate and set date
                // note- all raceDate details are validated in Date class
                Date raceDate = new Date(year,month,day);
                yearField.setEnabled(false);
                monthField.setEnabled(false);
                dayField.setEnabled(false);
                buttonUpdate.setEnabled(false);

                List<Formula1Driver> participatedDrivers = new ArrayList<>(championshipManager.getFormula1Drivers());

                // calling method to update race scores.
                championshipManager.updateRaceDetails(raceDate,participatedDrivers,positions);
                JOptionPane.showMessageDialog(null,"Details Successfully Updated!","Done!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            // if date is invalid
            catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,"Invalid Date!","Error!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        // if input fields are empty
        else{
            JOptionPane.showMessageDialog(null,"Please Enter Date!","Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
