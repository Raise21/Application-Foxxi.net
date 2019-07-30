package net.foxxi.application.scoreboard;

import net.foxxi.application.scoreboard.config.Line;
import org.bukkit.entity.Player;

public interface IScoreboard {
  void createScoreboard(Player player);
  void createTeams(Player player);
  void createScore(Player player);
  void sendScoreboard(Player player);
  void updateTime(Player player);
  void updateDate(Player player);
  void updateOnlinePlayers(Player player, int players);
  void defaultScoreboard(Line line1, Line line2, Line line3);
}
