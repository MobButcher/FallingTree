package fr.raksrinana.fallingtree.forge.tree.builder;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.level.block.Block;

public class AdjacentAbortSearchException extends AbortSearchException{
	private final Block block;
	
	public AdjacentAbortSearchException(Block block){
		super("Found block " + block + " that isn't whitelisted in the adjacent blocks");
		this.block = block;
	}
	
	@Override
	public Component getComponent(){
		return new TranslatableComponent("chat.fallingtree.search_aborted.adjacent", block);
	}
}
