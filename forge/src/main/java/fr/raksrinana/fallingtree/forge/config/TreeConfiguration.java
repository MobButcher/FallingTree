package fr.raksrinana.fallingtree.forge.config;

import com.google.common.collect.Lists;
import lombok.Getter;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Getter
public class TreeConfiguration{
	private static final String[] DESC_BREAK_MODE = {
			"How to break the tree.",
			"INSTANTANEOUS will break it in one go.",
			"SHIFT_DOWN will make the tree fall down as you cut it, so you still have to break x blocks but don't have to climb the tree for them."
	};
	private static final String[] DESC_DETECTION_MODE = {
			"What part of the tree should be cut.",
			"WHOLE_TREE tree will break the whole tree.",
			"ABOVE_CUT will break only blocks that are connected from above the cut point.",
			"ABOVE_Y will break only blocks that are above the y value of the cut point."
	};
	private static final String[] DESC_WHITELISTED_LOGS = {
			"Additional list of blocks considered as logs and that will be destroyed by the mod.",
			"INFO: Blocks marked with the log tag will already be whitelisted."
	};
	private static final String[] DESC_BLACKLISTED_LOGS = {
			"List of blocks that should not be considered as logs.",
			"INFO: This wins over the whitelist."
	};
	private static final String[] DESC_WHITELISTED_LEAVES = {
			"Additional list of blocks considered as leaves (decay naturally).",
			"INFO: Blocks marked with the leaves tag will already be whitelisted."
	};
	private static final String[] DESC_WHITELISTED_NON_DECAY_LEAVES = {
			"Additional list of blocks considered as leaves but that doesn't decay (need to be broken by tool)."
	};
	private static final String[] DESC_BLACKLISTED_LEAVES = {
			"List of blocks that should not be considered as leaves.",
			"INFO: This wins over the whitelist."
	};
	private static final String[] DESC_MAX_SCAN_SIZE = {
			"The maximum number of blocks to scan when trying to detect a tree. If there's more than this value the tree won't be cut."
	};
	private static final String[] DESC_MAX_SIZE = {
			"The maximum size of a tree. If there's more logs than this value the max size action will be applied."
	};
	private static final String[] DESC_MAX_SIZE_ACTION = {
			"What to do when the max size of a tree is reached.",
			"ABORT: Tree won't be cut.",
			"CUT: Tree will still be cut leaving some of it untouched."
	};
	private static final String[] DESC_BREAK_ORDER = {
			"In what order logs are broken.",
			"This only makes sense if maxSize < maxScanSize.",
			"FURTHEST_FIRST: The furthest log will be broken first.",
			"CLOSEST_FIRST: The closest log will be broken first.",
			"INFO: Only in INSTANTANEOUS mode."
	};
	private static final String[] DESC_TREE_BREAKING = {
			"When set to true, the mod will cut trees with one cut."
	};
	private static final String[] DESC_LEAVES_BREAKING = {
			"When set to true, leaves that should naturally break will be broken instantly."
	};
	private static final String[] DESC_LEAVES_BREAKING_FORCE_RADIUS = {
			"Radius to force break leaves. If another tree is still holding the leaves they'll still be broken.",
			"If the leaves are persistent (placed by player) they'll also be destroyed.",
			"The radius is applied from one of the top most log blocks.",
			"INFO: break_leaves must be activated for this to take effect.",
			"INFO: Only in INSTANTANEOUS mode."
	};
	private static final String[] DESC_MINIMUM_LEAVES_AROUND_REQUIRED = {
			"The minimum amount of leaves that needs to be around the top most log in order for the mod to consider it a tree.",
			"INFO: Only in INSTANTANEOUS mode."
	};
	private static final String[] DESC_ALLOW_MIXED_LOGS = {
			"When set to true this allow to have any kind of log in a tree trunk.",
			"Otherwise (false) the trunk will be considered as being only one kind of log."
	};
	private static final String[] DESC_BREAK_NETHER_TREE_WARTS = {
			"When set to true nether tree warts (leaves) will be broken along with the trunk."
	};
	private static final String[] DESC_INSTANTLY_BREAK_WARTS = {
			"When set to true nether tree warts (leaves) will be broken with only one hit.",
			"INFO: Only in SHIFT_DOWN mode."
	};
	private static final String[] DESC_SEARCH_AROUND_RADIUS = {
			"This defines the area in which the tree is searched. If any branch is going out of this area it won't be cut.",
			"This value is the radius of the area.",
			"i.e. Setting a value of 2 will result on an area of 3x3 centered on the log broken.",
			"If this value is set to a negative number then no area restriction will be applied."
	};
	private static final String[] DESC_WHITELISTED_ADJACENT_BLOCKS = {
			"List the blocks that can be against the tree. If something else is adjacent then the tree won't be cut.",
			"INFO: Use adjacentStopMode to define how we stop the search for the tree."
	};
	private static final String[] DESC_ADJACENT_STOP_MODE = {
			"What to do when an non whitelisted adjacent block is found.",
			"STOP_ALL will stop the search and nothing will be cut.",
			"STOP_BRANCH will stop the current branch only. The rest of the tree will be cut."
	};
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistedLogs;
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedLogs;
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistedLeaves;
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistedNonDecayLeaves;
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> blacklistedLeaves;
	private final ForgeConfigSpec.ConfigValue<BreakMode> breakMode;
	private final ForgeConfigSpec.ConfigValue<DetectionMode> detectionMode;
	private final ForgeConfigSpec.IntValue maxScanSize;
	private final ForgeConfigSpec.IntValue maxSize;
	private final ForgeConfigSpec.ConfigValue<MaxSizeAction> maxSizeAction;
	private final ForgeConfigSpec.ConfigValue<BreakOrder> breakOrder;
	private final ForgeConfigSpec.IntValue minimumLeavesAroundRequired;
	private final ForgeConfigSpec.BooleanValue treeBreaking;
	private final ForgeConfigSpec.BooleanValue leavesBreaking;
	private final ForgeConfigSpec.IntValue leavesBreakingForceRadius;
	private final ForgeConfigSpec.BooleanValue allowMixedLogs;
	private final ForgeConfigSpec.BooleanValue breakNetherTreeWarts;
	private final ForgeConfigSpec.BooleanValue instantlyBreakWarts;
	private final ForgeConfigSpec.IntValue searchAreaRadius;
	private final ForgeConfigSpec.ConfigValue<List<? extends String>> whitelistedAdjacentBlocks;
	private final ForgeConfigSpec.ConfigValue<AdjacentStopMode> adjacentStopMode;
	
