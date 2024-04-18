package me.give_me_moneyz.apollosarrows.items;

import me.give_me_moneyz.apollosarrows.entities.IncendiaryArrowEntity;
import me.give_me_moneyz.apollosarrows.registry.ModEntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class IncendiaryArrowItem extends ArrowItem {
    public IncendiaryArrowItem(Properties props) {
        super(props);
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
        return new IncendiaryArrowEntity(ModEntityType.INCENDIARY_ARROW.get(), shooter, world);
    }
}