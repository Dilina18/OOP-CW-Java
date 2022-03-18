package com.Dilina;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

public class SearchDriverFrame extends JFrame implements Serializable, ActionListener {
    Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
    private final JButton buttonHome;
    private final JTextField searchField;
    private final JButton searchButton;
    private final JTextArea textArea;

    SearchDriverFrame(){
        // load program data
        championshipManager.loadData("championshipData.ser");

        // initialize and load home/back button icon
        ImageIcon iconHome = new ImageIcon("home.png");

        // initialize and modify home/back button
        buttonHome = new JButton("HOME");
        buttonHome.setBounds(10,20,80,40);
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

        // frame top header
        JLabel topLabel = new JLabel("FIND DRIVER DETAILS");
        topLabel.setBounds(150,20,300,50);
        topLabel.setForeground(Color.white);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setVerticalAlignment(JLabel.CENTER);
        topLabel.setFont(new Font("Comic Sans",Font.BOLD,25));
        topLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE));


        // initialize and load search button icon
        ImageIcon iconSearch = new ImageIcon("search.png");

        // initialize and modify search button
        searchButton = new JButton("");
        searchButton.setBounds(360,80,70,25);
        searchButton.addActionListener(this);
        searchButton.setFocusable(false);
        searchButton.setIcon(iconSearch);
        searchButton.setHorizontalTextPosition(JButton.RIGHT);
        searchButton.setVerticalTextPosition(JButton.CENTER);
        searchButton.setFont(new Font("Comic Sans",Font.BOLD,12));
        searchButton.setForeground(Color.BLACK);
        searchButton.setBackground(new Color(255, 195, 0));
        searchButton.setBorder(BorderFactory.createEtchedBorder());

        // initialize and modify search field
        searchField = new JTextField();
        searchField.setBounds(170,80,180,25);
        searchField.setHorizontalAlignment(JLabel.CENTER);
        searchField.setBorder(BorderFactory.createLineBorder(Color.CYAN));

        // initialize and modify text area to show driver details
        textArea = new JTextArea(1,1);
        textArea.setFont(new Font("Comic Sans",Font.BOLD,16));
        textArea.setEditable(false);

        // this panel contains scrollable pane and textarea
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);
        panel.setBounds(100,120,400,300);
        panel.setBorder(BorderFactory.createLineBorder(Color.cyan));

        // initialize scrollable pane
        JScrollPane scrollPane = new JScrollPane(textArea); // add textarea
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(scrollPane); // add all to panel

        // initialize and load frame icon image
        ImageIcon icon = new ImageIcon("frameIcon.png");

        // set main frame properties and add above initialized components
        this.setTitle("Search For Drivers");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600,500);
        this.setIconImage(icon.getImage());
        this.setResizable(false);
        this.add(topLabel);
        this.add(buttonHome);
        this.add(searchButton);
        this.add(searchField);
        this.add(panel);
        Container pane = this.getContentPane();
        pane.setBackground(new Color(0,0,50));
        this.setLayout(null);
        this.setVisible(true);
        centeredFrame(this);
    }

    public void centeredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int cordX = (objDimension.width - objFrame.getWidth()) / 2;
        int cordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(cordX, cordY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // validate back/home button
        if (e.getSource()==buttonHome){
            this.dispose();
            MyFrame frame = new MyFrame();
        }
        else if (e.getSource()==searchButton){
            // calling method to validate search button functionality
            findDriver();
        }
    }

    // this methods find searched driver and set view to textarea
    // handles search button functionality
    public void findDriver(){

        // load program data
        championshipManager.loadData("championshipData.ser");

        // retrieve search name
        String searchedName = searchField.getText();

        StringBuilder output = new StringBuilder();
        output.append("     ---------------------------  ").append(searchedName.toUpperCase()).append("  --------------------------\n");

        // search for all the race driver participated
        int x = 0;
        for (Race race: championshipManager.getRaces()){
            for (int i = 0; i < race.participatedDrivers.size(); i++){
                if (searchedName.equalsIgnoreCase(race.getParticipatedDrivers().get(i).getDriverName())){
                    output.append("Date        : ").append(race.getRaceDate().toString()).append("                    \n");
                    output.append("Position : ").append(race.getPositions().get(i)).append("                    \n");
                    output.append("-----------------------------------------------------------------------------\n");
                    x++;
                }
            }
        }
        // if driver not fount/have not participated yet
        if (x==0){
            textArea.setText("Driver Did Not Find/Not Participated In Any Race!");
        }
        // set details to textarea
        else {
            textArea.setText(output.toString());
        }
    }
}
