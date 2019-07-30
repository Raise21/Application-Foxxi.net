package net.foxxi.application.scoreboard.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import lombok.Data;
import org.bukkit.Bukkit;

@Data
public class ScoreboardConfiguration {

  private static transient Gson GSON = new GsonBuilder()
      .setPrettyPrinting()
      .disableHtmlEscaping()
      .create();

  private transient File file;

  public ScoreboardConfiguration(File file) {
    this.file = file;
  }

  private List<Line> scoreboardLines = new ArrayList<>();
  private String scoreboardTitle;

  public void save() {
    try {
      Files.write(file.toPath(), GSON.toJson(this).getBytes());
    } catch (IOException exception) {
      Bukkit.getLogger().log(Level.WARNING, exception.toString());
    }
  }

}
