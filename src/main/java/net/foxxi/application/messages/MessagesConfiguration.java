package net.foxxi.application.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import lombok.Data;
import org.bukkit.Bukkit;

@Data
public class MessagesConfiguration {

  private static transient Gson GSON = new GsonBuilder()
      .setPrettyPrinting()
      .disableHtmlEscaping()
      .create();

  private transient File file;

  public MessagesConfiguration(File file) {
    this.file = file;
  }

  private String prefix = "&8[&aApplication&8] &7";
  private String nopermission = prefix + "&cDu besitzt nicht die nötigen Rechte.";
  private String dayMessage = prefix + "Du hast die Zeit auf &eTag &7gesetzt.";
  private String nightMessage = prefix + "Du hast die Zeit auf &bNacht &7gesetzt.";
  private String worldNotExists = prefix + "§cDiese Welt exitiert nicht.";
  private String stormWeather = prefix + "Es zieht sich nun ein &eSturm &7auf.";
  private String clearWeather = prefix + "Der &eSturm &7verzieht sich nun.";
  private String gamemodeMessage = prefix + "Dein Gamemode wurde auf &e%type% &7gesetzt.";
  private String playerNotOnline = prefix + "&cDieser Spieler ist nicht online.";
  private String gamemodePlayerMessage = prefix
      + "Du hast den Gamemode von &b%player% &7auf &e%type% &7verändert.";

  public void save() {
    try {
      Files.write(file.toPath(), GSON.toJson(this).getBytes());
    } catch (IOException exception) {
      Bukkit.getLogger().log(Level.WARNING, exception.toString());
    }
  }

}
