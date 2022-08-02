package cn.scii.proxy;

import cn.scii.block.BlocksCollection;
import cn.scii.config.Config;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.io.File;
import java.util.Objects;

@Mod.EventBusSubscriber
public class CommonProxy {

    // Config instance
    public static Configuration config;

    public void preInit(FMLPreInitializationEvent e) {
        new BlocksCollection();
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "scii.cfg"));
        Config.readConfig();
        loadTiles(e);
    }

    public void loadTiles(FMLPreInitializationEvent event)
    {
        BlocksCollection.tiles.forEach(e -> registerTileEntity(e.getTileEntityClass(),e.getRegistryName().toString()));
    }

    @SuppressWarnings("deprecation")
    public static String localize(String unlocalized, Object... args) {
        return I18n.translateToLocalFormatted(unlocalized, args);
    }


    @SuppressWarnings("deprecation")
    public void registerTileEntity(Class<? extends TileEntity> tileEntityClass, String id)
    {
        GameRegistry.registerTileEntity(tileEntityClass, id);
    }

    public void init(FMLInitializationEvent e) {
    }

    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        BlocksCollection.blocks.forEach(blockBase -> event.getRegistry().register(blockBase));
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        BlocksCollection.blocks.forEach(e -> event.getRegistry().register(new ItemBlock(e).setRegistryName(Objects.requireNonNull(e.getRegistryName()))));
    }
}
