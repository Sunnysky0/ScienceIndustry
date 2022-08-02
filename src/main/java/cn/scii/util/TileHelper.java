package cn.scii.util;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.fml.common.registry.GameRegistry;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class TileHelper {

    public ArrayList<TileEntity> getNearTiles(@Nonnull IBlockAccess world, @Nonnull BlockPos pos){
        ArrayList<TileEntity> tiles = new ArrayList<>();
        tiles.add(world.getTileEntity(pos.add(1,0,0)));
        tiles.add(world.getTileEntity(pos.add(0,1,0)));
        tiles.add(world.getTileEntity(pos.add(0,0,1)));
        tiles.add(world.getTileEntity(pos.add(-1,0,0)));
        tiles.add(world.getTileEntity(pos.add(0,-1,0)));
        tiles.add(world.getTileEntity(pos.add(0,0,-1)));

        return tiles;
    }

    public static int getItemBurnTime(ItemStack fuel)

    {

        if(fuel.isEmpty()) return 0;

        else

        {

            Item item = fuel.getItem();



            if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.AIR)

           {

               Block block = Block.getBlockFromItem(item);



                if (block == Blocks.WOODEN_SLAB) return 150;

               if (block.getDefaultState().getMaterial() == Material.WOOD) return 300;

               if (block == Blocks.COAL_BLOCK) return 16000;

           }

            if (item instanceof ItemTool && "WOOD".equals(((ItemTool)item).getToolMaterialName())) return 200;

            if (item instanceof ItemSword && "WOOD".equals(((ItemSword)item).getToolMaterialName())) return 200;

            if (item instanceof ItemHoe && "WOOD".equals(((ItemHoe)item).getMaterialName())) return 200;

            if (item == Items.STICK) return 100;

            if (item == Items.COAL) return 1600;

            if (item == Items.LAVA_BUCKET) return 20000;

            return GameRegistry.getFuelValue(fuel);

        }

    }

    public static boolean isItemFuel(ItemStack fuel)

    {

        return getItemBurnTime(fuel) > 0;

    }

}
