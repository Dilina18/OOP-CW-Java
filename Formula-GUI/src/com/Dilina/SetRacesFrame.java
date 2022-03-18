package com.Dilina;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SetRacesFrame extends JFrame implements ActionListener, Serializable {
    Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
    private final JButton buttonHome;
    private JTable table;
    private final JPanel panel;
    private final JLabel panelLabel;
    private final JTextField yearField,monthField,dayField;
    private final JButton buttonUpdate;
    private boolean isFirstPlaceAchieved = false;
    private final ArrayList<Integer> startPositions = new ArrayList<>();
    private final ArrayList<Integer> ensPositions = new ArrayList<>();
    SetRacesFrame(){
        // load program data
        championshipManager.loadData("championshipData.ser");

        // initialize and modify race date update button
        buttonUpdate = new JButton("UPDATE SCORES");
        buttonUpdate.setBounds(665,400,170,30);
        buttonUpdate.addActionListener(this);
        buttonUpdate.setFocusable(false);
        buttonUpdate.setHorizontalTextPosition(JButton.RIGHT);
        buttonUpdate.setVerticalTextPosition(JButton.CENTER);
        buttonUpdate.setFont(new Font("Comic Sans",Font.BOLD,12));
        buttonUpdate.setIconTextGap(25);
        buttonUpdate.setForeground(new Color(255,195,0));
        buttonUpdate.setBackground(new Color(0,0,50));
        buttonUpdate.setBorder(BorderFactory.createLineBorder(new Color(255,195,0)));

        // initialize and load home/back button icon
        ImageIcon iconHome = new ImageIcon("home.png");

        // initialize and modify back/home button
        buttonHome = new JButton("Home");
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

        // initialize and modify label to display no drivers error
        panelLabel = new JLabel("THERE ARE NO DRIVERS IN THE COMPETITION!");
        panelLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panelLabel.setHorizontalAlignment(JLabel.CENTER);
        panelLabel.setFont(new Font("Calibre",Font.BOLD,15));
        panelLabel.setBounds(5,30,490,40);
        panelLabel.setOpaque(true);
        panelLabel.setBackground(Color.RED);
        panelLabel.setForeground(Color.WHITE);

        // this panel contains above label
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);
        panel.add(panelLabel);
        panel.setBounds(100,240,800,200);
        panel.setBorder(BorderFactory.createLineBorder(Color.cyan));

        // // initialize and modify race details table
        table = setTableDetails(championshipManager.getFormula1Drivers());
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setEnabled(false);
        table.getTableHeader().setBackground(new Color(192,0,0));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Calibre",Font.BOLD,13));
        setTableHeight(championshipManager.getFormula1Drivers().size()); // calling method to set table height

        // set table sorter to sort according to starting position
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(sorter);
        List<RowSorter.SortKey> sortKeys = new ArrayList<>();
        System.out.println(sortKeys);
        sortKeys.add(new RowSorter.SortKey(0, SortOrder.ASCENDING));
        sorter.setSortKeys(sortKeys);

        // set table inside scrollable pane
        JScrollPane pane = new JScrollPane(table);
        // place scrollable inside panel
        panel.add(pane);

        // initialize and modify year,month,day labels and text fields

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

        // this panel contains all year,month,day labels and input fields
        JPanel datePanel = new JPanel();
        datePanel.setLayout(new GridLayout(0,1));
        datePanel.setBounds(650,180,200,200);
        datePanel.setBackground(new Color(0,0,50));
        datePanel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        datePanel.add(yearLabel);
        datePanel.add(yearField);
        datePanel.add(monthLabel);
        datePanel.add(monthField);
        datePanel.add(dayLabel);
        datePanel.add(dayField);

        // frame header
        JLabel topLabel = new JLabel("UPDATE LATEST RACE SCORES");
        topLabel.setBounds(200,60,600,50);
        topLabel.setForeground(Color.white);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setVerticalAlignment(JLabel.CENTER);
        topLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        topLabel.setFont(new Font("Comic Sans",Font.BOLD,32));

        // initialize and load frame icon image
        ImageIcon icon = new ImageIcon("frameIcon.png");

        // set main frame properties and add above initialized components
        this.setTitle("Manage Formula Races");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,600);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.setLayout(null);
        this.add(topLabel);
        this.add(datePanel);
        this.add(buttonUpdate);
        Container mainPane = this.getContentPane();
        mainPane.setBackground(new Color(0,0,50));
        this.add(panel);
        this.add(buttonHome);
        this.setVisible(true);
        centeredFrame(this);
    }

    // this method center frame on screen
    public void centeredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int cordX = (objDimension.width - objFrame.getWidth()) / 2;
        int cordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(cordX, cordY);
    }

    // this method returns an unique number/position each time
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

    // this method handles probability of getting first place according to a given starting position
    public int probabilisticPosition(int startPos){
        Random random = new Random();
        int number = random.nextInt(100)+1; // generate random number between 1-100
        if (startPos == 1){
            // check if first place already achieved
            if (isFirstPlaceAchieved){
                // calling method to generate position without checking probability
                return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
            }
            // if 1st position still available
            else {
                // this condition apply 40% probability to get 1st place
                if (number <= 40){
                    isFirstPlaceAchieved = true; // set 1st place achieved
                    ensPositions.add(1); // adding to position list
                    return 1;
                }
                // if number out of probability range again generate a unique position without probability
                else {
                    return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
                }
            }
        }
        else if (startPos == 2){
            if (isFirstPlaceAchieved){
                return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
            }
            else {
                if (number <= 30){
                    isFirstPlaceAchieved = true;
                    ensPositions.add(1);
                    return 1;
                }
                else {
                    return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
                }
            }
        }
        else if (startPos == 3 || startPos == 4){
            if (isFirstPlaceAchieved){
                return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
            }
            else {
                if (number <= 10){
                    isFirstPlaceAchieved = true;
                    ensPositions.add(1);
                    return 1;
                }
                else {
                    return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
                }
            }
        }
        else if (startPos == 5 || startPos == 6 || startPos == 7 || startPos == 8 || startPos == 9){
            if (isFirstPlaceAchieved){
                return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
            }
            else {
                if (number <= 2){
                    isFirstPlaceAchieved = true;
                    ensPositions.add(1);
                    return 1;
                }
                else {
                    return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);
                }
            }
        }
        // if achieved position 10 greater probability will not apply
        else return uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),ensPositions);

    }

    // this method set all race details to table and returns to set view
    public JTable setTableDetails(List<Formula1Driver> drivers){

        // load program data
        championshipManager.loadData("championshipData.ser");

        // set table data and headers
        String[][] data = new String[championshipManager.getFormula1Drivers().size()][3];
        String[] columnNames = {"Starting Position", "Driver Name", "End Position"};
        // check there are drivers to generate race
        if (drivers.isEmpty()){
            panel.setBounds(100,180,500,80);
            panelLabel.setVisible(true); // view no drivers table
            buttonUpdate.setEnabled(false);
        }
        else {
            int i = 0;
            panelLabel.setVisible(false);
            // updating table data
            for (Formula1Driver driver: drivers){

                // calling this method generate and returns a unique starting position
                int startingPos = uniquePositionGenerator(championshipManager.getFormula1Drivers().size(),startPositions);

                // calling this method set end point according to above generated starting position
                int endPosition = probabilisticPosition(startingPos);
                String [] rows = {String.valueOf(startingPos), driver.getDriverName(), String.valueOf(endPosition)};
                data[i] = rows;
                i++;
            }
        }
        return table = new JTable(data,columnNames); // add headers,data and return updated table
    }

    // this method settle table bounds when data adding
    public void setTableHeight(int num){
        championshipManager.saveData("championshipData.ser");
        if (!championshipManager.getFormula1Drivers().isEmpty()){
            int height = 17*num + 24;
            panel.setBounds(100,180,500,height);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonHome){
            if (buttonUpdate.isEnabled()){
                JOptionPane.showMessageDialog(null,"Please Update Scores!","Error!",
                        JOptionPane.ERROR_MESSAGE);
            }
            else {
                this.dispose();
                MyFrame frame = new MyFrame();
            }
        }
        else if (e.getSource()==buttonUpdate){
            onClickUpdateButton();
        }
    }

    // this method validate and updates all race details
    // handles update button functionality
    public void onClickUpdateButton(){

        // check if input fields are empty
        if (!yearField.getText().isEmpty() && !monthField.getText().isEmpty() && !dayField.getText().isEmpty()){
            try {
                int year = Integer.parseInt(yearField.getText());
                int month = Integer.parseInt(monthField.getText());
                int day = Integer.parseInt(dayField.getText());

                // validate and set race date
                // note-date validations are already handled in Date class
                Date raceDate = new Date(year,month,day);
                yearField.setEnabled(false);
                monthField.setEnabled(false);
                dayField.setEnabled(false);
                buttonUpdate.setEnabled(false);

                List<Formula1Driver> participatedDrivers = new ArrayList<>(championshipManager.getFormula1Drivers());

                // calling method to update race details
                championshipManager.updateRaceDetails(raceDate,participatedDrivers,ensPositions);
                JOptionPane.showMessageDialog(null,"Details Successfully Updated!","Done!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
            // if entered date is invalid
            catch (IllegalArgumentException e){
                JOptionPane.showMessageDialog(null,"Invalid Date!","Error!",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
        // if fields are empty
        else{
            JOptionPane.showMessageDialog(null,"Please Enter Date!","Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
