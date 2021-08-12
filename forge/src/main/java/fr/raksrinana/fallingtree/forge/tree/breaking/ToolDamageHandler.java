package fr.raksrinana.fallingtree.forge.tree.breaking;

import fr.raksrinana.fallingtree.forge.config.Config;
import lombok.Getter;
import net.minecraft.world.item.ItemStack;

public class ToolDamageHandler{
	private final ItemStack tool;
	private final double damageMultiplicand;
	private final boolean preserve;
	private final int maxDurabilityTaken;
	@Getter
	private final int maxBreakCount;
	
	public ToolDamageHandler(ItemStack tool, double damageMultiplicand, boolean preserve, int breakableCount){
		this.tool = tool;
		this.damageMultiplicand = damageMultiplicand;
		this.preserve = preserve;
		
		maxBreakCount = damageMultiplicand == 0 ? breakableCount : (int) Math.floor(getToolDurability() / damageMultiplicand);
		maxDurabilityTaken = getDamage(maxBreakCount);
	}
	
	private int getDamage(long count){
		if(Double.compare(damageMultiplicand, 0) <= 0){
			return 1;
		}
		var rawDamage = count * damageMultiplicand;
		
		return (int) switch(Config.COMMON.getTools().getDamageRounding()){
			case ROUND_DOWN -> Math.floor(rawDamage);
			case ROUND_UP -> Math.ceil(rawDamage);
			case ROUNDING -> Math.round(rawDamage);
			case PROBABILISTIC -> getProbabilisticDamage(rawDamage);
		};
	}

	private int getProbabilisticDamage(double rawDamage) {
		var damage = Math.floor(rawDamage);
		var finalDamage = (int) damage;
		var probability = rawDamage - damage;
		if (Math.random() < probability) {
			finalDamage++;
		}
		return finalDamage;
	}
	
	public boolean shouldPreserveTool(){
		if(!preserve){
			return false;
		}
		return getToolDurability() <= maxDurabilityTaken;
	}
	
	public int getActualDamage(int brokenCount){
		return brokenCount == maxBreakCount ? maxDurabilityTaken : Math.min(maxDurabilityTaken, getDamage(brokenCount));
	}
	
	private int getToolDurability(){
		return tool.getMaxDamage() - tool.getDamageValue();
	}
}
