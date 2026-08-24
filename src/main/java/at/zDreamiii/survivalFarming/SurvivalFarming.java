package at.zDreamiii.survivalFarming;

import org.bstats.bukkit.Metrics;
import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class SurvivalFarming extends JavaPlugin implements Listener {

    private static final String USE_PERMISSION = "survivalfarming.use";

    private static final Map<Material, Material> REPLANT_ITEMS = Map.of(
            Material.WHEAT, Material.WHEAT_SEEDS,
            Material.CARROTS, Material.CARROT,
            Material.POTATOES, Material.POTATO,
            Material.BEETROOTS, Material.BEETROOT_SEEDS,
            Material.NETHER_WART, Material.NETHER_WART,
            Material.COCOA, Material.COCOA_BEANS
    );

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        new Metrics(this, 33592);
    }

    @EventHandler(ignoreCancelled = true)
    public void onCropRightClick(PlayerInteractEvent event) {
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK
                || event.getHand() != EquipmentSlot.HAND) {
            return;
        }

        if (!event.getPlayer().hasPermission(USE_PERMISSION)) {
            return;
        }

        Block block = event.getClickedBlock();

        if (block == null || !REPLANT_ITEMS.containsKey(block.getType())) {
            return;
        }

        if (!(block.getBlockData() instanceof Ageable crop)) {
            return;
        }

        if (crop.getAge() != crop.getMaximumAge()) {
            return;
        }

        ItemStack hoe = event.getPlayer()
                .getInventory()
                .getItemInMainHand();

        if (!Tag.ITEMS_HOES.isTagged(hoe.getType())) {
            return;
        }

        List<ItemStack> drops = new ArrayList<>(
                block.getDrops(hoe, event.getPlayer())
        );

        removeReplantItem(drops, REPLANT_ITEMS.get(block.getType()));

        crop.setAge(0);
        block.setBlockData(crop);

        for (ItemStack drop : drops) {
            block.getWorld().dropItemNaturally(
                    block.getLocation().add(0.5, 0.5, 0.5),
                    drop
            );
        }

        event.setCancelled(true);
    }

    private void removeReplantItem(List<ItemStack> drops, Material material) {
        Iterator<ItemStack> iterator = drops.iterator();

        while (iterator.hasNext()) {
            ItemStack drop = iterator.next();

            if (drop.getType() != material) {
                continue;
            }

            if (drop.getAmount() <= 1) {
                iterator.remove();
            } else {
                drop.setAmount(drop.getAmount() - 1);
            }

            return;
        }
    }
}
