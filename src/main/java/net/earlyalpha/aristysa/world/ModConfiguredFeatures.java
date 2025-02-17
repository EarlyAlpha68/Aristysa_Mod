package net.earlyalpha.aristysa.world;

import net.earlyalpha.aristysa.Aristysa;
import net.earlyalpha.aristysa.block.ModBlocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.structure.rule.TagMatchRuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.List;

public class ModConfiguredFeatures {
public static final RegistryKey<ConfiguredFeature<?, ?>> LEAD_ORE_KEY = registerKey("lead_ore");
public static final RegistryKey<ConfiguredFeature<?, ?>> ALUMINUM_ORE_KEY = registerKey("aluminum_ore");

public static void boostrap(Registerable<ConfiguredFeature<?, ?>> context) {
    RuleTest stoneReplacables = new TagMatchRuleTest(BlockTags.STONE_ORE_REPLACEABLES);
    RuleTest deepslateReplacables = new TagMatchRuleTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES);

    List<OreFeatureConfig.Target> overworldLeadOres =
            List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.LEAD_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_LEAD_ORE.getDefaultState()));

    List<OreFeatureConfig.Target> overworldAluminumOres =
            List.of(OreFeatureConfig.createTarget(stoneReplacables, ModBlocks.ALUMINUM_ORE.getDefaultState()),
                    OreFeatureConfig.createTarget(deepslateReplacables, ModBlocks.DEEPSLATE_ALUMINUM_ORE.getDefaultState()));

    register(context,LEAD_ORE_KEY,Feature.ORE,new OreFeatureConfig(overworldLeadOres,3));
    register(context,ALUMINUM_ORE_KEY,Feature.ORE,new OreFeatureConfig(overworldAluminumOres,3));
}

    public static RegistryKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.CONFIGURED_FEATURE, new Identifier(Aristysa.MOD_ID, name));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>> void register(Registerable<ConfiguredFeature<?, ?>> context,
                                                                                   RegistryKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
