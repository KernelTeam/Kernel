/**
 * Project : Kernel
 * Package : net.siriuser.kernel.core
 * Created : 2014/01/25 - 0:04:54
 */
package net.siriuser.kernel.core;

import net.syamn.utils.LogUtil;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SiriuseR
 *
 */
public class Kernel extends JavaPlugin {

    private static Kernel instance;

    /**
     * @author SiriuseR
     */
    @Override
    public void onEnable() {
        LogUtil.init(this);

        PluginManager pm = getServer().getPluginManager();

        PluginDescriptionFile pdfFile = this.getDescription();
        LogUtil.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!");
    }

    /**
     * @author SiriuseR
     */
    @Override
    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        PluginDescriptionFile pdfFile = this.getDescription();
        LogUtil.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " is disabled!");
    }

    /**
     * @author SiriuseR
     * @return Kernel Instance
     */
    public static Kernel getInstance() {
        return instance;
    }
}
