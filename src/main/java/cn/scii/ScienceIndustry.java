package cn.scii;

import cn.scii.block.BlocksCollection;
import cn.scii.block.machineblock.tile.TilePowerGenerator;
import cn.scii.gui.SIGuiHandler;
import cn.scii.proxy.CommonProxy;
import cn.scii.render.RenderPowerGenerator;
import cn.scii.util.TileHelper;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import org.apache.logging.log4j.Logger;

@Mod(
        modid = ScienceIndustry.MOD_ID,
        name = ScienceIndustry.MOD_NAME,
        version = ScienceIndustry.VERSION
)
public class ScienceIndustry {

    public static final String MOD_ID = "scii";
    public static final String MOD_NAME = "ScienceIndustry";
    public static final String VERSION = "1.0-SNAPSHOT";
    public static Logger logger;
    public static TileHelper tileHelper;

    @SidedProxy(clientSide = "cn.scii.proxy.ClientProxy",serverSide = "cn.scii.proxy.ServerProxy")
    public static CommonProxy proxy;

    /**
     * This is the instance of your mod as created by Forge. It will never be null.
     */
    @Mod.Instance(MOD_ID)
    public static ScienceIndustry INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        tileHelper = new TileHelper();
        proxy.preInit(event);

        // OBJLoader.INSTANCE.addDomain(MOD_ID);

        NetworkRegistry.INSTANCE.registerGuiHandler(this, new SIGuiHandler());

        //ClientRegistry.bindTileEntitySpecialRenderer(TilePowerGenerator.class,new RenderPowerGenerator());
    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        proxy.postInit(event);
    }

}
