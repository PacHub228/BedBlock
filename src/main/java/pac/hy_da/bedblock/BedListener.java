package pac.hy_da.bedblock;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;

public class BedListener implements Listener {

    @EventHandler
    public void onBedEnter(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() == PlayerBedEnterEvent.BedEnterResult.OK) {
            event.setUseBed(PlayerBedEnterEvent.Result.DENY);
            
            Player player = event.getPlayer();
            Block bed = event.getBed();
            
            // Ломаем кровать
            bed.setType(Material.AIR);
            
            // Сообщение
            player.sendMessage(ChatColor.RED + "Спать в мире слишком опасно!");
        }
    }
}
