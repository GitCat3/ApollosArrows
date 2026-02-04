package me.give_me_moneyz.apollosarrows.events;

import me.give_me_moneyz.apollosarrows.ApollosArrows;
import me.give_me_moneyz.apollosarrows.inventory.FletchingTableMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ApollosArrows.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class RightClickEvent {
    @SubscribeEvent
    public static void onBlockRightClick(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();

        if (level.getBlockState(pos).is(Blocks.FLETCHING_TABLE)) {
            if (!level.isClientSide) {
                Player player = event.getEntity();
                player.openMenu(new SimpleMenuProvider(
                    (containerId, playerInventory, p) -> new FletchingTableMenu(containerId, playerInventory, ContainerLevelAccess.create(level, pos)),
                    Component.translatable("menu.apollosarrows.fletching_table")
                ));
            }

            event.setCanceled(true);
            event.setCancellationResult(InteractionResult.SUCCESS);
        }
    }
}
