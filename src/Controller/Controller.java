package Controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.RootPaneContainer;
import javax.swing.border.LineBorder;

import model.abilities.AreaOfEffect;
import model.world.Champion;
import model.world.Cover;
import model.world.Direction;
import View.AbilitiesChoice;
import View.StartView;
import View.View;
import engine.Game;
import engine.Player;
import engine.PriorityQueue;
import exceptions.AbilityUseException;
import exceptions.ChampionDisarmedException;
import exceptions.InvalidTargetException;
import exceptions.LeaderAbilityAlreadyUsedException;
import exceptions.LeaderNotCurrentException;
import exceptions.NotEnoughResourcesException;
import exceptions.UnallowedMovementException;


public class Controller implements ActionListener {
	 Game game;
	 View view;
	 double height;
	 double width;
	 StartView SV;
     ArrayList<JButton> buttons; 
     ArrayList<JButton> ChampsAndCovers; 
     int i;
     JComboBox combo2;
     JComboBox combo3;
   //  AbilitiesChoice ab;
     JTextArea ab;
 

	public Controller (Game game){
		i=0;	
		
	    buttons = new ArrayList<>();
	    ChampsAndCovers = new ArrayList<>();
	    
		/*
		 * String P1name =JOptionPane.showInputDialog("Enter First Player Name:");
		 * String P2name = JOptionPane.showInputDialog("Enter Second Player Name:");
		 * Player one = new Player(P1name); Player two = new Player(P2name); game = new
		 * Game (one,two);
		 */

	    
	    this.game=game;
			try {
				Game.loadAbilities("Abilities.csv");
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			try {
				Game.loadChampions("Champions.csv");

			} catch (IOException e) {
				
				e.printStackTrace();
			}
		
		
		  
		  game.setListener(this);
		
		  view =new View();
		  
		  
		
		  
			for (int i=0 ; i< Game.getAvailableChampions().size() ;i++){
				 
				  JButton temp = new JButton();
				  temp.setText(Game.getAvailableChampions().get(i).getName());
				  temp.setToolTipText("<html>"+toString(Game.getAvailableChampions().get(i)));
				  temp.addActionListener(this);
				  view.paneStart.add(temp);
				  buttons.add(temp);
				  
				  
			  }
			
			
			
			 
			 String [] combochamp = new String[16];
			 combochamp[0]="Select Champion to Know his Abilities";
			 for (int i=1 ;i<=game.getAvailableChampions().size();i++){
				 combochamp[i]=game.getAvailableChampions().get(i-1).getName();
				
			 }
			 
		     combo3 = new JComboBox(combochamp);
		     combo3.setPreferredSize(new Dimension(300,300));
			 combo3.addActionListener(this);
			 
			 ab = new JTextArea();
			 ab.setEditable(false);
			 ab.setFont(new Font("Arial",Font.PLAIN,16));
			 
			view.scroll2 = new JScrollPane(ab);
			view.scroll2.setPreferredSize(new Dimension(300,500));
			view.scroll2.setHorizontalScrollBarPolicy(view.scroll2.HORIZONTAL_SCROLLBAR_ALWAYS);
			view.scroll2.setVerticalScrollBarPolicy(view.scroll2.VERTICAL_SCROLLBAR_ALWAYS);
				 
			 
			 
			view.StartRightDown.add(combo3,BorderLayout.NORTH);
			view.StartRightDown.add(view.scroll2,BorderLayout.SOUTH);
			
			
			view.combo.addActionListener(this);
			
			
		  view.revalidate();
		  view.repaint();
		  view.setVisible(true);
		  
		/*  for (int i=0 ; i<25 ;i++){
				 
			  JButton temp = new JButton();
			  view.paneBoard1.add(temp);
			  ChampsAndCovers.add(temp);
			  
			  
		  }*/
		 // view.Player1.setText(game.getFirstPlayer().getName());
		 // view.Player2.setText(game.getSecondPlayer().getName());
		//  FillBoard();
		
		  view.revalidate();
		  view.repaint();
		  view.setVisible(true);
		
	}
	
	public void FillBoard(){
		 // int k=0;
	    //  int m=0;
	     // int i=0;
		view.paneBoard1.removeAll();
	      for ( int i=4 ; i>=0 ;i--){
	    	  for (int z=0;z<5;z++ ){
				 
			  JButton temp = new JButton();
			//  System.out.print(game.getBoard()[i][z]);
			  Object o = game.getBoard()[i][z];
			  if(o!=null){
				  
				  if(o instanceof Champion){
					 
					  Champion c = (Champion)o;
					  temp.setText(c.getName()+"\n"+"("+ i+ " ,"+ z+")");
					//  if (game.getCurrentChampion().equals(c))
						  //temp.setToolTipText("<html>"+c.toStringCurrentChamp());
					 if(!game.getCurrentChampion().equals(c))
					      temp.setToolTipText("<html>"+c.toStringRemainingChamp());
					  
					  
					  temp.addActionListener(this);
					  view.paneBoard1.add(temp);
					  ChampsAndCovers.add(temp);
					/*  if(c.equals(game.getFirstPlayer().getLeader()))
						  temp.setBackground(Color.YELLOW);
					  else if ( c.equals(game.getSecondPlayer().getLeader()))
						  temp.setBackground(Color.WHITE);*/
					 
					  
					  
					  if (game.getFirstPlayer().getTeam().contains(c)) 
					   temp.setBackground(Color.RED);
					  else
						  temp.setBackground(Color.BLUE);
					  
					  if (c.equals(game.getCurrentChampion())){
						  temp.setBackground(Color.yellow);
						 // temp.setText("Champion playing");
						  //temp.setToolTipText("<html>"+c.toStringCurrentChamp());
					  }
						  
				 
			  }
				  else if(o instanceof Cover){
					  Cover cover = (Cover)o;
					  temp.setText("Cover"+"\n"+"("+ i+ " ,"+ z+")");
					  temp.setBackground(Color.LIGHT_GRAY);
					 // han3ml toString ll cover 
					 temp.setToolTipText("<html>"+"Current HP : "+cover.getCurrentHP());
					 // temp.setToolTipText("<html>"+toString(Game.getAvailableChampions().get(i)));
					  temp.addActionListener(this);
					  view.paneBoard1.add(temp);
					  ChampsAndCovers.add(temp);
					//  temp.setBackground(Color.WHITE);
				 
					  
				  }
			 
			/*  BoardList[k][m]=temp;
			  m++;
			  if (m>=5)
				  k++;
			  if (m>=5)
				  m=0;
			  if (k>=5)
				  break;
				  	  */
		  }
			  else {
				  temp.setText("("+ i+ " ,"+ z+")");
			 temp.setBackground(Color.WHITE); 
			  view.paneBoard1.add(temp);
			  ChampsAndCovers.add(temp);
			//  temp.setBackground(Color.WHITE);
			  }
			  
			  }
	    	     }
	      
	      Dimension d=view.getToolkit().getScreenSize();
	  	 height=d.getHeight();
	  	 width=d.getWidth();
		  view.revalidate();
		  view.repaint();
		  view.setVisible(true);
		  
	}
	
	
		public void Combo2 (){
//			 
//			 String [] comboarray = new String[6];
//			 int j=1;
//			 comboarray[0]="Select Ability";
//			 for (int i=0 ;i<game.getCurrentChampion().getAbilities().size();i++){
//				// System.out.print("Enter ");
//				 String s =game.getCurrentChampion().getAbilities().get(i).getName();
//				 comboarray[j]=game.getCurrentChampion().getAbilities().get(i).getName();
//				 j++;
//			 }
//			 comboarray[j]="Use Leader Ability";
//			 combo2 = new JComboBox(comboarray);
//			 combo2.addActionListener(this);
//			 
		}
		
	

		





		
  public void actionPerformed( ActionEvent e) {
			if (buttons.contains(e.getSource())){
				
				ChooseChampions(e);
				
			}
			if (e.getSource()==view.combo) {
			 if (view.combo.getSelectedItem().equals("Move Up")){
				MoveUp();
			}
			else if (view.combo.getSelectedItem().equals("Move Down")){
				MoveDown();
			}
			else if (view.combo.getSelectedItem().equals("Move Right")){
				MoveRight();
			}
			else if (view.combo.getSelectedItem().equals("Move Left")){
				MoveLeft();
			}
			else if (view.combo.getSelectedItem().equals("Attack Up")){
				AttackUp();
			}
			else if (view.combo.getSelectedItem().equals("Attack Down")){
				AttackDown();
			}
			else if (view.combo.getSelectedItem().equals("Attack Right")){
				AttackRight();
			}
			else if (view.combo.getSelectedItem().equals("Attack Left")){
				AttackLeft();
			}
			else if (view.combo.getSelectedItem().equals("End Turn")){
				EndTurn();
			}
			 }
			if (combo2==e.getSource()){
				
			if (combo2.getSelectedItem().equals(game.getCurrentChampion().getAbilities().get(0).getName())){
					CastAbility1();
				}
			else if (combo2.getSelectedItem().equals(game.getCurrentChampion().getAbilities().get(1).getName()))
				CastAbility2();
			
			else if (combo2.getSelectedItem().equals(game.getCurrentChampion().getAbilities().get(2).getName()))
				CastAbility3();
			else if (game.getCurrentChampion().getAbilities().size()==4){
				if (combo2.getSelectedItem().equals(game.getCurrentChampion().getAbilities().get(3).getName()))
					CastAbility4();
			}
			else if (combo2.getSelectedItem().equals("Use Leader Ability")){
				UseLeader();
			}
			}
			 if(combo3==e.getSource()) {
				Show((String)combo3.getSelectedItem());
				
			 }
			/*else if (e.getSource().equals(view.UseLeader))
				UseLeader();
			else if (e.getSource().equals(view.CastAbility1))
				CastAbility1();
			else if (e.getSource().equals(view.CastAbility2))
				CastAbility2();
			else if (e.getSource().equals(view.CastAbility3))
				CastAbility3();
			
			*/
			
			//if(Button = directional){
			// view.showDirectionalAbilityOptions();
			//
			
			
			
			
			
		
			}
		
       public void MoveUp(){
    	   try {
				game.move(Direction.UP);    
			    PrepareGameBoard();
			    FillBoard();
    	   	
			} catch (NotEnoughResourcesException e1) {
				
			JOptionPane.showMessageDialog(view,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			} catch (UnallowedMovementException e1) {
				
				JOptionPane.showMessageDialog(view,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			}
			
		
       }
   
       
       public void MoveDown(){
    	   try {
				game.move(Direction.DOWN);
				   PrepareGameBoard();
					FillBoard();
					
				
			} catch (NotEnoughResourcesException
					| UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(view,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
			}
       }
      
       
       public void MoveRight(){
    	   try {
				game.move(Direction.RIGHT);
				    PrepareGameBoard();
					FillBoard();
					
				
			} catch (NotEnoughResourcesException
					| UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(view,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
			}
			
       }
       
       public void MoveLeft(){
    	   try {
				game.move(Direction.LEFT);
				    PrepareGameBoard();
					FillBoard();
					
				
			} catch (NotEnoughResourcesException
					| UnallowedMovementException e1) {
				JOptionPane.showMessageDialog(view,e1.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				//e1.printStackTrace();
			}
       }
       
       public void AttackUp(){
    	   try {
			game.attack(Direction.UP);
			CheckGameOver();
		//	view.getContentPane().removeAll();
		    PrepareGameBoard();
			FillBoard();
		} catch (NotEnoughResourcesException | ChampionDisarmedException
				| InvalidTargetException e) {
			JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		}
    	   
       }
       
       
       public void AttackDown(){
    	   try {
   			game.attack(Direction.DOWN);
   			CheckGameOver();
   		//	view.getContentPane().removeAll();
		    PrepareGameBoard();
			FillBoard();
   		} catch (NotEnoughResourcesException | ChampionDisarmedException
   				| InvalidTargetException e) {
   			JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
   			//e.printStackTrace();
   		}
    	   
       }
       
       
       public void AttackRight(){
    	   try {
   			game.attack(Direction.RIGHT);
   			CheckGameOver();
		    PrepareGameBoard();
			FillBoard();
   		} catch (NotEnoughResourcesException | ChampionDisarmedException
   				| InvalidTargetException e) {
   			JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
   		
   			//e.printStackTrace();
   		}
    	   
       }
       
       
       public void AttackLeft(){
    	
    	   try {
   			game.attack(Direction.LEFT);
   			CheckGameOver();
		    PrepareGameBoard();
			FillBoard();
   		} catch (NotEnoughResourcesException | ChampionDisarmedException
   				| InvalidTargetException e) {
   			JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
   			
   			//e.printStackTrace();
   		}
    	   
       }
       
       
       public void EndTurn(){
    	   Combo2();
    	   game.endTurn();
    	  
    	   CheckGameOver();
		    PrepareGameBoard();
			FillBoard();
    	   
       }
       
       
       public void UseLeader(){
    	   try {
			game.useLeaderAbility();
			CheckGameOver();
			 PrepareGameBoard();
				FillBoard();
		} 
    	   catch (LeaderNotCurrentException | LeaderAbilityAlreadyUsedException e) {
			JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
		
		}
    	   
    	   view.revalidate();
    		  view.repaint();
    		    
       }
       
       
       
       public void CheckGameOver(){
    	   String s;
    	   if (game.checkGameOver()!=null){
    		   Player winner = game.checkGameOver();
    		   view.dispose();
    		   view.setVisible(false);
    		  // String win =game.getFirstPlayer().getName();
    		   if (winner.equals(game.getFirstPlayer())){
    			    s= "Mr "+game.getFirstPlayer().getName()+" "+"we Won :')";
    		   }
    		   else 
    			   s= "Mr "+game.getSecondPlayer().getName()+" "+"we Won :')";
    		   
    		   JOptionPane.showMessageDialog(view,s,"Congratulations!",JOptionPane.INFORMATION_MESSAGE);
    		   System.exit(0);
    	   }
       }
       
       
       public void CastAbility1 () {
  //   if(game.getCurrentChampion().getAbilities().get(0).getName().equals(view.CastAbility1.getText()))    	 {
    	   if(game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.SURROUND ||
    			 game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.SELFTARGET ||
    			 game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.TEAMTARGET) {
    		   try {
				game.castAbility( game.getCurrentChampion().getAbilities().get(0));
				CheckGameOver();
			    PrepareGameBoard();
				FillBoard();
			} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
				JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
				
		//		e.printStackTrace();
			}
    		 
    	 }
    	 else if (game.getCurrentChampion().getAbilities().get(0).getCastArea()==AreaOfEffect.DIRECTIONAL) {
    		 String direction =JOptionPane.showInputDialog("Please write a Direction (in lowercase)");
    		 try {
    			
    			 if (CastHelper(direction)!=null){
				game.castAbility(game.getCurrentChampion().getAbilities().get(0),CastHelper(direction));
				CheckGameOver();
			    PrepareGameBoard();
				FillBoard();
			}} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
				JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
			//	e.printStackTrace();
			}
    		 
    	 }
    	 else {
    		 String x=JOptionPane.showInputDialog("Please write a x-axis of chosen target");
    		 String y=JOptionPane.showInputDialog("Please write a y-axis of chosen target");
    		 
    		 try {
    			 int x1=Integer.parseInt(x);
        		 int y1=Integer.parseInt(y);
        		 if(x1<0 || x1>4 || y1<0 || y1>4 )
	  	    			JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);
	  	    		 else{	 
				game.castAbility(game.getCurrentChampion().getAbilities().get(0), x1, y1);
				CheckGameOver();
			    PrepareGameBoard();
				FillBoard();
			}} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
					| CloneNotSupportedException e) {
				JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
		//		e.printStackTrace();
			}catch (NumberFormatException e) {
				
				JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);

			// TODO: handle exception
	    		 }
    	 }
     }
    	   
   //    }
       
       public void CastAbility2 () {
    	 //    if(game.getCurrentChampion().getAbilities().get(1).getName().equals(view.CastAbility2.getText()))    	 {
    	    	   if(game.getCurrentChampion().getAbilities().get(1).getCastArea()==AreaOfEffect.SURROUND ||
    	    			 game.getCurrentChampion().getAbilities().get(1).getCastArea()==AreaOfEffect.SELFTARGET ||
    	    			 game.getCurrentChampion().getAbilities().get(1).getCastArea()==AreaOfEffect.TEAMTARGET) {
    	    		   try {
    					game.castAbility( game.getCurrentChampion().getAbilities().get(1));
    					CheckGameOver();
    				    PrepareGameBoard();
    					FillBoard();
    				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
    					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    					
    			//		e.printStackTrace();
    				}
    	    		 
    	    	 }
    	    	 else if (game.getCurrentChampion().getAbilities().get(1).getCastArea()==AreaOfEffect.DIRECTIONAL) {
    	    		 String direction =JOptionPane.showInputDialog("Please write a Direction (in lowercase)");
    	    		 try {
    	    			// System.out.println(CastHelper(direction));
    	    			 if (CastHelper(direction)!=null){
    					game.castAbility(game.getCurrentChampion().getAbilities().get(1),CastHelper(direction));
    					CheckGameOver();
    				    PrepareGameBoard();
    					FillBoard();
    				}}catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
    					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    				//	e.printStackTrace();
    				}
    	    		 
    	    	 }
    	    	 else {
    	    		 String x=JOptionPane.showInputDialog("Please write a x-axis of chosen target");
    	    		 String y=JOptionPane.showInputDialog("Please write a y-axis of chosen target");
    	    		
    	    		 try {
    	    			 int x1=Integer.parseInt(x);
        	    		 int y1=Integer.parseInt(y);
        	    		 if(x1<0 || x1>4 || y1<0 || y1>4 )
      	  	    			JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);
      	  	    		 else{	 
    					game.castAbility(game.getCurrentChampion().getAbilities().get(1), x1, y1);
    					CheckGameOver();
    				    PrepareGameBoard();
    					FillBoard();
    				}} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
    						| CloneNotSupportedException e) {
    					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    			//		e.printStackTrace();
    				}catch (NumberFormatException e) {
    					
    					JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);

