package ca.raindoggames.quickpicktool.controls;

import static ca.raindoggames.quickpickmoddedtool.QuickPickModdedTool.LOGGER;

import com.mojang.blaze3d.platform.InputConstants;

import ca.raindoggames.quickpickmoddedtool.QuickPickModdedTool;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyRegisterEventHandler {
	@SubscribeEvent
	public static void registerKeys(RegisterKeyMappingsEvent e) {
    	KeyMapping[] keyBindings = QuickPickModdedTool.keyBindings;
    	LOGGER.info("Registering keys");
    	// Save keyBindings 
    	keyBindings = new KeyMapping[4];
        keyBindings[0] = new KeyMapping("key.quickpickmoddedtool.break", InputConstants.KEY_B, "category.quickpickmoddedtool.utils");
        keyBindings[1] = new KeyMapping("key.quickpickmoddedtool.save", InputConstants.KEY_V, "category.quickpickmoddedtool.utils"); 
        keyBindings[2] = new KeyMapping("key.quickpickmoddedtool.pickarang", InputConstants.KEY_Y, "category.quickpickmoddedtool.utils");
        keyBindings[3] = new KeyMapping("key.quickpickmoddedtool.atomicdisassembler", InputConstants.KEY_X, "category.quickpickmoddedtool.utils");
    	for (int i = 0; i < keyBindings.length; i++) {
    		e.register(keyBindings[i]);
    	}
    	QuickPickModdedTool.keyBindings = keyBindings;
  	}
}
