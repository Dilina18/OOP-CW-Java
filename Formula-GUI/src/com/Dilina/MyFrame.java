package com.Dilina;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;


public class MyFrame extends JFrame implements ActionListener, Serializable {


    private final JButton buttonManageDrivers;
    private final JButton buttonFormulaTable;
    private final JButton buttonGetRace;
    private final JButton buttonSetRace;
    private final JButton buttonViewRaces;
    private final JButton buttonSearchDriver;

    MyFrame(){
        Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
        championshipManager.loadData("championshipData.ser"); // load program data

        // initialize and load all button icons
        ImageIcon icon0 = new ImageIcon("button0.png");
        ImageIcon icon1 = new ImageIcon("button1.png");
        ImageIcon icon2 = new ImageIcon("button2.png");
        ImageIcon icon3 = new ImageIcon("button3.png");
        ImageIcon icon4 = new ImageIcon("button4.png");
        ImageIcon icon5 = new ImageIcon("button5.png");

        // initialize and modify manage driver button
        buttonManageDrivers = new JButton("Manage Formula Drivers");
        buttonManageDrivers.setBounds(125,95,350,60);
        buttonManageDrivers.addActionListener(this);
        buttonManageDrivers.setFocusable(false);
        buttonManageDrivers.setIcon(icon0);
        buttonManageDrivers.setHorizontalTextPosition(JButton.RIGHT);
        buttonManageDrivers.setVerticalTextPosition(JButton.CENTER);
        buttonManageDrivers.setFont(new Font("Comic Sans",Font.BOLD,20));
        buttonManageDrivers.setIconTextGap(15);
        buttonManageDrivers.setForeground(new Color(241,196,15));
        buttonManageDrivers.setBorder(BorderFactory.createLineBorder(new Color(255,195,0)));
        buttonManageDrivers.setContentAreaFilled(false);

        // initialize and modify view formula table button
        buttonFormulaTable = new JButton("View Formula Table");
        buttonFormulaTable.setBounds(125,165,350,60);
        buttonFormulaTable.addActionListener(this);
        buttonFormulaTable.setFocusable(false);
        buttonFormulaTable.setIcon(icon1);
        buttonFormulaTable.setHorizontalTextPosition(JButton.RIGHT);
        buttonFormulaTable.setVerticalTextPosition(JButton.CENTER);
        buttonFormulaTable.setFont(new Font("Comic Sans",Font.BOLD,20));
        buttonFormulaTable.setIconTextGap(25);
        buttonFormulaTable.setForeground(new Color(155, 89, 182));
        buttonFormulaTable.setBorder(BorderFactory.createLineBorder(new Color(155, 89, 182)));
        buttonFormulaTable.setContentAreaFilled(false);

        // initialize and modify generate random race button
        buttonGetRace = new JButton("Get Formula Race");
        buttonGetRace.setBounds(125,235,350,60);
        buttonGetRace.addActionListener(this);
        buttonGetRace.setFocusable(false);
        buttonGetRace.setIcon(icon2);
        buttonGetRace.setHorizontalTextPosition(JButton.RIGHT);
        buttonGetRace.setVerticalTextPosition(JButton.CENTER);
        buttonGetRace.setFont(new Font("Comic Sans",Font.BOLD,20));
        buttonGetRace.setIconTextGap(25);
        buttonGetRace.setForeground(Color.cyan);
        buttonGetRace.setBorder(BorderFactory.createLineBorder(Color.cyan));
        buttonGetRace.setContentAreaFilled(false);

        // initialize and modify generate probabilistic random race button
        buttonSetRace = new JButton("Set Formula Race");
        buttonSetRace.setBounds(125,305,350,60);
        buttonSetRace.addActionListener(this);
        buttonSetRace.setFocusable(false);
        buttonSetRace.setIcon(icon3);
        buttonSetRace.setHorizontalTextPosition(JButton.RIGHT);
        buttonSetRace.setVerticalTextPosition(JButton.CENTER);
        buttonSetRace.setFont(new Font("Comic Sans",Font.BOLD,20));
        buttonSetRace.setIconTextGap(25);
        buttonSetRace.setForeground(Color.RED);
        buttonSetRace.setBorder(BorderFactory.createLineBorder(Color.RED));
        buttonSetRace.setContentAreaFilled(false);

        // initialize and modify view all races button
        buttonViewRaces = new JButton("View Formula Races");
        buttonViewRaces.setBounds(125,375,350,60);
        buttonViewRaces.addActionListener(this);
        buttonViewRaces.setFocusable(false);
        buttonViewRaces.setIcon(icon4);
        buttonViewRaces.setHorizontalTextPosition(JButton.RIGHT);
        buttonViewRaces.setVerticalTextPosition(JButton.CENTER);
        buttonViewRaces.setFont(new Font("Comic Sans",Font.BOLD,20));
        buttonViewRaces.setIconTextGap(25);
        buttonViewRaces.setForeground(Color.WHITE);
        buttonViewRaces.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        buttonViewRaces.setContentAreaFilled(false);

        // initialize and modify search for driver button
        buttonSearchDriver = new JButton("Search For Driver");
        buttonSearchDriver.setBounds(125,445,350,60);
        buttonSearchDriver.addActionListener(this);
        buttonSearchDriver.setFocusable(false);
        buttonSearchDriver.setIcon(icon5);
        buttonSearchDriver.setHorizontalTextPosition(JButton.RIGHT);
        buttonSearchDriver.setVerticalTextPosition(JButton.CENTER);
        buttonSearchDriver.setFont(new Font("Comic Sans",Font.BOLD,20));
        buttonSearchDriver.setIconTextGap(25);
        buttonSearchDriver.setForeground(new Color(46, 204, 113));
        buttonSearchDriver.setBorder(BorderFactory.createLineBorder(new Color(46, 204, 113)));
        buttonSearchDriver.setContentAreaFilled(false);

        // initialize and modify frame header
        JLabel headLabel = new JLabel("WELCOME TO FORMULA1 CHAMPIONSHIP MANAGER");
        headLabel.setBackground(Color.cyan);
        headLabel.setBounds(50,20,500,60);
        headLabel.setHorizontalAlignment(JLabel.CENTER);
        headLabel.setVerticalAlignment(JLabel.CENTER);
        headLabel.setFont(new Font("Comic Sans",Font.BOLD,17));
        headLabel.setForeground(Color.cyan);
        headLabel.setBorder(BorderFactory.createEtchedBorder());

        // initialize and load frame icon
        ImageIcon icon = new ImageIcon("frameIcon.png");

        // initialize and set frame background image
        ImageIcon background=new ImageIcon("main.jpg");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(600,600,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel back=new JLabel(background);
        back.setLayout(null);
        back.setBounds(0,0,600,600);

        // set main frame properties and add above initialized components
        this.setIconImage(icon.getImage());
        this.setTitle("Formula1 Championship Manager");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container pane = this.getContentPane();
        pane.setBackground(new Color(0,0,50));
        this.setSize(600,600);
        this.setResizable(false);
        this.setLayout(null);
        this.setVisible(true);
        this.add(headLabel);
        this.add(buttonManageDrivers);
        this.add(buttonFormulaTable);
        this.add(buttonGetRace);
        this.add(buttonSetRace);
        this.add(buttonViewRaces);
        this.add(buttonSearchDriver);
        this.add(back);
        centeredFrame(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // validating buttons to get frame for selected options

        if (e.getSource() == buttonManageDrivers){
            this.dispose();
            ManageDriversFrame driversFrame = new ManageDriversFrame();
        }
        if (e.getSource() == buttonFormulaTable){
            this.dispose();
            FormulaTableFrame tableFrame = new FormulaTableFrame();
        }
        else if (e.getSource() == buttonGetRace){
            this.dispose();
            GetRacesFrame getRacesFrame = new GetRacesFrame();
        }
        else if (e.getSource() == buttonSetRace){
            this.dispose();
            SetRacesFrame setRacesFrame = new SetRacesFrame();
        }
        else if (e.getSource() == buttonViewRaces){
            this.dispose();
            ViewRacesFrame viewRacesFrame = new ViewRacesFrame();
        }
        else if (e.getSource() == buttonSearchDriver){
            this.dispose();
            SearchDriverFrame searchDriverFrame = new SearchDriverFrame();
        }

    }
    public void centeredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int cordX = (objDimension.width - objFrame.getWidth()) / 2;
        int cordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(cordX, cordY);
    }


}
