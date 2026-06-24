package pac.hy_da.bedblock;

import org.bukkit.ChatColor;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Bed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedListener implements Listener {

    private final Bedblock plugin;

    public BedListener(Bedblock plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() != PlayerBedEnterEvent.BedEnterResult.OK) {
            return;
        }

        Player player = event.getPlayer();
        if (player.hasPermission("bedblock.bypass")) {
            return;
        }

        event.setUseBed(PlayerBedEnterEvent.Result.DENY);

        if (plugin.getConfig().getBoolean("break-bed", true)) {
            breakWholeBed(event.getBed());
        }

        if (plugin.getConfig().getBoolean("effects", true)) {
            Block bed = event.getBed();
            bed.getWorld().playSound(bed.getLocation(), Sound.ENTITY_ZOMBIE_BREAK_WOODEN_DOOR, 1f, 1f);
            bed.getWorld().playEffect(bed.getLocation(), Effect.STEP_SOUND, Material.RED_BED);
        }

        String msg = plugin.getConfig().getString("message", "&cСпать в мире слишком опасно!");
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    // Кровать состоит из двух блоков — ломаем обе половины, чтобы не оставался призрак
    private void breakWholeBed(Block bed) {
        if (bed.getBlockData() instanceof Bed) {
            Bed data = (Bed) bed.getBlockData();
            Block other = bed.getRelative(
                    data.getPart() == Bed.Part.HEAD
                            ? data.getFacing().getOppositeFace()
                            : data.getFacing());
            other.setType(Material.AIR);
        }
        bed.setType(Material.AIR);
    }
}
