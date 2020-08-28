package com.github.galatynf.achaybales.mixin;

import jdk.internal.jline.internal.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
abstract class DetectionMixin extends Entity{

    public DetectionMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Shadow public abstract boolean isHolding(Item item);
/*
    @Inject(at = @At("INVOKE"), method = "getAttackDistanceScalingFactor")
    private void detection(Entity entity, CallbackInfo ci){
        if(isHolding(Items.ZOMBIE_HEAD)){

        }
        double d = 1.0D;
        if (this.isSneaky()) {
            d *= 0.8D;
        }

        if (this.isInvisible()) {
            float f = getArmorVisibility();
            if (f < 0.1F) {
                f = 0.1F;
            }

            d *= 0.7D * (double)f;
        }

        if (entity != null) {
            ItemStack itemStack = this.getEquippedStack(EquipmentSlot.HEAD);
            Item item = itemStack.getItem();
            EntityType<?> entityType = entity.getType();
            if (entityType == EntityType.SKELETON && item == Items.SKELETON_SKULL || entityType == EntityType.ZOMBIE && item == Items.ZOMBIE_HEAD || entityType == EntityType.CREEPER && item == Items.CREEPER_HEAD) {
                d *= 0.5D;
            }
        }

        return d;
        ci.cancel;
    }*/
}
