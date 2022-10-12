package ca.raindoggames.quickpicktool.controls;

import ca.raindoggames.quickpickmoddedtool.QuickPickModdedTool;
import ca.raindoggames.quickpickmoddedtool.blocktags.ToolBlockTags;
import ca.raindoggames.quickpickmoddedtool.inventory.PlayerInventoryHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.ClickInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class MouseEventHandler {
	static PlayerInventoryHelper helper = new PlayerInventoryHelper();
	
	@SubscribeEvent
	public static void onEvent(ClickInputEvent event) {
		KeyMapping[] keyBindings = QuickPickModdedTool.keyBindings;
		
		// break
		if (keyBindings[0].isDown()) {
			if (event.isPickBlock()) {
				Minecraft minecraft = Minecraft.getInstance();
				LocalPlayer player = minecraft.player;
				HitResult hitResult = minecraft.hitResult;
				if (hitResult != null && hitResult.getType() != HitResult.Type.MISS) {
					HitResult.Type hitresult$type = hitResult.getType();
					if (hitresult$type == HitResult.Type.BLOCK) {
			            BlockPos blockpos = ((BlockHitResult)hitResult).getBlockPos();
			            BlockState state = minecraft.level.getBlockState(blockpos);
			            if (state.isAir()) {
			            	event.setCanceled(true);
			            	return;
			            }
			            if (state.is(ToolBlockTags.SHEAR_MINEABLE)) {
							helper.selectTool(player.getInventory(), "shears", minecraft.gameMode, false);
						} else if (state.is(ToolBlockTags.SILK_TOUCH_MINEABLE)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, false);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_pickaxe", minecraft.gameMode, false);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, false);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_shovel", minecraft.gameMode, false);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, false);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_pickaxe", minecraft.gameMode, false);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_AXE)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, false);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_axe", minecraft.gameMode, false);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_HOE)) {
							helper.selectTool(player.getInventory(), "_hoe", minecraft.gameMode, false);
						}
		            			            
					}
					
				}
				event.setCanceled(true);
			}
		}
		
		// save
		if (keyBindings[1].isDown()) {
			if (event.isPickBlock()) {
				Minecraft minecraft = Minecraft.getInstance();
				LocalPlayer player = minecraft.player;
				HitResult hitResult = minecraft.hitResult;
				if (hitResult != null && hitResult.getType() != HitResult.Type.MISS) {
					HitResult.Type hitresult$type = hitResult.getType();
					if (hitresult$type == HitResult.Type.BLOCK) {
			            BlockPos blockpos = ((BlockHitResult)hitResult).getBlockPos();
			            BlockState state = minecraft.level.getBlockState(blockpos);
			            if (state.isAir()) {
			            	event.setCanceled(true);
			            	return;
			            }
			            if (state.is(ToolBlockTags.SHEAR_MINEABLE)) {
							helper.selectTool(player.getInventory(), "shears", minecraft.gameMode, false);
						} else if (state.is(ToolBlockTags.SILK_TOUCH_MINEABLE)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, true);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_pickaxe", minecraft.gameMode, true);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, true);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_shovel", minecraft.gameMode, true);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_PICKAXE)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, true);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_pickaxe", minecraft.gameMode, true);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_AXE)) {
							boolean paxelSwap = helper.selectTool(player.getInventory(), "_paxel", minecraft.gameMode, true);
							if (!paxelSwap) {
								helper.selectTool(player.getInventory(), "_axe", minecraft.gameMode, true);
							}
						} else if (state.is(BlockTags.MINEABLE_WITH_HOE)) {
							helper.selectTool(player.getInventory(), "_hoe", minecraft.gameMode, true);
						}
		            			            
					}
					
				}
				event.setCanceled(true);
			}
		}
		
		// pickarang
		if (keyBindings[2].isDown()) {
			if (event.isPickBlock()) {
				Minecraft minecraft = Minecraft.getInstance();
				LocalPlayer player = minecraft.player;
				helper.selectPickarang(player.getInventory(), minecraft.gameMode);
			}
			event.setCanceled(true);
		}
		
		// atomic disassembler
		if (keyBindings[3].isDown()) {
			if (event.isPickBlock()) {
				Minecraft minecraft = Minecraft.getInstance();
				LocalPlayer player = minecraft.player;
				helper.selectAtomicDisassembler(player.getInventory(), minecraft.gameMode);
			}
			event.setCanceled(true);
		}
	}
}
