package me.give_me_moneyz.apollosarrows;

import com.mojang.logging.LogUtils;
import me.give_me_moneyz.apollosarrows.registry.GlobalLootModifiers;
import me.give_me_moneyz.apollosarrows.registry.ModEntityType;
import me.give_me_moneyz.apollosarrows.registry.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ApollosArrows.MODID)
public class ApollosArrows {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "apollosarrows";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public ApollosArrows() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModEntityType.ENTITY_TYPES.register(modEventBus);
        GlobalLootModifiers.GLM.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }
}
