package subside.extensions.koth.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import subside.plugins.koth.events.KothEndEvent;
import subside.plugins.koth.events.KothInitializeEvent;
import subside.plugins.koth.events.KothPostUpdateEvent;

public class KothScoreboard extends JavaPlugin implements Listener {
    @Override
    public void onLoad(){
        // Registering the scoreboards //
        new ConfigHandler(this);
        new ScoreboardManager();
        
        
        if(ConfigHandler.getInstance().isUseOldScoreboard()){
            ScoreboardManager.getInstance().registerScoreboard("default", OldScoreboard.class);
        } else {
            ScoreboardManager.getInstance().registerScoreboard("default", DefaultScoreboard.class);
        }

        ScoreboardManager.getInstance().registerScoreboard("conquest", ConquestScoreboard.class);
    }
    
    
    @Override
    public void onEnable(){
        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(ScoreboardManager.getInstance(), this);
    }
    
    @Override
    public void onDisable(){
        // Remove the scoreboard from all the players
        ScoreboardManager.getInstance().destroy();
    }
    
    
    
    // Koth Event listeners
    
    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void KothInitialize(final KothInitializeEvent event){
        Bukkit.getScheduler().runTask(this, new Runnable(){
            @Override
            public void run() {
                ScoreboardManager.getInstance().loadScoreboard("default", event.getRunningKoth());
            }    
        });
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void KothPostUpdate(KothPostUpdateEvent event){
        ScoreboardManager.getInstance().update();
    }
    
    @EventHandler(priority = EventPriority.MONITOR)
    public void KothStart(KothEndEvent event){
        ScoreboardManager.getInstance().destroy();
    }
    
    
}
