package View;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Controller.Controller;
import engine.Game;
import engine.Player;

public class StartView extends JFrame  implements ActionListener {
	
	Game game;
	JButton submit;
	JTextField first;
	JTextField second;
	
	public StartView() {
		
		 this.setTitle("Marvel");
     	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
     	  this.setResizable(true);
     	  this.setBounds(0,0,1960,1050);
     	  this.setExtendedState(MAXIMIZED_BOTH);
     	  this.setVisible(true);
     	 this.setLayout(null);
     	  ImageIcon ig2 = new ImageIcon("Marvel.png");
     	  this.setIconImage(ig2.getImage());
     	  this.setBackground(new Color(153,0,0));
     	  
     	 ImageIcon ig =new ImageIcon("b709aa1e13018790af47a838446bf728.jpg"); 
     	
     	 JLabel  Startphoto =new JLabel(ig);
     	 Startphoto.setBounds(0,0,1960,1050);
     	 Startphoto.setLayout(null);
		  this.setContentPane(Startphoto);
		 
    	
     	  
     	 first= new JTextField();
     	 first.setBounds(50, 50, 200, 50);
     	 first.setFont(new Font("Arial",Font.PLAIN,16));
     	 JLabel l1= new JLabel("Enter First Player Name");
     	 l1.setBounds(first.getBounds().x, first.getBounds().y-20, first.getWidth(),20);
     	  
     	second= new JTextField();
     	second.setBounds(50, 200, 200, 50);
     	second.setFont(new Font("Arial",Font.PLAIN,16));
    	 JLabel l2= new JLabel("Enter Second Player Name");
    	 l2.setBounds(second.getBounds().x, second.getBounds().y-20, second.getWidth(),20);
		
		
		// String P1name =JOptionPane.showInputDialog("Enter First Player Name:");
		// String P2name = JOptionPane.showInputDialog("Enter Second Player Name:");
		 
		
		//System.out.print(first.getText());
		
		
		
		
		
		
		
		submit = new JButton();
		submit.setText("Snap");
		submit.setBounds(820,800,200,50);
		submit.addActionListener(this);
        this.add(submit);

		this.add(first);
		this.add(second);
		this.add(l1);
		this.add(l2); 
        
        
        
   	 
    	 
        
        
     	
        
     	 this.revalidate();
     	 this.repaint();
       
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource()==submit) {
			if (first.getText().length()==0||second.getText().length()==0)
				JOptionPane.showMessageDialog(this,"Please enter Player name","Error",JOptionPane.ERROR_MESSAGE);
			else {	
			Player one = new Player(first.getText());
			Player two = new Player(second.getText());
			game = new Game (one,two);
		   this.setVisible(false);
			new Controller(game);
		   
		}
		
	}
	}

}
