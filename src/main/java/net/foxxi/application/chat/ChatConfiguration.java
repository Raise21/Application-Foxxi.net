package net.foxxi.application.chat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import lombok.Data;
import org.bukkit.Bukkit;

@Data
public class ChatConfiguration {

  private static transient Gson GSON = new GsonBuilder()
      .setPrettyPrinting()
      .disableHtmlEscaping()
      .create();

  private transient File file;

  public ChatConfiguration(File file) {
    this.file = file;
  }

  private String chatPrefix = "&8[&aSpieler&8] &a%name%&8: &7";

  public void save() {
    try {
      Files.write(file.toPath(), GSON.toJson(this).getBytes());
    } catch (IOException exception) {
      Bukkit.getLogger().log(Level.WARNING, exception.toString());
    }
  }

}
