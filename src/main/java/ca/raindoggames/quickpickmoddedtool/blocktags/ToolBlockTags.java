package ca.raindoggames.quickpickmoddedtool.blocktags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ToolBlockTags {
	public static final TagKey<Block> SILK_TOUCH_MINEABLE = TagKey.create(Registries.BLOCK, new ResourceLocation("quickpicktool", "mineable/silk_touch"));
	public static final TagKey<Block> SHEAR_MINEABLE = TagKey.create(Registries.BLOCK, new ResourceLocation("quickpicktool", "mineable/shear"));
}
