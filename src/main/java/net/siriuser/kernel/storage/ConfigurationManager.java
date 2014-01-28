/**
 * Project : Kernel
 * Package : net.siriuser.kernel.storage
 * Created : 2014/01/28 - 18:24:54
 */
package net.siriuser.kernel.storage;

import java.io.File;

import net.siriuser.kernel.core.Kernel;
import net.syamn.utils.LogUtil;
import net.syamn.utils.file.FileStructure;

import org.bukkit.configuration.file.FileConfiguration;

/**
 * @author SiriuseR
 *
 */
public class ConfigurationManager {
    private final Kernel plugin;
    private final int lastVersion = 1;

    private FileConfiguration config;
    private File pluginDir;

    public ConfigurationManager(final Kernel plugin) {
        this.plugin = plugin;
        this.pluginDir = plugin.getDataFolder();
    }

    public void loadConfig(final boolean initiaLoad) throws Exception {
        FileStructure.createDir(pluginDir);

        File file = new File(pluginDir, "config.yml");
        if (!file.exists()) {
            FileStructure.extractResource("/config.yml", pluginDir, false, false, plugin);
            LogUtil.info("Config.yml is not found! Created default config.yml!");
        }

        plugin.reloadConfig();
        config = plugin.getConfig();

        checkVer(config.getInt("ConfigVersion"));
    }

    private void checkVer(final int ver) {
        if (ver < lastVersion) {
            final String destName = "oldconfig-v" + ver + ".yml";
            String srcPath = new File(pluginDir, "config.yml").getPath();
            String destPath = new File(pluginDir, destName).getPath();
            try {
                FileStructure.copyTransfer(srcPath, destPath);
                LogUtil.info("Copyed old config.yml to " + destName + "!");
            } catch (Exception ex) {
                LogUtil.warning("Faild to copy old config.yml!");
            }

            FileStructure.extractResource("/config.yml", pluginDir, true, false, plugin);

            plugin.reloadConfig();
            config = plugin.getConfig();

            //TODO: 古いConfigの項目を新しいConfigに反映させる。
        }
    }
}
