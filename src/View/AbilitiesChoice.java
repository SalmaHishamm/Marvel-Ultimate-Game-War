package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import engine.Game;

public class AbilitiesChoice extends JFrame implements ActionListener{
	Game game;
	JButton ok;
	
	public AbilitiesChoice (Game game,String s) {
		this.game=game;
		
		 this.setTitle("Marvel");
    	  this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    	  this.setResizable(true);
    	  this.setBounds(0,0,700,700);
    	
    	  this.setVisible(true);
    	  this.setLayout(new BorderLayout());
    	  this.setBackground(new Color(153,0,0));
    	  
    	  
    	  JTextArea abinfo = new JTextArea();
    	  abinfo.setPreferredSize(new Dimension(700,500));
    	  
    	  
    	  for (int i=0;i<game.getAvailableChampions().size();i++) {
    		  if (game.getAvailableChampions().get(i).getName().equals(s))
    			  abinfo.setText(game.getAvailableChampions().get(i).toStringAbilities());
    	  }
    	  
			/*
			 * ok = new JButton(); ok.addActionListener(this); ok.setPreferredSize(new
			 * Dimension(100,100));
			 */
    	  
    	
    //	this.add(ok,BorderLayout.SOUTH);
    	this.add(abinfo,BorderLayout.NORTH);
    	
    	
    	this.revalidate();
    	this.repaint();
	}

	
	
	
	
	public void actionPerformed(ActionEvent e) {
	/*	if (e.getSource()==ok)
			this.dispose();
		
		
	}*/
	

}}
