package net.foxxi.application.session;

import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import lombok.AccessLevel;
import lombok.Getter;
import net.foxxi.application.Application;
import net.foxxi.application.chat.ChatConfiguration;
import net.foxxi.application.inventory.config.InventoryConfiguration;
import net.foxxi.application.inventory.config.InventoryItem;
import net.foxxi.application.messages.MessagesConfiguration;
import net.foxxi.application.scoreboard.config.Line;
import net.foxxi.application.scoreboard.config.ScoreboardConfiguration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class Session implements ISession {

  @Getter(AccessLevel.PUBLIC)
  private ScoreboardConfiguration scoreboardConfiguration;

  @Getter(AccessLevel.PUBLIC)
  private ChatConfiguration chatConfiguration;

  @Getter(AccessLevel.PUBLIC)
  private MessagesConfiguration messagesConfiguration;

  @Getter(AccessLevel.PUBLIC)
  private InventoryConfiguration inventoryConfiguration;

  private Application application;

  private File scoreboardFile;
  private File chatFile;
  private File messagesFile;
  private File inventoryFile;

  private Line line1;
  private Line line2;
  private Line line3;

  private InventoryItem item1 = new InventoryItem();
  private InventoryItem item2 = new InventoryItem();
  private InventoryItem item3 = new InventoryItem();
  private InventoryItem item4 = new InventoryItem();
  private InventoryItem item5 = new InventoryItem();

  private Session() {
    this.application = Application.getInstance();
  }


  @Override
  public void loadScoreboard() {
    scoreboardFile = new File("plugins/Application", "scoreboard.json");

    try {
      Files.createDirectories(Paths.get(scoreboardFile.getParent()));
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    try {
      if (scoreboardFile.exists()) {
        scoreboardConfiguration = new Gson().fromJson(new String(
            Files.readAllBytes(scoreboardFile.toPath())), ScoreboardConfiguration.class);
        scoreboardConfiguration.setFile(scoreboardFile);
      } else {
        scoreboardFile.createNewFile();
        scoreboardConfiguration = new ScoreboardConfiguration(scoreboardFile);
        scoreboardConfiguration.setScoreboardTitle("&a&lApplication");
        line1 = new Line();
        line2 = new Line();
        line3 = new Line();
        application.getScoreboardManager().defaultScoreboard(line1, line2, line3);
        scoreboardConfiguration.save();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void loadChat() {
    chatFile = new File("plugins/Application/chat", "chat.json");

    try {
      Files.createDirectories(Paths.get(chatFile.getParent()));
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    try {
      if (chatFile.exists()) {
        chatConfiguration = new Gson().fromJson(new String(
            Files.readAllBytes(chatFile.toPath())), ChatConfiguration.class);
        chatConfiguration.setFile(chatFile);
      } else {
        chatFile.createNewFile();
        chatConfiguration = new ChatConfiguration(chatFile);
        chatConfiguration.save();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void loadMessages() {
    messagesFile = new File("plugins/Application/messages", "messages.json");

    try {
      Files.createDirectories(Paths.get(messagesFile.getParent()));
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    try {
      if (messagesFile.exists()) {
        messagesConfiguration = new Gson().fromJson(new String(
            Files.readAllBytes(messagesFile.toPath())), MessagesConfiguration.class);
        messagesConfiguration.setFile(messagesFile);
      } else {
        messagesFile.createNewFile();
        messagesConfiguration = new MessagesConfiguration(messagesFile);
        messagesConfiguration.save();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  @Override
  public void loadInventory() {
    inventoryFile = new File("plugins/Application/inventory", "inventory.json");

    try {
      Files.createDirectories(Paths.get(inventoryFile.getParent()));
    } catch (IOException exception) {
      exception.printStackTrace();
    }

    try {
      if (inventoryFile.exists()) {
        inventoryConfiguration = new Gson().fromJson(new String(
            Files.readAllBytes(inventoryFile.toPath())), InventoryConfiguration.class);
        inventoryConfiguration.setFile(inventoryFile);
      } else {
        inventoryFile.createNewFile();
        inventoryConfiguration = new InventoryConfiguration(inventoryFile);
        inventoryConfiguration.defaultInventory(item1, item2,
            item3, item4, item5);
        inventoryConfiguration.save();
      }
    } catch (IOException exception) {
      exception.printStackTrace();
    }
  }

  public void changeTime(Player player, World world, int time, String type) {
    Bukkit.getWorld(world.getName()).setTime(time);
    if (type.toLowerCase().equalsIgnoreCase("day")) {
      player.sendMessage(this.messagesConfiguration.getDayMessage()
          .replace("&", "§"));
    } else if (type.toLowerCase().equalsIgnoreCase("night")) {
      player.sendMessage(this.messagesConfiguration.getNightMessage()
          .replace("&", "§"));
    }
  }

  public void changeWeather(Player player, World world, boolean storm, String type) {
    Bukkit.getWorld(world.getName()).setStorm(storm);
    if (type.toLowerCase().equalsIgnoreCase("storm")) {
      player.sendMessage(this.messagesConfiguration.getStormWeather()
          .replace("&", "§"));
    } else if (type.toLowerCase().equalsIgnoreCase("clear")) {
      player.sendMessage(this.messagesConfiguration.getClearWeather()
          .replace("&", "§"));
    }
  }

  public void changeGamemode(Player player, String type) {
    if (type.equalsIgnoreCase("0")) {
      player.setGameMode(GameMode.SURVIVAL);
      player.sendMessage(this.messagesConfiguration.getGamemodeMessage()
          .replace("&", "§")
          .replace("%type%", GameMode.SURVIVAL.name()));
    } else if (type.equalsIgnoreCase("1")) {
      player.setGameMode(GameMode.CREATIVE);
      player.sendMessage(this.messagesConfiguration.getGamemodeMessage()
          .replace("&", "§")
          .replace("%type%", GameMode.CREATIVE.name()));
    } else if (type.equalsIgnoreCase("2")) {
      player.setGameMode(GameMode.ADVENTURE);
      player.sendMessage(this.messagesConfiguration.getGamemodeMessage()
          .replace("&", "§")
          .replace("%type%", GameMode.ADVENTURE.name()));
    } else if (type.equalsIgnoreCase("3")) {
      player.setGameMode(GameMode.SPECTATOR);
      player.sendMessage(this.messagesConfiguration.getGamemodeMessage()
          .replace("&", "§")
          .replace("%type%", GameMode.SPECTATOR.name()));
    }
  }


  public static Session create() {
    return new Session();
  }
}

