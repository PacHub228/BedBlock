package pac.hy_da.bedblock;

import org.bukkit.plugin.java.JavaPlugin;

public final class Bedblock extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new BedListener(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
