package cn.scii.block.machineblock.impl;

import cn.scii.block.machineblock.BlockMachine;
import cn.scii.block.machineblock.tile.TileClickCounter;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class MachineClickCounter extends BlockMachine<TileClickCounter> {

    public MachineClickCounter() {
        super("click_counter");
    }

    @Override
    @Deprecated
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    @Deprecated
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing side, float hitX, float hitY, float hitZ) {
        if (!world.isRemote) {
            TileClickCounter tile = getTileEntity(world, pos);
            if (side == EnumFacing.DOWN) {
                tile.decrementCount();
            } else if (side == EnumFacing.UP) {
                tile.incrementCount();
            }
            player.sendMessage(new TextComponentString("Count: " + tile.getCount()));
        }
        return true;
    }

    @Override
    public Class<TileClickCounter> getTileEntityClass() {
        return TileClickCounter.class;
    }

    @Nullable
    @Override
    public TileClickCounter createTileEntity(World world, IBlockState state) {
        return new TileClickCounter();
    }
}