	public TreeConfiguration(ForgeConfigSpec.Builder builder){
		breakMode = builder.comment(DESC_BREAK_MODE)
				.defineEnum("break_mode", BreakMode.INSTANTANEOUS);
		detectionMode = builder.comment(DESC_DETECTION_MODE)
				.defineEnum("detection_mode", DetectionMode.WHOLE_TREE);
		whitelistedLogs = builder.comment(DESC_WHITELISTED_LOGS)
				.defineList("logs_whitelisted", Lists.newArrayList(), Objects::nonNull);
		blacklistedLogs = builder.comment(DESC_BLACKLISTED_LOGS)
				.defineList("logs_blacklisted", Lists.newArrayList(), Objects::nonNull);
		whitelistedLeaves = builder.comment(DESC_WHITELISTED_LEAVES)
				.defineList("leaves_whitelisted", Lists.newArrayList(), Objects::nonNull);
		whitelistedNonDecayLeaves = builder.comment(DESC_WHITELISTED_NON_DECAY_LEAVES)
				.defineList("leaves_non_decay_whitelisted", Lists.newArrayList(), Objects::nonNull);
		blacklistedLeaves = builder.comment(DESC_BLACKLISTED_LEAVES)
				.defineList("leaves_blacklisted", Lists.newArrayList(), Objects::nonNull);
		maxScanSize = builder.comment(DESC_MAX_SCAN_SIZE)
				.defineInRange("max_scan_size", 500, 1, Integer.MAX_VALUE);
		maxSize = builder.comment(DESC_MAX_SIZE)
				.defineInRange("logs_max_count", 100, 1, Integer.MAX_VALUE);
		maxSizeAction = builder.comment(DESC_MAX_SIZE_ACTION)
				.defineEnum("max_size_action", MaxSizeAction.ABORT);
		breakOrder = builder.comment(DESC_BREAK_ORDER)
				.defineEnum("break_order", BreakOrder.FURTHEST_FIRST);
		treeBreaking = builder.comment(DESC_TREE_BREAKING)
				.define("tree_breaking", true);
		leavesBreaking = builder.comment(DESC_LEAVES_BREAKING)
				.define("leaves_breaking", true);
		leavesBreakingForceRadius = builder.comment(DESC_LEAVES_BREAKING_FORCE_RADIUS)
				.defineInRange("leaves_breaking_force_radius", 0, 0, 10);
		minimumLeavesAroundRequired = builder.comment(DESC_MINIMUM_LEAVES_AROUND_REQUIRED)
				.defineInRange("minimum_leaves_around_required", 1, 0, 5);
		allowMixedLogs = builder.comment(DESC_ALLOW_MIXED_LOGS)
				.define("allow_mixed_logs", false);
		breakNetherTreeWarts = builder.comment(DESC_BREAK_NETHER_TREE_WARTS)
				.define("break_nether_tree_warts", true);
		instantlyBreakWarts = builder.comment(DESC_INSTANTLY_BREAK_WARTS)
				.define("instantly_break_warts", false);
		searchAreaRadius = builder.comment(DESC_SEARCH_AROUND_RADIUS)
				.defineInRange("search_around_radius", -1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		whitelistedAdjacentBlocks = builder.comment(DESC_WHITELISTED_ADJACENT_BLOCKS)
				.defineList("adjacent_blocks_whitelisted", Lists.newArrayList(), Objects::nonNull);
		adjacentStopMode = builder.comment(DESC_ADJACENT_STOP_MODE)
				.defineEnum("adjacent_stop_mode", AdjacentStopMode.STOP_ALL);
	}
	
	public AdjacentStopMode getAdjacentStopMode(){
		return adjacentStopMode.get();
	}
	
	public void setAdjacentStopMode(AdjacentStopMode value){
		adjacentStopMode.set(value);
	}
	
	public int getSearchAreaRadius(){
		return searchAreaRadius.get();
	}
	
	public List<String> getBlacklistedLeaves(){
		return (List<String>) blacklistedLeaves.get();
	}
	
	public List<String> getBlacklistedLogs(){
		return (List<String>) blacklistedLogs.get();
	}
	
	public List<String> getWhitelistedLeaves(){
		return (List<String>) whitelistedLeaves.get();
	}
	
	public Collection<Block> getWhitelistedNonDecayLeaveBlocks(){
		return ConfigCache.getInstance().getWhitelistedNonDecayLeaves(this::getWhitelistedNonDecayLeaves);
	}
	
	public List<String> getWhitelistedLogs(){
		return (List<String>) whitelistedLogs.get();
	}
	
	public void setAllowMixedLogs(Boolean value){
		allowMixedLogs.set(value);
	}
	
	public void setBlacklistedLeaves(List<String> value){
		blacklistedLeaves.set(value);
	}
	
	public void setBlacklistedLogs(List<String> value){
		blacklistedLogs.set(value);
	}
	
	public void setBreakMode(BreakMode value){
		breakMode.set(value);
	}
	
	public void setBreakNetherTreeWarts(Boolean value){
		breakNetherTreeWarts.set(value);
	}
	
	public void setInstantlyBreakWarts(Boolean value){
		instantlyBreakWarts.set(value);
	}
	
	public void setDetectionMode(DetectionMode value){
		detectionMode.set(value);
	}
	
	public void setMaxSizeAction(MaxSizeAction value){
		maxSizeAction.set(value);
	}
	
	public void setBreakOrder(BreakOrder value){
		breakOrder.set(value);
	}
	
	public void setLeavesBreaking(Boolean value){
		leavesBreaking.set(value);
	}
	
	public void setLeavesBreakingForceRadius(Integer value){
		leavesBreakingForceRadius.set(value);
	}
	
	public void setMaxSize(Integer value){
		maxSize.set(value);
	}
	
	public void setMaxScanSize(Integer value){
		maxScanSize.set(value);
	}
	
	public void setMinimumLeavesAroundRequired(Integer value){
		minimumLeavesAroundRequired.set(value);
	}
	
	public void setTreeBreaking(Boolean value){
		treeBreaking.set(value);
	}
	
	public void setWhitelistedLeaves(List<String> value){
		whitelistedLeaves.set(value);
	}
	
	public List<String> getWhitelistedNonDecayLeaves(){
		return (List<String>) whitelistedNonDecayLeaves.get();
	}
	
	public void setWhitelistedLogs(List<String> value){
		whitelistedLogs.set(value);
	}
	
	public Collection<Block> getBlacklistedLeaveBlocks(){
		return ConfigCache.getInstance().getBlacklistedLeaves(this::getBlacklistedLeaves);
	}
	
	public Collection<Block> getBlacklistedLogBlocks(){
		return ConfigCache.getInstance().getBlacklistedLogs(this::getBlacklistedLogs);
	}
	
	public boolean isLeavesBreaking(){
		return leavesBreaking.get();
	}
	
	public boolean isTreeBreaking(){
		return treeBreaking.get();
	}
	
	public int getMaxSize(){
		return maxSize.get();
	}
	
	public int getMaxScanSize(){
		return maxScanSize.get();
	}
	
	public int getMinimumLeavesAroundRequired(){
		return minimumLeavesAroundRequired.get();
	}
	
	public Collection<Block> getWhitelistedLeaveBlocks(){
		return ConfigCache.getInstance().getWhitelistedLeaves(this::getWhitelistedLeaves);
	}
	
	public void setWhitelistedNonDecayLeaves(List<String> value){
		whitelistedNonDecayLeaves.set(value);
	}
	
	public Collection<Block> getWhitelistedLogBlocks(){
		return ConfigCache.getInstance().getWhitelistedLogs(this::getWhitelistedLogs);
	}
	
	public int getLeavesBreakingForceRadius(){
		return leavesBreakingForceRadius.get();
	}
	
	public BreakMode getBreakMode(){
		return breakMode.get();
	}
	
	public DetectionMode getDetectionMode(){
		return detectionMode.get();
	}
	
	public boolean isAllowMixedLogs(){
		return allowMixedLogs.get();
	}
	
	public boolean isBreakNetherTreeWarts(){
		return breakNetherTreeWarts.get();
	}
	
	public boolean isInstantlyBreakWarts(){
		return instantlyBreakWarts.get();
	}
	
	public void setSearchAreaRadius(Integer value){
		searchAreaRadius.set(value);
	}
	
	public Collection<Block> getWhitelistedAdjacentBlockBlocks(){
		return ConfigCache.getInstance().getWhitelistedAdjacentBlocks(this::getWhitelistedAdjacentBlocks);
	}
	
	public void setWhitelistedAdjacentBlocks(List<String> value){
		whitelistedAdjacentBlocks.set(value);
	}
	
	public List<String> getWhitelistedAdjacentBlocks(){
		return (List<String>) whitelistedAdjacentBlocks.get();
	}
	
	public MaxSizeAction getMaxSizeAction(){
		return maxSizeAction.get();
	}
	
	public BreakOrder getBreakOrder(){
		return breakOrder.get();
	}
}
