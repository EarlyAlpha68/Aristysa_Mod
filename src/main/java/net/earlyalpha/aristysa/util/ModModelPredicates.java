package net.earlyalpha.aristysa.util;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.item.ModItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

public class ModModelPredicates {
        public static void registerModelPredicates() {
            ModelPredicateProviderRegistry.register(ModItems.CRIMSON_LACE, new Identifier("aristysa", "usage_ticks"),
                    (stack, world, entity, seed) -> {
                        NbtCompound nbt = stack.getNbt();
                        if (nbt != null) {
                            int usageTicks = nbt.getInt("UsageTicks");
                            if (usageTicks >= 30) {
                                return 1.0F;
                            } else if (usageTicks >= 20) {
                                return 0.67F;
                            } else if (usageTicks >= 10) {
                                return 0.33F;
                            } else {
                                return 0.0F;
                            }
                        }
                        return 0.0F;
                    }
            );
        }
    }

