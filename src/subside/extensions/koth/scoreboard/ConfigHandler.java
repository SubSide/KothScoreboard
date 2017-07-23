package subside.extensions.koth.scoreboard;

import org.bukkit.configuration.file.FileConfiguration;

import lombok.Getter;

public class ConfigHandler {
    private @Getter int characterLimit = 16;
    private @Getter int characterTitleLimit = 16;
    private @Getter boolean useOldScoreboard = false;
    private @Getter FileConfiguration cfg;
    
    private static @Getter ConfigHandler instance;
    
    public ConfigHandler(KothScoreboard plugin){
        instance = this;
        cfg = plugin.getConfig();
        characterLimit = cfg.getInt("characterlimit");
        characterTitleLimit = cfg.getInt("charactertitlelimit");
        useOldScoreboard = cfg.getBoolean("use-old-scoreboard");
    }
}
