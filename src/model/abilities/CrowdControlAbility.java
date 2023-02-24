package model.abilities;

import java.util.ArrayList;

import model.effects.Effect;
import model.world.Champion;
import model.world.Damageable;

public class CrowdControlAbility extends Ability {
	private Effect effect;

	public CrowdControlAbility(String name, int cost, int baseCoolDown, int castRadius, AreaOfEffect area, int required,
			Effect effect) {
		super(name, cost, baseCoolDown, castRadius, area, required);
		this.effect = effect;

	}

	public Effect getEffect() {
		return effect;
	}
	public String toStringCrowd(){
		String s="";
		
		s="Ability name : "+ this.getName()+"\n"+
		"Area of effect : "+this.getCastArea()+ "\n"+
		"Cast Range : "+this.getCastRange()+ "\n"+
		"Mana cost : "+this.getManaCost()+ "\n"+
		"Action cost : "+this.getRequiredActionPoints()+ "\n"+
		"Current cool down : "+this.getCurrentCooldown()+ "\n"+
		"Base cool down : "+this.getBaseCooldown()+ "\n"+
		"Ability Type : Crowd Control Ability "+ "\n"+
		"Effect name : " + this.getEffect().getName()+ "\n"+
		"Effect Duration : "+this.getEffect().getDuration();
		return s;
		
	}

	@Override
	public void execute(ArrayList<Damageable> targets) throws CloneNotSupportedException {
		for(Damageable d: targets)
		{
			Champion c =(Champion) d;
			c.getAppliedEffects().add((Effect) effect.clone());
			effect.apply(c);
		}
		
	}

}
