package com.github.galatynf.achaybales.mixin;

import com.github.galatynf.achaybales.Achaybales;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HayBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HayBlock.class)
public class HayBlockMixin extends Block {
    public HayBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("INVOKE"), method = "onLandedUpon", cancellable = true)
    private void onLandedUpon(World world, BlockPos pos, Entity entity, float distance, CallbackInfo info) {
        entity.handleFallDamage(distance, 0.0F);
        if(entity instanceof PlayerEntity) {
            if (distance >= 15) {
                BlockPos posPlayer = entity.getBlockPos();
                world.playSound(posPlayer.getX(), posPlayer.getY(), posPlayer.getZ(), Achaybales.aclandevent, SoundCategory.AMBIENT, 1F, 1F, true);
            }
            if (!((PlayerEntity) entity).isCreative() && distance >= 30) {
                BlockPos posIterator = pos;
                for (int i = 0; i < 2; i++) {
                    for (int j = -1; j < 2; j++) {
                        for (int k = -1; k < 2; k++) {
                            BlockPos newPos = new BlockPos(j, 0, k);
                            if (world.getBlockState(posIterator.add(newPos)).isOf(Blocks.HAY_BLOCK)) {
                                world.setBlockState(posIterator.add(newPos), Achaybales.untiedhay.getDefaultState());
                            }
                        }
                    }
                    posIterator = posIterator.down();
                }
            }
        }
        info.cancel();
    }
}
