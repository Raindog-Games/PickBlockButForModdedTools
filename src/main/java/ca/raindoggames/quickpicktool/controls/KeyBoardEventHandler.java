package ca.raindoggames.quickpicktool.controls;

import ca.raindoggames.quickpickmoddedtool.QuickPickModdedTool;
import ca.raindoggames.quickpickmoddedtool.inventory.PlayerInventoryHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(Dist.CLIENT)
public class KeyBoardEventHandler {
	static PlayerInventoryHelper helper = new PlayerInventoryHelper();
	
	@SubscribeEvent
	public static void onEvent(KeyInputEvent event) {
		KeyMapping[] keyBindings = QuickPickModdedTool.keyBindings;
		
		// pickarang
		if (keyBindings[2].isDown()) {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			helper.selectPickarang(player.getInventory(), minecraft.gameMode);
		}
		
		// atomic disassembler
		if (keyBindings[3].isDown()) {
			Minecraft minecraft = Minecraft.getInstance();
			LocalPlayer player = minecraft.player;
			helper.selectAtomicDisassembler(player.getInventory(), minecraft.gameMode);
		}
	}
}