    				// TODO: handle exception
      	    		 }
    	    	 }
    	     }
    	    	   
    	  //     }
    	       
    	       
       public void CastAbility3 () {
    	  //   if(game.getCurrentChampion().getAbilities().get(2).getName().equals(view.CastAbility3.getText()))    	 {
    	    	   if(game.getCurrentChampion().getAbilities().get(2).getCastArea()==AreaOfEffect.SURROUND ||
    	    			 game.getCurrentChampion().getAbilities().get(2).getCastArea()==AreaOfEffect.SELFTARGET ||
    	    			 game.getCurrentChampion().getAbilities().get(2).getCastArea()==AreaOfEffect.TEAMTARGET) {
    	    		   try {
    					game.castAbility( game.getCurrentChampion().getAbilities().get(2));
    					CheckGameOver();
    				    PrepareGameBoard();
    					FillBoard();
    				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
    					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    					
    			//		e.printStackTrace();
    				}
    	    		 
    	    	 }
    	    	 else if (game.getCurrentChampion().getAbilities().get(2).getCastArea()==AreaOfEffect.DIRECTIONAL) {
    	    		 String direction =JOptionPane.showInputDialog("Please write a Direction (in lowercase)");
    	    		 try {
    	    			// System.out.println(CastHelper(direction));
    	    			 if (CastHelper(direction)!=null){
    					game.castAbility(game.getCurrentChampion().getAbilities().get(2),CastHelper(direction));
    					CheckGameOver();
    				    PrepareGameBoard();
    					FillBoard();
    				}} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
    					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    				//	e.printStackTrace();
    				}
    	    		 
    	    	 }
    	    	 else {
    	    		 String x=JOptionPane.showInputDialog("Please write a x-axis of chosen target");
    	    		 String y=JOptionPane.showInputDialog("Please write a y-axis of chosen target");
    	    		
    	    		 try {
    	    			 int x1=Integer.parseInt(x);
        	    		 int y1=Integer.parseInt(y);
        	    		 if(x1<0 || x1>4 || y1<0 || y1>4 )
      	  	    			JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);
      	  	    		 else{	 
    					game.castAbility(game.getCurrentChampion().getAbilities().get(2), x1, y1);
    					CheckGameOver();
    				    PrepareGameBoard();
    					FillBoard();
    				}} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
    						| CloneNotSupportedException e) {
    					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
    			//		e.printStackTrace();
    				}catch (NumberFormatException e) {
    					
    					JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);

    				
      	    		 }
    	    	 }
    	     }
    	    	   
    	//       }
    	       
    	       
       public void CastAbility4 () {
  	 //    if(game.getCurrentChampion().getAbilities().get(3).getName().equals(view.CastAbility3.getText()))    	 {
  	    	   if(game.getCurrentChampion().getAbilities().get(3).getCastArea()==AreaOfEffect.SURROUND ||
  	    			 game.getCurrentChampion().getAbilities().get(3).getCastArea()==AreaOfEffect.SELFTARGET ||
  	    			 game.getCurrentChampion().getAbilities().get(3).getCastArea()==AreaOfEffect.TEAMTARGET) {
  	    		   try {
  					game.castAbility( game.getCurrentChampion().getAbilities().get(3));
  					CheckGameOver();
  				    PrepareGameBoard();
  					FillBoard();
  				} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
  					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
  					
  			//		e.printStackTrace();
  				}
  	    		 
  	    	 }
  	    	 else if (game.getCurrentChampion().getAbilities().get(3).getCastArea()==AreaOfEffect.DIRECTIONAL) {
  	    		 String direction =JOptionPane.showInputDialog("Please write a Direction (in lowercase)");
  	    		 try {
  	    			 if (CastHelper(direction)!=null){
  					game.castAbility(game.getCurrentChampion().getAbilities().get(3),CastHelper(direction));
  					CheckGameOver();
  				    PrepareGameBoard();
  					FillBoard();
  				}} catch (NotEnoughResourcesException | AbilityUseException | CloneNotSupportedException e) {
  					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
  				//	e.printStackTrace();
  				}
  	    		 
  	    	 }
  	    	 else {
  	    		 String x=JOptionPane.showInputDialog("Please write a x-axis of chosen target");
  	    		 String y=JOptionPane.showInputDialog("Please write a y-axis of chosen target");
  	    		 	    		
  	    		 try {
  	    			int x1=Integer.parseInt(x);
 	  	    		 int y1=Integer.parseInt(y);
 	  	    		 if(x1<0 || x1>4 || y1<0 || y1>4 )
 	  	    			JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);
 	  	    		 else{	 
  					game.castAbility(game.getCurrentChampion().getAbilities().get(3), x1, y1);
  					CheckGameOver();
  				    PrepareGameBoard();
  					FillBoard();
  				}} catch (NotEnoughResourcesException | AbilityUseException | InvalidTargetException
  						| CloneNotSupportedException e) {
  					JOptionPane.showMessageDialog(view,e.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
  			//		e.printStackTrace();
  				}
  	    		 catch (NumberFormatException e) {
				
					JOptionPane.showMessageDialog(view,"Please Enter a valid number","Error",JOptionPane.ERROR_MESSAGE);

				// TODO: handle exception
  	    		 }
  	    	 }
  	     }
  	    	   
  	  //     }
  	       
       
       
       
       
       public Direction CastHelper(String s) {
    	 //  System.out.print("hii");
    	   Direction d = null;
    	   
    	   if (s.equals("up")){
    		   
    	 //  System.out.print("hii");
    		   d=Direction.UP;
    	   }
    	   else if (s.equals("down"))
    		   d=Direction.DOWN;
    	   else if (s.equals("right"))
    		   d=Direction.RIGHT;
    	   else if (s.equals("left"))
    		   d=Direction.LEFT;
    	   else{
    		   System.out.println("hello");
    		   JOptionPane.showMessageDialog(view,"Please Enter A Valid Direction","Error",JOptionPane.ERROR_MESSAGE);
    	   }
    	   return d;
    	   
       }
       
		public void ChooseChampions(ActionEvent e){
		//	System.out.print(game.getFirstPlayer().getTeam());
			for (int k=0;k<buttons.size();k++){
				
				if (buttons.get(k).equals(e.getSource())){
					buttons.get(k).setEnabled(false);
					if (i<3 && i>=0){
						//view.textStart2.setText("First Player choosing now");
						if (i==0)
							game.getFirstPlayer().setLeader(game.getAvailableChampions().get(k));
						game.getFirstPlayer().getTeam().add(game.getAvailableChampions().get(k));
						i++;	
						if(i>2)
							view.textStart2.setText("Second Player Choosing Now");
							
					}
					else if (i<5 && i>=3){
						
						if (i==3)
						 game.getSecondPlayer().setLeader(game.getAvailableChampions().get(k));
						game.getSecondPlayer().getTeam().add(game.getAvailableChampions().get(k));
						i++;
					}
					else{
						game.getSecondPlayer().getTeam().add(game.getAvailableChampions().get(k));
						game.placeChampions();
						game.prepareChampionTurns();
						view.StartRight.setVisible(false);
						view.textStart.setVisible(false);
						view.paneStart.setVisible(false);
						
						FillBoard();
						PrepareGameBoard();
						break;
					//	System.out.print(game.getCurrentChampion().toStringCurrentChamp());
						//view.LabelTurnOrder.setText(game.toStringTurnOrder());
						
						
						
						
						//view.add(view.paneBoard2,BorderLayout.WEST);
						//view.add(view.Player1);
						//view.add(view.Player2);
						
						
						
						
						
					}
					
			}
				
			
				
			}
			
			/*for(Champion c:game.getSecondPlayer().getTeam()){ //End game scenario
				c.setCurrentHP(1);
			}*/

			}
		
		
		public void PrepareGameBoard(){
			
			view.paneLeft.removeAll();
			view.paneMethods.removeAll();
			view.paneRight.removeAll();
			view.Leader1.removeAll();
			view.Leader2.removeAll();
			
			
			
			view.setLayout(new BorderLayout());
			
			
			
			
			view.LabelTurnOrder.setText(game.toStringTurnOrder());
			view.paneRight.add(view.paneBoardTurnOrder,BorderLayout.NORTH);
			
			
			view.paneRight.add(view.paneCombo,BorderLayout.CENTER);
			
			
			/*
			 view.CastAbility1.setText(game.getCurrentChampion().getAbilities().get(0).getName());
	         view.CastAbility2.setText(game.getCurrentChampion().getAbilities().get(1).getName());
	         view.CastAbility3.setText(game.getCurrentChampion().getAbilities().get(2).getName());
	         if (game.getCurrentChampion().equals(game.getFirstPlayer().getLeader())
	        		 || game.getCurrentChampion().equals(game.getSecondPlayer().getLeader()))
	        	 view.UseLeader.setEnabled(true);
	         else 
	        	 view.UseLeader.setEnabled(false);*/
			//Combo2();
			 
			
			
			 String [] comboarray = new String[6];
			 int j=1;
			 comboarray[0]="Select Ability";
			 for (int i=0 ;i<game.getCurrentChampion().getAbilities().size();i++){
				 
				
				 String s =game.getCurrentChampion().getAbilities().get(i).getName();
				 comboarray[j]=game.getCurrentChampion().getAbilities().get(i).getName();
				// System.out.println(comboarray[j]);
				 j++;
			 }
			 comboarray[j]="Use Leader Ability";
			 combo2 = new JComboBox(comboarray);
			 combo2.addActionListener(this);
			 
			view.paneMethods.add(combo2);
	         
	         
	        // view.CurrentChampion.setText(game.getCurrentChampion().toStringCurrentChamp());
	         
	         view.paneRight.add(view.paneMethods ,BorderLayout.SOUTH);
			
	         view.PlayerName1.setForeground(Color.RED);
	         view.PlayerName2.setForeground(Color.BLUE);
	         
	         view.PlayerName1.setText("First Player Name : " +"\n"+game.getFirstPlayer().getName());
	         view.PlayerName2.setText("Second Player Name : " +"\n"+game.getSecondPlayer().getName());
	         view.PlayerDetails1.setText(game.getFirstPlayer().toStringsPlayersChamp());
	         view.PlayerDetails2.setText(game.getSecondPlayer().toStringsPlayersChamp());
	         
	        // view.paneLeft.add(view.paneBoardPlayer1,BorderLayout.NORTH);
	         view.paneLeft.add(view.paneFirstwaSecond ,BorderLayout.NORTH);
	         
	         view.CurrentChampText.setText(game.getCurrentChampion().toStringCurrentChamp());
	         
	         
	         /*JPanel Scrollpane= new JPanel();
	         Scrollpane.setPreferredSize(new Dimension(680,600));
	         view.paneLeft.add(Scrollpane ,BorderLayout.CENTER);*/
	         
	         
	         JScrollPane scroll = new JScrollPane(view.CurrentChampText);
	         scroll.setPreferredSize(new Dimension(680,775));
	         scroll.setHorizontalScrollBarPolicy(scroll.HORIZONTAL_SCROLLBAR_ALWAYS);
	         scroll.setVerticalScrollBarPolicy(scroll.VERTICAL_SCROLLBAR_ALWAYS);
	         view.paneLeft.add(scroll,BorderLayout.SOUTH);
	         
	         
	       //  view.paneLeft.add(view.CurrentChampPane ,BorderLayout.SOUTH);
	         
	         
	         if (game.isFirstLeaderAbilityUsed()==true)
	        	 view.Leader1.setText("Leader Ability Used");
	         else 
	        	 view.Leader1.setText("Leader Not Ability Used");
	        	 
	        	
	         if (game.isSecondLeaderAbilityUsed()==true)
	        	 view.Leader2.setText("Leader Ability Used");
	         else 
	        	 view.Leader2.setText("Leader Not Ability Used");
	        	 
	         
			view.UseLeader.addActionListener(this);
			view.CastAbility1.addActionListener(this);
			view.CastAbility2.addActionListener(this);
			view.CastAbility3.addActionListener(this);
			
			
			
	         
	         
	         
			
			view.add(view.paneBoard1,BorderLayout.CENTER);
			view.add(view.paneRight, BorderLayout.WEST);
			view.add(view.paneLeft, BorderLayout.EAST);
			
			
			//view.paneBoard1.setBounds(470, 0, 1000, 1000);
	//	view.add(view.paneBoard1 ,BorderLayout.CENTER);
			
			//view.paneBoard2.setBounds(0, 800, 600, 600);
			//view.add(view.paneBoard2 ,BorderLayout.WEST);
			
			
		/*	view.LabelTurnOrder.setBounds(100,500,650,650);
            view.LabelTurnOrder.setText(game.toStringTurnOrder());
            view.add(view.paneBoardTurnOrder);
            
            view.CurrentChampText.setBounds(0,0,250,700);
            view.CurrentChampText.setText(game.getCurrentChampion().toStringCurrentChamp());
            view.add(view.CurrentChampText);
            
            
            view.RemanChamp1.setBounds(25, 51, 20, 0);
            view.RemanChamp2.setBounds(25, 510, 200, 300);
            view.RemanChamp1.setText(game.getFirstPlayer().toStringsPlayersChamp());
            view.RemanChamp2.setText(game.getSecondPlayer().toStringsPlayersChamp());
            view.Leader1.setText("Use Leader Ability not used");
            view.Leader2.setText("Use Leader Ability not used");
            view.add(view.paneBoardPlayer1);
            view.add(view.paneBoardPlayer2);
          //  view.add(view.Leader1);
          //  view.add(view.Leader2);
            
          
           
           view.paneMethods.add(view.CastAbility1);
           view.paneMethods.add(view.CastAbility2);
           view.paneMethods.add(view.CastAbility3);
           view.add(view.paneMethods);*/
          //System.out.print(game.isFirstLeaderAbilityUsed());
          //System.out.print(game.isSecondLeaderAbilityUsed());
            
            view.revalidate();
      	    view.repaint();
		}

		
		public void Show(String s) {
			  
	    	  for (int i=0;i<game.getAvailableChampions().size();i++) {
	    		  if (game.getAvailableChampions().get(i).getName().equals(s))
	    			  ab.setText(game.getAvailableChampions().get(i).toStringAbilities());
	    	  }
		}
		public String toString(Champion c){
			String s=" ";
			s ="Attack Damage:"+c.getAttackDamage()+ "<br>" +
				"Attack Damage:"+c.getAttackRange()+"<br>" +
				"Current Action points:" +c.getCurrentActionPoints()+"<br>" +
				"Current HP:"+c.getCurrentHP()+"<br>" +
				"Mana:"+c.getMana()+"<br>" +
				"Max Action Points:"+c.getMaxActionPointsPerTurn()+"<br>" +
				"Max HP:"+c.getMaxHP()+"<br>" +
				"Speed:"+c.getSpeed()+"<br>";
			s=s+c.toStringAbilityName();
				
				
			return s;
		}
		
		public static void main(String[]args){
	        new StartView();
	        
	      
	}

		


	

     
}
