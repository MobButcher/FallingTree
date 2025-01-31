package fr.raksrinana.fallingtree.forge.utils;

import lombok.Getter;
import net.minecraft.core.BlockPos;
import java.util.Objects;
import static java.lang.System.currentTimeMillis;

@Getter
public class CacheSpeed{
	private final BlockPos pos;
	private final float speed;
	private final long millis;
	
	public CacheSpeed(BlockPos pos, float speed){
		this.pos = pos;
		this.speed = speed;
		millis = currentTimeMillis();
	}
	
	public boolean isValid(BlockPos blockPos){
		return millis + 1000 >= currentTimeMillis() && Objects.equals(pos, blockPos);
	}
}
