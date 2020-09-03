package com.github.galatynf.achaybales.mixin;

import com.github.galatynf.achaybales.Achaybales;
import com.github.galatynf.achaybales.Tool;
import jdk.vm.ci.code.site.Call;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.sound.Sound;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.rmi.StubNotFoundException;

import static java.lang.Math.abs;

@Mixin(PlayerEntity.class)
public abstract class JumpingMixin extends LivingEntity {
    @Unique
    protected boolean playTheSound = false;

    protected JumpingMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Shadow
    public abstract void playSound(SoundEvent sound, float volume, float pitch);

    @Shadow
    public abstract boolean isCreative();

    @Shadow
    protected boolean isSubmergedInWater;

    /*
        Just resets the sound when on ground so it doesn't play each tick when falling
     */
    @Inject(at = @At("INVOKE"), method = "tick")
    private void resetPlaysound(CallbackInfo ci) {
        if(onGround)
            playTheSound = true;
    }

    @Inject(at = @At("INVOKE"), method = "tick")
    private void playEagleSound(CallbackInfo info) {
        if (!onGround &&
                !isCreative() &&
                !isSubmergedInWater &&
                playTheSound &&
                Tool.countBlocksDownwards(world, getBlockPos()) >= 30) {

            double horizontalPlayerSpeed = abs(getVelocity().x) + abs(getVelocity().z);
            double verticalPlayerSpeed = getVelocity().y;
            BlockPos posIterator = getBlockPos();
            boolean thereIsHayWater = false;

            /*
                Checks if there is hay or water below the player
             */
            while (world.getBlockState(posIterator).getBlock() == Blocks.AIR) {
                posIterator = posIterator.down();
            }
            if (world.getBlockState(posIterator).getBlock() == Blocks.HAY_BLOCK ||
                    world.getBlockState(posIterator).getBlock() == Blocks.WATER) {
                thereIsHayWater = true;
            }

            /*
                Check if the player is moving downwards with speed
             */
            if (thereIsHayWater &&
                    horizontalPlayerSpeed <= 0.15F &&
                    verticalPlayerSpeed >= -1F) {
                playSound(Achaybales.eagleevent, 1.0F, 1.0F);
                playTheSound = false;
            }
        }
    }
}
