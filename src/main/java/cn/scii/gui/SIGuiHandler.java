package cn.scii.gui;

import cn.scii.block.machineblock.tile.TilePowerGenerator;
import cn.scii.gui.container.ContainerGenerator;
import cn.scii.gui.impl.GuiGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.IGuiHandler;

import javax.annotation.Nullable;

public class SIGuiHandler implements IGuiHandler {
    public static final int GENERATOR = 0;

    @Nullable
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GENERATOR:
                return new ContainerGenerator(player.inventory, (TilePowerGenerator) world.getTileEntity(new BlockPos(x, y, z)));
            default:
                return null;
        }
    }


    @Nullable
    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID) {
            case GENERATOR:
                return new GuiGenerator((Container) getServerGuiElement(ID, player, world, x, y, z), player.inventory);
            default:
                return null;
        }
    }
}
