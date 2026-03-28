package me.johnadept;

import me.johnadept.config.AutoAttackConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class Attacker {

    public static void tryAttack(Minecraft mc, AutoAttackConfig config) {
        LocalPlayer player = mc.player;
        if (player == null) return;

        if (player.getAttackStrengthScale(0.0f) < 1.0f) return;

        HitResult hit = mc.hitResult;
        if (hit == null || hit.getType() != HitResult.Type.ENTITY) return;

        EntityHitResult entityHit = (EntityHitResult) hit;
        Entity entity = entityHit.getEntity();

        String idStr = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();

        if (config.entityBlacklist.contains(idStr)) return;

        boolean whitelisted = config.entityWhitelist.contains(idStr);

        if (!whitelisted) {
            if (!(entity instanceof LivingEntity) && !config.attackNonLiving) return;
            if (entity instanceof Player) return;

            if (config.protectTamedMobs && entity instanceof OwnableEntity ownable) {
                if (player.getUUID().equals(ownable.ownerUUID())) return;
            }

            if (!(entity instanceof Enemy) && !config.attackNonHostile) return;
        }

        if (isShielding(player)) return;

        ItemStack mainHand = player.getItemInHand(InteractionHand.MAIN_HAND);
        if (config.disableOnLowDurability && mainHand.isDamageableItem()) {
            int durability = mainHand.getMaxDamage() - mainHand.getDamageValue();
            if (durability <= config.durabilityThreshold) return;
        }

        mc.gameMode.attack(player, entity);
        player.swing(InteractionHand.MAIN_HAND);
    }

    private static boolean isShielding(LocalPlayer player) {
        return player.isUsingItem() && player.getUseItem().getItem() instanceof ShieldItem;
    }
}
