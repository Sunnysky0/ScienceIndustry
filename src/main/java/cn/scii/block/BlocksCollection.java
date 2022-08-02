package cn.scii.block;

import cn.scii.block.machineblock.BlockMachine;
import cn.scii.block.machineblock.impl.MachineClickCounter;
import cn.scii.block.machineblock.impl.MachineItemHeap;
import cn.scii.block.machineblock.impl.MachinePowerGenerator;
import net.minecraft.block.material.Material;

import java.util.ArrayList;

public class BlocksCollection {

    public static ArrayList<BlockBase> blocks = new ArrayList<>();
    public static ArrayList<BlockMachine> tiles = new ArrayList<>();

    public BlocksCollection() {
        //Normal blocks; ores, etc.
        BlocksCollection.blocks.add(new BlockBase(Material.IRON,"first_of_all"));
        //Blocks with tile entities; machines, etc.
        BlocksCollection.blocks.add(new MachineClickCounter());
        BlocksCollection.blocks.add(new MachineItemHeap());
        BlocksCollection.blocks.add(new MachinePowerGenerator());

        for (BlockBase e:
             blocks) {
            if(e.hasTile())
                tiles.add((BlockMachine) e);
        }

    }
}
