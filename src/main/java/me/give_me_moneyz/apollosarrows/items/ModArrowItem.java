package me.give_me_moneyz.apollosarrows.items;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.function.BiFunction;

public class ModArrowItem extends ArrowItem {
    private final BiFunction<Level, LivingEntity, AbstractArrow> arrowFactory;

    public ModArrowItem(Properties props, BiFunction<Level, LivingEntity, AbstractArrow> arrowFactory) {
        super(props);
        this.arrowFactory = arrowFactory;
    }

    @Override
    public AbstractArrow createArrow(Level world, ItemStack ammoStack, LivingEntity shooter) {
        return arrowFactory.apply(world, shooter);
    }
}
