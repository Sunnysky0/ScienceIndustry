package cn.scii.block;

import cn.scii.itemgroup.SIItemGroup;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;



public class BlockBase extends Block {
    public BlockBase(Material materialIn,String name) {
        super(materialIn);
        this.setRegistryName(name);
        this.setTranslationKey(name);
        this.setCreativeTab(SIItemGroup.MainGroup);
    }


    public boolean hasTile(){
        return false;
    }
}
