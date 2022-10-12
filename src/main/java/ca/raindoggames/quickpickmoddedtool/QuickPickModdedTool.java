package ca.raindoggames.quickpickmoddedtool;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("quickpickmoddedtool")
public class QuickPickModdedTool
{
    // Directly reference a slf4j logger
	public static final String MODID = "quickpickmoddedtool";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
    public static KeyMapping[] keyBindings = {};

    public QuickPickModdedTool()
    {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
    }
}
