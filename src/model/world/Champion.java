package model.world;

import java.awt.Point;
import java.util.ArrayList;






import model.abilities.Ability;
import model.abilities.CrowdControlAbility;
import model.abilities.DamagingAbility;
import model.abilities.HealingAbility;
import model.effects.Effect;

@SuppressWarnings("rawtypes")
public abstract class Champion implements Damageable,Comparable {
	private String name;
	private int maxHP;
	private int currentHP;
	private int mana;
	private int maxActionPointsPerTurn;
	private int currentActionPoints;
	private int attackRange;
	private int attackDamage;
	private int speed;
	private ArrayList<Ability> abilities;
	private ArrayList<Effect> appliedEffects;
	private Condition condition;
	private Point location;
	

	public Champion(String name, int maxHP, int mana, int actions, int speed, int attackRange, int attackDamage) {
		this.name = name;
		this.maxHP = maxHP;
		this.mana = mana;
		this.currentHP = this.maxHP;
		this.maxActionPointsPerTurn = actions;
		this.speed = speed;
		this.attackRange = attackRange;
		this.attackDamage = attackDamage;
		this.condition = Condition.ACTIVE;
		this.abilities = new ArrayList<Ability>();
		this.appliedEffects = new ArrayList<Effect>();
		this.currentActionPoints=maxActionPointsPerTurn;
	}

	public int getMaxHP() {
		return maxHP;
	}

	public String getName() {
		return name;
	}

	public void setCurrentHP(int hp) {

		if (hp <= 0) {
			currentHP = 0;
			condition=Condition.KNOCKEDOUT;
			
		} 
		else if (hp > maxHP)
			currentHP = maxHP;
		else
			currentHP = hp;

	}

	
	public int getCurrentHP() {

		return currentHP;
	}

	public ArrayList<Effect> getAppliedEffects() {
		return appliedEffects;
	}

	public int getMana() {
		return mana;
	}

	public void setMana(int mana) {
		this.mana = mana;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int currentSpeed) {
		if (currentSpeed < 0)
			this.speed = 0;
		else
			this.speed = currentSpeed;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public Point getLocation() {
		return location;
	}

	public void setLocation(Point currentLocation) {
		this.location = currentLocation;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public ArrayList<Ability> getAbilities() {
		return abilities;
	}

	public int getCurrentActionPoints() {
		return currentActionPoints;
	}

	public void setCurrentActionPoints(int currentActionPoints) {
		if(currentActionPoints>maxActionPointsPerTurn)
			currentActionPoints=maxActionPointsPerTurn;
		else 
			if(currentActionPoints<0)
			currentActionPoints=0;
		this.currentActionPoints = currentActionPoints;
	}

	public int getMaxActionPointsPerTurn() {
		return maxActionPointsPerTurn;
	}

	public void setMaxActionPointsPerTurn(int maxActionPointsPerTurn) {
		this.maxActionPointsPerTurn = maxActionPointsPerTurn;
	}

	public int compareTo(Object o)
	{
		Champion c = (Champion)o;
		if(speed==c.speed)
			return name.compareTo(c.name);
		return -1 * (speed-c.speed);
	}
	
	
	
	public String toStringAbilityName(){
		String s="";
		for (int i=0;i<this.getAbilities().size();i++){
			s=s+ "Ability "+(i+1)+" :"+this.getAbilities().get(i).getName()+"<br>";
					
		}
		return s;
	}
	
	
	public String toStringEffects(){
		String s="";
		for (int i=0 ;i<this.getAppliedEffects().size();i++){	
			s= s+ "Effect "+(i+1)+" :"+"Effect Name : "+this.getAppliedEffects().get(i).getName()+"\n"+
		          "Effect Duration : "+this.getAppliedEffects().get(i).getDuration();
		}
		return s;
		}
	
	
	public String toStringAbilities(){
		String s="";
		for (int i=0;i<this.getAbilities().size();i++){
			if (this.getAbilities().get(i) instanceof HealingAbility){
				
				HealingAbility h =(HealingAbility) this.getAbilities().get(i);
			   s="\n"+s+ "Ability "+(i+1)+" :"+"\n"+(h.toStringHeal())+"\n"+"\n";
			   
			}
			else if (this.getAbilities().get(i) instanceof DamagingAbility){
				
				DamagingAbility h =(DamagingAbility) this.getAbilities().get(i);
			   s="\n"+s+"Ability "+(i+1)+" :"+"\n"+ (h.toStringDamage())+"\n"+"\n";
			}
			else {
				CrowdControlAbility h =(CrowdControlAbility) this.getAbilities().get(i);
				   s="\n"+s+ "Ability "+(i+1)+" :"+"\n"+ (h.toStringCrowd())+"\n"+"\n";
				
			}
			
			
			
		}
		return s;
	}
	public String toStringCurrentChamp(){
		String type;
		if (this instanceof Hero)
			type= "Hero";
	   else if (this instanceof Hero)
		   type ="AntiHero";
	   else
		   type="Villian";
			
		
		String s=" ";
		s = "Name : "+ name+"\n"+
		    "Type : "+type+"\n"+
			"Attack Damage:"+this.getAttackDamage()+ "\n" +
			"Attack Range:"+this.getAttackRange()+"\n" +
			"Current Action points:" +this.getCurrentActionPoints()+"\n"+
			"Current HP:"+this.getCurrentHP()+"\n" +
			"Mana:"+this.getMana()+"\n" +
			"Max Action Points:"+this.getMaxActionPointsPerTurn()+"\n" +
			"Max HP:"+this.getMaxHP()+"\n" +
			"Speed:"+this.getSpeed()+"\n";
		
		s=s+this.toStringAbilities()+"\n"+this.toStringEffects()+"\n"+"\n";
			
			
		return s;
	}
	public String toStringRemainingChamp(){
		String type;
		if (this instanceof Hero)
			type= "Hero";
	   else if (this instanceof Hero)
		   type ="AntiHero";
	   else
		   type="Villian";
			
		
		String s=" ";
		s = "Name : "+ name+"<br>"+
		    "Type : "+type+"<br>"+
			"Attack Damage:"+this.getAttackDamage()+ "<br>" +
			"Attack Range:"+this.getAttackRange()+"<br>" +
			
			"Current HP:"+this.getCurrentHP()+"<br>" +
			"Mana:"+this.getMana()+"<br>" +
			"Max Action Points:"+this.getMaxActionPointsPerTurn()+"<br>" +
			
			"Speed:"+this.getSpeed()+"<br>";
		s=s+"\n"+this.toStringEffects();
			
			
		return s;
		
	}
	
	
	
public abstract void useLeaderAbility(ArrayList<Champion> targets);
}
