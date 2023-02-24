package model.abilities;

import java.util.ArrayList;

import model.world.Damageable;

public class DamagingAbility extends Ability {

	private int damageAmount;

	public DamagingAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			int damageAmount) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.damageAmount = damageAmount;
	}

	public int getDamageAmount() {
		return damageAmount;
	}

	public void setDamageAmount(int damageAmount) {
		this.damageAmount = damageAmount;
	}
	public String toStringDamage(){
		String s="";
		
		s="Ability name : "+ this.getName()+"\n"+
		"Area of effect : "+this.getCastArea()+ "\n"+
		"Cast Range : "+this.getCastRange()+ "\n"+
		"Mana cost : "+this.getManaCost()+ "\n"+
		"Action cost : "+this.getRequiredActionPoints()+ "\n"+
		"Current cool down : "+this.getCurrentCooldown()+ "\n"+
		"Base cool down : "+this.getBaseCooldown()+ "\n"+
		"Ability Type : Damaging Ability "+ "\n"+
		"Damage amount : " + this.getDamageAmount();
		return s;
		
	}

	@Override
	public void execute(ArrayList<Damageable> targets) {
		for (Damageable d : targets)

			d.setCurrentHP(d.getCurrentHP() - damageAmount);

	}
}
