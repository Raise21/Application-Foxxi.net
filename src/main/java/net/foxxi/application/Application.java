package net.foxxi.application;

import java.util.Arrays;
import lombok.AccessLevel;
import lombok.Getter;
import net.foxxi.application.commands.GamemodeCommand;
import net.foxxi.application.commands.InventoryCommand;
import net.foxxi.application.commands.SetTimeCommand;
import net.foxxi.application.commands.SetWeatherCommand;
import net.foxxi.application.inventory.ApplicationInventory;
import net.foxxi.application.listener.PlayerChatListener;
import net.foxxi.application.listener.PlayerJoinListener;
import net.foxxi.application.listener.PlayerQuitListener;
import net.foxxi.application.listener.inventory.InventoryClickListener;
import net.foxxi.application.scoreboard.ScoreboardManager;
import net.foxxi.application.session.Session;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Application extends JavaPlugin {

  @Getter(AccessLevel.PUBLIC)
  private static Application instance;

  @Getter(AccessLevel.PUBLIC)
  private ScoreboardManager scoreboardManager;

  @Getter(AccessLevel.PUBLIC)
  private Session session;

  @Getter(AccessLevel.PUBLIC)
  private ApplicationInventory inventory;


  @Override
  public void onLoad() {
    instance = this;
    session = Session.create();
    scoreboardManager = new ScoreboardManager();
  }

  @Override
  public void onEnable() {
    session.loadScoreboard();
    session.loadChat();
    session.loadMessages();
    session.loadInventory();

    inventory = ApplicationInventory.create();

    Arrays.asList(new PlayerJoinListener(),
        new PlayerQuitListener(), new PlayerChatListener(),
        new InventoryClickListener()).forEach(listener ->
        Bukkit.getPluginManager().registerEvents(listener, this));

    Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
      for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
        scoreboardManager.updateTime(onlinePlayer);
        scoreboardManager.updateDate(onlinePlayer);
      }
    }, 0, 20);

    getCommand("settime").setExecutor(new SetTimeCommand());
    getCommand("setweather").setExecutor(new SetWeatherCommand());
    getCommand("gamemode").setExecutor(new GamemodeCommand());
    getCommand("inventory").setExecutor(new InventoryCommand());

  }

  @Override
  public void onDisable() {
  }
}
