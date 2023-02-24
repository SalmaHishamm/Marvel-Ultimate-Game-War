package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import Controller.Controller;
import engine.Game;

public class View extends JFrame {
	
	 public JLabel Player1; //name of first player
	 public JLabel Player2;  //name of second player
	 public JTextArea Leader1; //used or not
	 public JTextArea Leader2; //used or not
	 public JTextArea RemanChamp1;
	 public JTextArea RemanChamp2;
	 public JTextArea LabelTurnOrder;
	 public JTextArea CurrentChampion;
	 public JTextArea PlayerName1;
	 public JTextArea PlayerName2;
	 public JTextArea PlayerDetails1;
	 public JTextArea PlayerDetails2;		
	 
	 
	 public JScrollPane CurrentChampion1;
	 
	 public JPanel paneStart; //first panel of choosing
	 public JPanel paneBoard1; // board in game
	 public JPanel paneBoard2; // combobox in game
	 public JPanel paneBoardTurnOrder;  
	 public JPanel paneBoardPlayer1;
	 public JPanel paneBoardPlayer2;
	 public JPanel paneMethods;
	 public JPanel paneRight;
	 public JPanel paneLeft;
	 public JPanel paneCombo;
	 public JPanel paneFirstwaSecond;
     public JPanel StartRight;
     public JPanel StartRightUp;
     public JPanel StartRightDown;
     
     
	 public JButton CastAbility1;
	 public JButton CastAbility2;
	 public JButton CastAbility3;
	 public JButton UseLeader;
	 
	 public JScrollPane scroll2;
	 
