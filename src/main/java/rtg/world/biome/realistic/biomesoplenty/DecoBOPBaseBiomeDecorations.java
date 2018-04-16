package rtg.world.biome.realistic.biomesoplenty;

import java.util.Collection;
import java.util.Random;

import net.minecraft.util.math.ChunkPos;

import biomesoplenty.api.generation.GeneratorStage;
import com.google.common.collect.Sets;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBaseBiomeDecorations;

/**
 *
 * @author WhichOnesPink
 */
public class DecoBOPBaseBiomeDecorations extends DecoBaseBiomeDecorations {

    private Collection<GeneratorStage> stagesToRemove = Sets.newHashSet();

    public DecoBOPBaseBiomeDecorations() {
        super();
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed && biome instanceof IRealisticBOPBiome) {

            // skip if already decorated (@author Zeno410, lifted from DecoSingleBiomeDecorations)
            ChunkPos position = new ChunkPos(worldX,worldZ);
            if (rtgWorld.decoratedChunks().contains(position)) return;
            rtgWorld.decoratedChunks().add(position);

            IRealisticBOPBiome bopBiome = (IRealisticBOPBiome) biome;

            bopBiome.addGenerators();
            super.generate(biome, rtgWorld, rand, worldX, worldZ, strength, river, hasPlacedVillageBlocks);
            bopBiome.removeGenerators();
        }
    }

    public void addStageForRemoval(GeneratorStage stage) {
        this.stagesToRemove.add(stage);
    }

    public Collection<GeneratorStage> stagesToRemove() {
        return this.stagesToRemove;
    }
}