package com.Dilina;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Collections;
import java.util.List;

public class ManageDriversFrame extends JFrame implements ActionListener,Serializable {

    private final JButton addButton,delButton,buttonHome;
    private final JTextField addNameField,addTeamField,addLocField;
    private final JTextField delNameField,delTeamField;

    private final Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();

    ManageDriversFrame(){
        // deserialize/load program data
        championshipManager.loadData("championshipData.ser");

        // input field info labels for adding a driver
        JLabel nameLabel = new JLabel("Driver Name:");
        nameLabel.setFont(new Font("Calibre",Font.BOLD,15));
        JLabel teamLabel = new JLabel("Team Name:");
        teamLabel.setFont(new Font("Calibre",Font.BOLD,15));
        JLabel locLabel = new JLabel("Driver Location:");
        locLabel.setFont(new Font("Calibre",Font.BOLD,15));

        nameLabel.setBounds(50,50,100,100);
        teamLabel.setBounds(50,100,100,100);
        locLabel.setBounds(50,150,120,100);

        // initialise and modify name,team,location input fields
        addNameField = new JTextField();
        addTeamField = new JTextField();
        addLocField = new JTextField();

        addNameField.setBounds(170,85,150,25);
        addTeamField.setBounds(170,135,150,25);
        addLocField.setBounds(170,185,150,25);

        // initialize and load add button icon
        ImageIcon icon0 = new ImageIcon("addButton.png");
        // setup button borders
        Border border = BorderFactory.createLineBorder(new Color(0,100,0));

        // initialize and modify add driver button
        addButton = new JButton("ADD DRIVER");
        addButton.setBounds(50,240,300,60);
        addButton.addActionListener(this);
        addButton.setFocusable(false);
        addButton.setFont(new Font("Comic Sans",Font.BOLD,20));
        addButton.setIcon(icon0);
        addButton.setHorizontalTextPosition(JButton.RIGHT);
        addButton.setVerticalTextPosition(JButton.CENTER);
        addButton.setIconTextGap(15);
        addButton.setForeground(new Color(0,100,0));
        addButton.setBackground(new Color(152,251,152));
        addButton.setBorder(border);

        // add driver panel top header
        JLabel addLabel = new JLabel("ADD FORMULA DRIVER");
        addLabel.setBounds(50,10,300,50);
        addLabel.setHorizontalAlignment(JLabel.CENTER);
        addLabel.setFont(new Font("Comic Sans",Font.BOLD,25));

        // border for panels
        Border borderPanels = BorderFactory.createLineBorder(Color.cyan);

        // this panel contains all components for adding a driver
        JPanel addPanel = new JPanel();
        addPanel.setLayout(null);
        addPanel.setBounds(100,20,400,350);
        addPanel.setBackground(new Color(255, 195, 0));
        addPanel.add(addLabel);
        addPanel.add(nameLabel);
        addPanel.add(teamLabel);
        addPanel.add(locLabel);
        addPanel.add(addNameField);
        addPanel.add(addTeamField);
        addPanel.add(addLocField);
        addPanel.add(addButton);
        addPanel.setBorder(borderPanels);


        // delete driver panel header
        JLabel delLabel = new JLabel("DELETE EXISTING DRIVER");
        delLabel.setBounds(25,10,350,50);
        delLabel.setHorizontalAlignment(JLabel.CENTER);
        delLabel.setFont(new Font("Comic Sans",Font.BOLD,25));

        // input field info labels for deleting a driver
        JLabel delNameLabel = new JLabel("Driver Name:");
        delNameLabel.setFont(new Font("Calibre",Font.BOLD,15));
        JLabel delTeamLabel = new JLabel("Team Name:");
        delTeamLabel.setFont(new Font("Calibre",Font.BOLD,15));

        delNameLabel.setBounds(50,50,100,100);
        delTeamLabel.setBounds(50,100,100,100);

        // initialize and modify input fields for deleting driver
        delNameField = new JTextField();
        delTeamField = new JTextField();

        delNameField.setBounds(150,85,150,25);
        delTeamField.setBounds(150,135,150,25);

        // setup button borders
        Border border2 = BorderFactory.createLineBorder(new Color(139,0,0));
        // initialize and load delete button icon
        ImageIcon icon2 = new ImageIcon("deleteButton.png");

        // initialize and modify delete button
        delButton = new JButton("DELETE DRIVER");
        delButton.setBounds(50,200,300,60);
        delButton.addActionListener(this);
        delButton.setFocusable(false);
        delButton.setFont(new Font("Comic Sans",Font.BOLD,20));
        delButton.setIcon(icon2);
        delButton.setHorizontalTextPosition(JButton.RIGHT);
        delButton.setVerticalTextPosition(JButton.CENTER);
        delButton.setIconTextGap(15);
        delButton.setForeground(new Color(139,0,0));
        delButton.setBackground(new Color(180,128,114));
        delButton.setBorder(border2);

        // this panel contains all components for deleting a driver
        JPanel deletePanel = new JPanel();
        deletePanel.setLayout(null);
        deletePanel.setBounds(100,380,400,300);
        deletePanel.setBackground(new Color(255, 195, 0));
        deletePanel.add(delLabel);
        deletePanel.add(delNameLabel);
        deletePanel.add(delTeamLabel);
        deletePanel.add(delNameField);
        deletePanel.add(delTeamField);
        deletePanel.add(delButton);
        deletePanel.setBorder(borderPanels);

        // initialize and load home/back button icon
        ImageIcon iconHome = new ImageIcon("home.png");

        // initialize and modify home/back button
        buttonHome = new JButton("HOME");
        buttonHome.setBounds(10,710,80,40);
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

        // initialize and load frame icon
        ImageIcon icon = new ImageIcon("frameIcon.png");

        // set main frame properties and add above initialized components
        this.setTitle(" Manage Formula1 Drivers");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,800);
        this.setIconImage(icon.getImage());
        Container pane = this.getContentPane();
        pane.setBackground(new Color(0,0,50));
        this.setResizable(false);
        this.setLayout(null);
        this.add(addPanel);
        this.add(deletePanel);
        this.add(buttonHome);
        this.setVisible(true);
        centeredFrame(this);
    }

    public void centeredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int cordX = (objDimension.width - objFrame.getWidth()) / 2;
        int cordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(cordX, cordY);
    }

    // this class is implements ActionListener interface
    // override method to handle button events
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton){
            // this method handles add button functionality
            addButtonValidator();
            championshipManager.saveData("championshipData.ser");
        }
        else if (e.getSource() == delButton){
            // this method handles delete button functionality
            deleteButtonValidator();
            championshipManager.saveData("championshipData.ser");
        }
        // home button validation
        else if (e.getSource()==buttonHome){
            this.dispose();
            MyFrame frame = new MyFrame();
        }
    }

    // this method validated and add driver to competition
    // this method handles add button functionality
    public void addButtonValidator(){

        // load program data
        championshipManager.loadData("championshipData.ser");
        String name = addNameField.getText();
        String team = addTeamField.getText();
        String location = addLocField.getText();

        // check all details are filled
        if (name.isEmpty() || team.isEmpty() || location.isEmpty()){
            JOptionPane.showMessageDialog(null,"Fields Cannot Be Empty!","Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        // check entered driver team is already exist
        else if (isManufacturerExist(championshipManager.getFormula1Drivers(),team)){
            JOptionPane.showMessageDialog(null,"Constructor Team" + "(" + team+")"+ "Already Exist!","Should be Unique!",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            // show adding confirmation message
            Object[] choices = {"Yes", "Edit"};
            int answer = JOptionPane.showOptionDialog(null,"Are you sure to Add " +
                    name+"("+team+")?","Confirmation!",JOptionPane.YES_NO_OPTION,JOptionPane.INFORMATION_MESSAGE,
                    null,choices,0);
            // if user confirmed
            if (answer==0){
                JOptionPane.showMessageDialog(null,"Driver Successfully Added!","Done!",
                        JOptionPane.INFORMATION_MESSAGE);
                addNameField.setText("");
                addTeamField.setText("");
                addLocField.setText("");
                championshipManager.addNewDriver(name,location,team); // add new driver
                championshipManager.getFormula1Drivers().sort(Collections.reverseOrder());
                championshipManager.saveData("championshipData.ser"); // save program data
            }
        }
    }

    // this method validated and delete existing driver from competition
    // this method handles delete button functionality
    public void deleteButtonValidator(){

        // load program data
        championshipManager.loadData("championshipData.ser");
        String name = delNameField.getText();
        String team = delTeamField.getText();

        // check drivers are in the competition
        if (championshipManager.getFormula1Drivers().isEmpty()){
            JOptionPane.showMessageDialog(null,"Currently There are no Drivers to Remove!","Empty!",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            // check all details are filled
            if (name.isEmpty() || team.isEmpty()){
                JOptionPane.showMessageDialog(null,"Fields Cannot Be Empty!","Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            // check if selected driver is exists
            else {
                // calling method to get driver
                Formula1Driver driver = championshipManager.getDriverByNameAndTeam(name,team);
                // make sure driver is founded
                if (driver != null){
                    // delete confirmation alert
                    int answer = JOptionPane.showConfirmDialog(null,"Are you sure to Delete " +
                            name+"("+team+")?","Confirmation!",JOptionPane.YES_NO_OPTION);
                    // if deletion confirmed
                    if (answer==0){
                        JOptionPane.showMessageDialog(null,"Driver Successfully Deleted!",
                                "Done!",
                                JOptionPane.INFORMATION_MESSAGE);
                        delNameField.setText("");
                        delTeamField.setText("");
                        championshipManager.getFormula1Drivers().remove(driver); // remove driver
                        championshipManager.saveData("championshipData.ser"); // save program data
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null,"Driver Did not Find!","Error!",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    // method to check if selected constructor team already exists
    public static boolean isManufacturerExist(List<Formula1Driver> drivers,String manufacturer) {
        for (Formula1Driver driver : drivers) {
            if (driver.getDriverManufactureTeam().equalsIgnoreCase(manufacturer)) {
                return true;
            }
        }
        return false;
    }
}
