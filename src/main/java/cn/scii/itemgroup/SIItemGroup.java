package cn.scii.itemgroup;

import cn.scii.ScienceIndustry;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public class SIItemGroup extends CreativeTabs {
    public static final SIItemGroup MainGroup = new SIItemGroup(ScienceIndustry.MOD_ID,new ItemStack(Blocks.BEDROCK));

    private ItemStack Icon;

    public SIItemGroup(String p_i47634_1_, ItemStack icon) {
        super(p_i47634_1_);
        Icon = icon;
    }


    @Override
    public ItemStack createIcon() {
        return Icon;
    }

    @Override
    public boolean hasSearchBar() {
        return true;
    }
}