	 public JTextArea textStart2;
	 public JTextArea textStart ;  //text of leader choice
	 public String[] ArrayMethods; 
	 public JComboBox combo;
	 public JButton [][] BoardList; 
	 public  ArrayList<JButton> buttonsStart= new ArrayList<JButton>(); 
    public JLabel Startphoto;
    public JPanel CurrentChampPane;
    public JTextArea CurrentChampText;
    
	 
	 public View(){
		 
	
	  this.setTitle("Marvel");
	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	  this.setResizable(true);
	  this.setBounds(0,0,1960,1050);
	  this.setExtendedState(MAXIMIZED_BOTH);
	  this.setVisible(true);
	  this.setLayout(new BorderLayout());
	 
	  
	  BoardList = new JButton[5][5];
	  
	  paneStart = new JPanel();
	  paneStart.setLayout(new GridLayout(0,5));
	  paneStart.setPreferredSize(new Dimension(1500,1050));
	 
	 
	
	  StartRight=new JPanel();
	  StartRight.setPreferredSize(new Dimension(420,1050));
	  StartRight.setLayout(new BorderLayout());
	  
	  
	  textStart = new JTextArea("Your first chosen champion"+"\n"+ " is your leader");
	  textStart.setPreferredSize(new Dimension(300 ,100));
	  textStart.setEditable(false);
	  textStart.setFont(new Font("MONOSPACE",Font.BOLD,20));	
	  
	  
	  textStart2=new JTextArea("First Player Choosing Now");
	  textStart2.setPreferredSize(new Dimension(300 ,100));
	  textStart2.setEditable(false);
	  textStart2.setFont(new Font("MONOSPACE",Font.BOLD,20));	
	  
	  StartRightUp=new JPanel();
	  StartRightUp.setPreferredSize(new Dimension(300,200));
	  StartRightUp.setLayout(new BorderLayout());
	  
	  StartRightUp.add(textStart,BorderLayout.NORTH);
	  StartRightUp.add(textStart2,BorderLayout.SOUTH);
	  
	  StartRightDown= new JPanel();
	  StartRightDown.setPreferredSize(new Dimension(300,800));
	  StartRightDown.setLayout(new BorderLayout());
	  
	  StartRight.add(StartRightUp,BorderLayout.NORTH);
	  StartRight.add(StartRightDown,BorderLayout.SOUTH);
	 
	  
	  
	  
	  
	  
	 
      
      
     
	  
	  
      this.add(StartRight,BorderLayout.EAST);	
      this.add(paneStart ,BorderLayout.WEST);
     
      
      
	  
     // paneBoard1.setBounds(200,15, 80, this.getHeight());
     
	//  paneBoard1.setAlignmentX(JComponent.CENTER_ALIGNMENT);
	  
	 
      /*int k=0;
      int m=0;
      int i=0;
      for ( i=4 ; i>=0 ;i--){
    	  for (int z=0;z>5;z++ ){
			 
		  JButton temp = new JButton();
		  
		  temp.setBackground(Color.WHITE);
		  paneBoard1.add(temp);
		  
		  BoardList[k][m]=temp;
		  m++;
		  if (m>=5)
			  k++;
		  if (m>=5)
			  m=0;
		  if (k>=5)
			  break;
			  	  
	  }}*/
   //   System.out.print(i);
      

      paneBoard1=new JPanel();
      paneBoard1.setLayout(new GridLayout(0,5));
      paneBoard1.setBackground(Color.WHITE);
	  paneBoard1.setPreferredSize(new Dimension(980,1050));
	//  this.add(paneBoard1,BorderLayout.CENTER);
      
      
      paneRight = new JPanel();
      paneRight.setLayout(new BorderLayout());
      paneRight.setBackground(Color.RED);
      paneRight.setPreferredSize(new Dimension(300,1050));
    //  this.add(paneRight, BorderLayout.EAST);
      
      
      paneLeft = new JPanel();
      paneLeft.setLayout(new BorderLayout());
      paneLeft.setBackground(Color.BLUE);
      paneLeft.setPreferredSize(new Dimension(680,1050));
   //   this.add(paneLeft, BorderLayout.WEST);
	  
	  
	  
	  
	  
	  
      paneBoard2 = new JPanel();
      paneBoard2.setPreferredSize(new Dimension(50,50));
     // paneBoard2.setLayout(BorderLayout.EAST);
      ArrayMethods = new String[10];
      ArrayMethods[0]="Select One";
      ArrayMethods[1]="Move Up";
      ArrayMethods[2]= "Move Down";
      ArrayMethods[3]= "Move Left";
      ArrayMethods[4]= "Move Right";
      ArrayMethods[5]= "Attack Up";
      ArrayMethods[6]= "Attack Down";
      ArrayMethods[7]= "Attack Right";
      ArrayMethods[8]= "Attack Left";
      ArrayMethods[9]="End Turn";
    //  ArrayMethods[8]= "Cast Ability Up";
     // ArrayMethods[9]= "Cast Ability Down";
     // ArrayMethods[10]="Cast Ability Left";
    //  ArrayMethods[11]="Cast Ability Right";
    //  ArrayMethods[12]= "Use Leader Ability" ;
     
    //  ArrayMethods[14]="Cast Ability SingleTarget";
    //  ArrayMethods[15]="Cast Ability ";
      paneBoard2.setLayout(new BorderLayout());
      paneBoard2.setPreferredSize(new Dimension(490,500));
      
      
      combo = new JComboBox(ArrayMethods);
      combo.setPreferredSize(new Dimension(300,500));
   //   combo.setBounds(0, 0, 500, 500);
   //   paneBoard2.add(combo ,BorderLayout.WEST);
      
    	
      
      paneBoardTurnOrder= new JPanel();
      paneBoardTurnOrder.setLayout(new BorderLayout());
      paneBoardTurnOrder.setPreferredSize(new Dimension(300,400));
      
     // paneBoardTurnOrder.setBounds(1500, 100, 500, 500);
     // paneBoardTurnOrder.add(LabelTurnOrder);
      
      LabelTurnOrder =new JTextArea();
      LabelTurnOrder.setEditable(false);
      LabelTurnOrder.setPreferredSize(new Dimension(490,300));
      LabelTurnOrder.setBackground(Color.YELLOW);
      LabelTurnOrder.setFont(new Font("",Font.PLAIN,40));
      paneBoardTurnOrder.add(LabelTurnOrder);
      
      
      paneCombo= new JPanel();
      paneCombo.setLayout(new BorderLayout());
      paneCombo.setPreferredSize(new Dimension(490,300));
      paneCombo.add(combo);
      
      
      paneMethods = new JPanel();
      paneMethods.setLayout(new BorderLayout());
      paneMethods.setPreferredSize(new Dimension(300,450));
      
      CastAbility1 = new JButton();
      CastAbility2 = new JButton();
      CastAbility3 = new JButton();
      UseLeader = new JButton();
      UseLeader.setText("Use Leader");
      UseLeader.setEnabled(false);
      this.revalidate();
	  this.repaint();
	  
      
    /*  paneMethods.add(CastAbility1,BorderLayout.NORTH);
      paneMethods.add(CastAbility2,BorderLayout.SOUTH);
      paneMethods.add(CastAbility3,BorderLayout.CENTER);
      paneMethods.add(UseLeader,BorderLayout.WEST);
      */
      
      
      CurrentChampion = new JTextArea();
      CurrentChampion.setEditable(false);
      CurrentChampion1 = new JScrollPane();
      CurrentChampion.add(CurrentChampion1);
     // paneMethods.add(CurrentChampion,BorderLayout.EAST);
      
      
      paneBoardPlayer1= new JPanel();
      paneBoardPlayer1.setBackground(Color.PINK);
      paneBoardPlayer1.setLayout(new BorderLayout());
      paneBoardPlayer1.setPreferredSize(new Dimension(320,200));
      PlayerName1 = new JTextArea();
      PlayerDetails1 = new JTextArea();
      
      paneBoardPlayer1.add(PlayerName1 ,BorderLayout.NORTH);
      paneBoardPlayer1.add(PlayerDetails1 , BorderLayout.CENTER);
      
      
      
      
      paneBoardPlayer2= new JPanel();
      paneBoardPlayer2.setBackground(Color.GREEN);
      paneBoardPlayer2.setLayout(new BorderLayout());
      paneBoardPlayer2.setPreferredSize(new Dimension(320,200));
      PlayerName2 = new JTextArea();
      PlayerDetails2 = new JTextArea();
      
      paneBoardPlayer2.add(PlayerName2 ,BorderLayout.NORTH);
      paneBoardPlayer2.add(PlayerDetails2 , BorderLayout.CENTER);
      
      
      PlayerName1.setEditable(false);
      PlayerDetails1.setEditable(false);
      PlayerName2.setEditable(false);
      PlayerDetails2.setEditable(false);
      
      PlayerName1.setFont(new Font("MONOSPACE",Font.PLAIN,25));
      PlayerName2.setFont(new Font("MONOSPACE",Font.PLAIN,25));
      PlayerDetails1.setFont(new Font("MONOSPACE",Font.PLAIN,25));
      PlayerDetails2.setFont(new Font("MONOSPACE",Font.PLAIN,25));
      
      
      paneFirstwaSecond = new JPanel();
      paneFirstwaSecond.setBackground(Color.RED);
      paneFirstwaSecond.setPreferredSize(new Dimension(680,220));
      paneFirstwaSecond.add(paneBoardPlayer1, BorderLayout.EAST);
      paneFirstwaSecond.add(paneBoardPlayer2,BorderLayout.WEST);
      
      
      
      
    	
      CurrentChampPane=new JPanel();
      CurrentChampPane.setPreferredSize(new Dimension(600,760));
      CurrentChampPane.setBackground(Color.BLUE);
      CurrentChampText= new JTextArea();
    //  CurrentChampText.setPreferredSize(new Dimension(600,500));
      CurrentChampText.setEditable(false);
      CurrentChampText.setFont(new Font("MONOSPACE",Font.PLAIN,20));
    		 
     // CurrentChampPane.add(CurrentChampText,BorderLayout.SOUTH);
      
      
      Leader1 = new JTextArea();
      Leader1.setEditable(false);
      Leader1.setFont(new Font("MONOSPACE",Font.PLAIN,25));
      paneBoardPlayer1.add(Leader1,BorderLayout.SOUTH);
      
      
      Leader2 = new JTextArea();
      Leader2.setEditable(false);
      Leader2.setFont(new Font("MONOSPACE",Font.PLAIN,25));
      paneBoardPlayer2.add(Leader2,BorderLayout.SOUTH);
      
    /*  
     paneBoardPlayer1 = new JPanel();
     paneBoardPlayer2 = new JPanel();
     RemanChamp1 = new JTextArea();
     RemanChamp2 = new JTextArea();
     Leader1= new JTextArea ();
     Leader2=new JTextArea();
     
     paneBoardPlayer1.setBounds(1450,850,500,500);
     paneBoardPlayer2.setBounds(1450,600,500,500);
     
     RemanChamp1.setBackground(Color.YELLOW);
     RemanChamp1.setFont(new Font("MV Boli",Font.PLAIN,20));
     RemanChamp2.setBackground(Color.YELLOW);
     RemanChamp2.setFont(new Font("MV Boli",Font.PLAIN,20));
     Leader1.setBackground(Color.YELLOW);
     Leader1.setFont(new Font("MV Boli",Font.PLAIN,20));
     Leader2.setBackground(Color.YELLOW);
     Leader2.setFont(new Font("MV Boli",Font.PLAIN,20));
     
     RemanChamp1.setEditable(false);
     RemanChamp2.setEditable(false);
     Leader1.setEditable(false);
     Leader2.setEditable(false);
     
     
    RemanChamp1.setBounds(400, 900, 5000, 3000);
   // RemanChamp1.setSize(100, 100);
    RemanChamp2.setBounds(900, 1000, 5000, 3000);
   
     
     paneBoardPlayer1.add(RemanChamp1);
     paneBoardPlayer1.add(Leader1);
     paneBoardPlayer2.add(RemanChamp2);
     paneBoardPlayer2.add(Leader2);
     */
     
     
   
      
      this.revalidate();
	  this.repaint();
	  
       
       
	 }

		

}
