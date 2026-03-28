package me.johnadept;

import me.johnadept.config.AutoAttackConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
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

        String idStr
