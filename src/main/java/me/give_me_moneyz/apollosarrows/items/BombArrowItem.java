package me.give_me_moneyz.apollosarrows.items;

import me.give_me_moneyz.apollosarrows.world.entity.ModEntityType;
import me.give_me_moneyz.apollosarrows.world.entity.projectile.BombArrow;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class BombArrowItem extends ArrowItem {
    public final float damage;
    public BombArrowItem(Properties pProperties, float damage) {
        super(pProperties);
        this.damage = damage;
    }

    @Override
    public AbstractArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        return new BombArrow(ModEntityType.BOMB_ARROW.get(), pLevel);
    }

    @Override
    public boolean isInfinite(ItemStack stack, ItemStack bow, net.minecraft.world.entity.player.Player player) {
        int enchant = net.minecraft.world.item.enchantment.EnchantmentHelper.getItemEnchantmentLevel(net.minecraft.world.item.enchantment.Enchantments.INFINITY_ARROWS, bow);
        return enchant <= 0 ? false : this.getClass() == BombArrowItem.class;
    }
}
