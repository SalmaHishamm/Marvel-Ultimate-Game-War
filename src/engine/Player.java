package engine;

import java.util.ArrayList;

import model.world.Champion;

public class Player {
	private String name;
	private ArrayList<Champion> team;
	private Champion leader;
	

	public Player(String name) {
		this.name = name;
		team = new ArrayList<Champion>();
		
	}


	public Champion getLeader() {
		return leader;
	}

	public void setLeader(Champion leader) {
		this.leader = leader;
	}

	public String getName() {
		return name;
	}

	public ArrayList<Champion> getTeam() {
		return team;
	}
	public String toStringsPlayersChamp(){
		String s="";
		//String Leader= "";
		for (int i=0 ;i<this.getTeam().size();i++){
			if (this.getTeam().get(i).equals (this.getLeader())){
			    s=s+this.getTeam().get(i).getName()+" (Leader)"+"\n";
		}
			else
				s=s+this.getTeam().get(i).getName()+"\n";
				
			}
		return s;
		
	}


}
