package com.Dilina;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FormulaTableFrame extends JFrame implements ActionListener, Serializable {

    Formula1ChampionshipManager championshipManager = new Formula1ChampionshipManager();
    private final JLabel panelLabel;
    private final JPanel panel;
    private  JTable table;
    private final JButton buttonPointsAsc,buttonPointsDes,buttonPlaceAsc,buttonPlaceDes;
    private final JButton buttonHome;

    FormulaTableFrame(){

        // deserialize/load program data
        championshipManager.loadData("championshipData.ser");

        // create border for buttons
        Border buttonBorders = BorderFactory.createLineBorder(Color.cyan);

        // initialize and modify points ascending button
        buttonPointsAsc = new JButton("Total Points (Ascending)");
        buttonPointsAsc.setBounds(100,190,170,30);
        buttonPointsAsc.addActionListener(this);
        buttonPointsAsc.setFocusable(false);
        buttonPointsAsc.setHorizontalTextPosition(JButton.RIGHT);
        buttonPointsAsc.setVerticalTextPosition(JButton.CENTER);
        buttonPointsAsc.setFont(new Font("Comic Sans",Font.BOLD,12));
        buttonPointsAsc.setIconTextGap(25);
        buttonPointsAsc.setForeground(Color.WHITE);
        buttonPointsAsc.setBackground(new Color(0,0,50));
        buttonPointsAsc.setBorder(buttonBorders);

        // initialize and modify points descending button
        buttonPointsDes = new JButton("Total Points (Descending)");
        buttonPointsDes.setBounds(310,190,170,30);
        buttonPointsDes.addActionListener(this);
        buttonPointsDes.setFocusable(false);
        buttonPointsDes.setHorizontalTextPosition(JButton.RIGHT);
        buttonPointsDes.setVerticalTextPosition(JButton.CENTER);
        buttonPointsDes.setFont(new Font("Comic Sans",Font.BOLD,12));
        buttonPointsDes.setIconTextGap(25);
        buttonPointsDes.setForeground(Color.WHITE);
        buttonPointsDes.setBackground(new Color(0,0,50));
        buttonPointsDes.setBorder(buttonBorders);

        // initialize and modify total wins ascending button
        buttonPlaceAsc = new JButton("Total Wins (Ascending)");
        buttonPlaceAsc.setBounds(520,190,170,30);
        buttonPlaceAsc.addActionListener(this);
        buttonPlaceAsc.setFocusable(false);
        buttonPlaceAsc.setHorizontalTextPosition(JButton.RIGHT);
        buttonPlaceAsc.setVerticalTextPosition(JButton.CENTER);
        buttonPlaceAsc.setFont(new Font("Comic Sans",Font.BOLD,12));
        buttonPlaceAsc.setIconTextGap(25);
        buttonPlaceAsc.setForeground(Color.WHITE);
        buttonPlaceAsc.setBackground(new Color(0,0,50));
        buttonPlaceAsc.setBorder(buttonBorders);

        // initialize and modify total wins descending button
        buttonPlaceDes = new JButton("Total Wins (Descending)");
        buttonPlaceDes.setBounds(730,190,170,30);
        buttonPlaceDes.addActionListener(this);
        buttonPlaceDes.setFocusable(false);
        buttonPlaceDes.setHorizontalTextPosition(JButton.RIGHT);
        buttonPlaceDes.setVerticalTextPosition(JButton.CENTER);
        buttonPlaceDes.setFont(new Font("Comic Sans",Font.BOLD,12));
        buttonPlaceDes.setIconTextGap(25);
        buttonPlaceDes.setForeground(Color.WHITE);
        buttonPlaceDes.setBackground(new Color(0,0,50));
        buttonPlaceDes.setBorder(buttonBorders);

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

        // initialize and modify Frame heading label
        JLabel topLabel = new JLabel("FORMULA1 CHAMPIONSHIP POINTS TABLE");
        topLabel.setBounds(150,60,700,50);
        topLabel.setForeground(Color.white);
        topLabel.setHorizontalAlignment(JLabel.CENTER);
        topLabel.setVerticalAlignment(JLabel.CENTER);
        topLabel.setFont(new Font("Comic Sans",Font.BOLD,32));
        topLabel.setBorder(BorderFactory.createEtchedBorder());

        // initialize and setting up frame background image
        ImageIcon background=new ImageIcon("back.jpg");
        Image img=background.getImage();
        Image temp=img.getScaledInstance(1000,700,Image.SCALE_SMOOTH);
        background=new ImageIcon(temp);
        JLabel back=new JLabel(background); // this label contains all the frame components.
        back.setLayout(null);
        back.setBounds(0,0,1000,700);
        back.add(buttonPointsAsc);
        back.add(buttonPointsDes);
        back.add(buttonPlaceAsc);
        back.add(buttonPlaceDes);
        back.add(buttonHome);
        back.add(topLabel);

        // the label displayed when competition is empty
        panelLabel = new JLabel("NO RACES ENTERTAINED TO SHOW DETAILS!");
        panelLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        panelLabel.setHorizontalAlignment(JLabel.CENTER);
        panelLabel.setFont(new Font("Calibre",Font.BOLD,25));
        panelLabel.setBounds(50,30,700,160);
        panelLabel.setOpaque(true);
        panelLabel.setBackground(new Color(255,195,0));

        // this panel contains above label
        panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.lightGray);
        panel.add(panelLabel);
        panel.setBounds(100,240,800,200);
        panel.setBorder(BorderFactory.createLineBorder(new Color(255, 195, 0)));

        // initialize and modify formula table
        table = setTableDetails(championshipManager.getFormula1Drivers()); // calling method to load program data to table
        table.setEnabled(false);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setEnabled(false);
        table.getTableHeader().setBackground(Color.RED);
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Calibre",Font.BOLD,13));
        setTableHeight(championshipManager.getFormula1Drivers().size());

        // initialize and set table to scrollable pane
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane);

        // initialize and load frame top icon
        ImageIcon icon = new ImageIcon("frameIcon.png");

        // set main frame properties and add above initialized components
        this.setTitle("Formula1 Championship Points Table");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000,700);
        this.setIconImage(icon.getImage());
        this.setResizable(true);
        this.setLayout(null);
        this.add(panel);
        this.add(back);
        this.setVisible(true);
        centeredFrame(this); // calling method to center window on screen
    }

    // method center window on screen
    public void centeredFrame(javax.swing.JFrame objFrame){
        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int cordX = (objDimension.width - objFrame.getWidth()) / 2;
        int cordY = (objDimension.height - objFrame.getHeight()) / 2;
        objFrame.setLocation(cordX, cordY);
    }

    // this method returns an updated formula table
    public JTable setTableDetails(List<Formula1Driver> drivers){

        // load program data
        championshipManager.loadData("championshipData.ser");

        // set table headers and details
        String[][] data = new String[championshipManager.getFormula1Drivers().size()][10];
        String[] columnNames = { "Position", "Name", "Team", "Location", "Races", "Wins", "2nd", "3rd",
                "Above", "Points"  };
        // if there are no drivers
        if (drivers.isEmpty()){
            panelLabel.setVisible(true); // show no drivers label
            buttonPointsAsc.setEnabled(false);
            buttonPointsDes.setEnabled(false);
            buttonPlaceAsc.setEnabled(false);
            buttonPlaceDes.setEnabled(false);
        }
        // if there are drivers
        else {
            int i = 0;
            // hide no drivers table
            panelLabel.setVisible(false);
            // update table data and return
            for (Formula1Driver driver: drivers){
                String [] rows = {String.valueOf(i+1),driver.getDriverName(),driver.getDriverManufactureTeam(),
                        driver.getDriverLocation(),String.valueOf(driver.getDriverTotalRaces()),
                        String.valueOf(driver.getDriverFirstPlaces()),String.valueOf(driver.getDriverSecondPlaces()),
                        String.valueOf(driver.getDriverThirdPlaces()),String.valueOf(driver.getOtherPlaces().totalOtherPlaces()),
                        String.valueOf(driver.getDriverTotalPoints())};
                data[i] = rows;
                i++;
            }
        }
        return table = new JTable(data,columnNames);
    }
    // method to set table bounds when updating data
    public void setTableHeight(int num){
        championshipManager.saveData("championshipData.ser");
        if (!championshipManager.getFormula1Drivers().isEmpty()){
            int height = 17*num + 24;
            panel.setBounds(100,240,800,height);
        }
    }

    // this class is implements ActionListener interface
    // override method to handle button events
    @Override
    public void actionPerformed(ActionEvent e) {
        TableRowSorter<TableModel> sorter;

        // button functionalities to sorting formula table

        // total points ascending
        if (e.getSource()==buttonPointsAsc){
            sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(9, SortOrder.ASCENDING));
            sortKeys.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);

            buttonPointsAsc.setEnabled(false);
            buttonPointsDes.setEnabled(true);
            buttonPlaceAsc.setEnabled(true);
            buttonPlaceDes.setEnabled(true);

        }
        // total points descending
        else if (e.getSource()==buttonPointsDes){
            sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(9, SortOrder.DESCENDING));
            sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
            sorter.setSortKeys(sortKeys);

            buttonPointsAsc.setEnabled(true);
            buttonPointsDes.setEnabled(false);
            buttonPlaceAsc.setEnabled(true);
            buttonPlaceDes.setEnabled(true);
        }

        // total wins ascending
        else if (e.getSource()==buttonPlaceAsc){
            sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(5, SortOrder.ASCENDING));
            sortKeys.add(new RowSorter.SortKey(9, SortOrder.ASCENDING));
            sorter.setSortKeys(sortKeys);

            buttonPointsAsc.setEnabled(true);
            buttonPointsDes.setEnabled(true);
            buttonPlaceAsc.setEnabled(false);
            buttonPlaceDes.setEnabled(true);
        }

        // total wins descending
        else if (e.getSource()==buttonPlaceDes){
            sorter = new TableRowSorter<>(table.getModel());
            table.setRowSorter(sorter);
            List<RowSorter.SortKey> sortKeys = new ArrayList<>();
            sortKeys.add(new RowSorter.SortKey(5, SortOrder.DESCENDING));
            sortKeys.add(new RowSorter.SortKey(9, SortOrder.DESCENDING));
            sorter.setSortKeys(sortKeys);

            buttonPointsAsc.setEnabled(true);
            buttonPointsDes.setEnabled(true);
            buttonPlaceAsc.setEnabled(true);
            buttonPlaceDes.setEnabled(false);
        }
        // validating home button
        else if (e.getSource()==buttonHome){
            this.dispose(); // destroy current window
            MyFrame frame = new MyFrame(); // load home window
        }
    }
}
