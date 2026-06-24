package pac.hy_da.bedblock;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

public final class Bedblock extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BedListener(this), this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("bedblock")) {
            if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
                if (!sender.hasPermission("bedblock.reload")) {
                    sender.sendMessage(ChatColor.RED + "Недостаточно прав.");
                    return true;
                }
                reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "BedBlock: конфиг перезагружен.");
                return true;
            }
            sender.sendMessage(ChatColor.YELLOW + "Использование: /bedblock reload");
            return true;
        }
        return false;
    }
}
