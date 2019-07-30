package net.foxxi.application.listener;

import java.util.concurrent.CompletableFuture;
import net.foxxi.application.Application;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

  private Application application;

  public PlayerJoinListener() {
    application = Application.getInstance();
  }

  @EventHandler
  public void expectJoin(PlayerJoinEvent event) {
    application.getScoreboardManager().sendScoreboard(event.getPlayer());

    CompletableFuture.runAsync(() -> {
      for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
        application.getScoreboardManager()
            .updateOnlinePlayers(onlinePlayers, Bukkit.getOnlinePlayers().size());
      }
    });
  }
}
