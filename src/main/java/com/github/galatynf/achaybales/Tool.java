package com.github.galatynf.achaybales;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Tool {
    public static int countBlocksDownwards(World world, BlockPos blockPosition) {
        BlockPos posIterator = blockPosition;
        int result = 0;

        while (world.getBlockState(posIterator).getBlock() == Blocks.AIR) {
            posIterator = posIterator.down();
            result ++;
        }
        return result;
    }
}
