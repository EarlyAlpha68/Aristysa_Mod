package net.earlyalpha.aristysa.datagen.tags;

import net.earlyalpha.aristysa.Aristysa;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> LEAD_ORE =
                createCommonTag("lead_ores");
        public static final TagKey<Block> ALUMINUM_ORE =
                createCommonTag("aluminum_ores");
        public static final TagKey<Block> LEAD_BLOCKS =
                createCommonTag("lead_blocks");


        private static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, new Identifier(Aristysa.MOD_ID, name));
        }
        private static TagKey<Block> createCommonTag(String name) {
                return TagKey.of(RegistryKeys.BLOCK, new Identifier("c", name));
            }
    }


    public static class Items {
        public static final TagKey<Item> LEAD_INGOTS =
                createCommonTag("lead_ingots");
        public static final TagKey<Item> ALUMINUM_INGOTS =
                createCommonTag("aluminum_ingots");
        public static final TagKey<Item> ALUMINUM_PLATES =
                createCommonTag("aluminum_plates");
        public static final TagKey<Item> LEAD_PLATES =
                createCommonTag("lead_plates");

        private static TagKey<Item> createTag(String name){
            return TagKey.of(RegistryKeys.ITEM, new Identifier(Aristysa.MOD_ID, name));
        }
        private static TagKey<Item> createCommonTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, new Identifier("c", name));
        }
    }
}
