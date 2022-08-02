package cn.scii.api;

import cn.scii.block.machineblock.BlockMachine;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;

public interface IPowerTile<F> extends IGeneralTile {

    @Nullable
    ArrayList<BlockMachine> getConnected(World world, BlockPos pos);

    BlockMachine getBlock();

}
