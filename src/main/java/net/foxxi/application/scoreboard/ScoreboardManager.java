package net.foxxi.application.scoreboard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import net.foxxi.application.Application;
import net.foxxi.application.scoreboard.config.Line;
import net.foxxi.application.session.Session;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardManager implements IScoreboard {

  private Scoreboard scoreboard;
  private Objective objective;
  private transient SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
  private transient SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
  private Date date = Calendar.getInstance().getTime();
  private Session session;

  public ScoreboardManager() {
    timeFormat.setTimeZone(TimeZone.getTimeZone("GMT+2:00"));
    session = Application.getInstance().getSession();
  }

  @Override
  public void createScoreboard(Player player) {
    scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    objective = scoreboard.registerNewObjective("aaa", "dummy");
    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    objective.setDisplayName(session.getScoreboardConfiguration().getScoreboardTitle()
        .replace("&", "§"));
  }

  @Override
  public void createTeams(Player player) {
    for (Line line : session.getScoreboardConfiguration().getScoreboardLines()) {
      Team team = scoreboard.registerNewTeam(line.getTeam());
      team.setPrefix(line.getText()
          .replace("&", "§")
          .replace("%time%", timeFormat.format(new Date()))
          .replace("%date", dateFormat.format(date))
          .replace("%online_players%", Integer.toString(Bukkit.getOnlinePlayers().size())));
      team.addEntry(line.getEntry().replace("&", "§"));
    }
  }

  @Override
  public void createScore(Player player) {
    for (Line line : session.getScoreboardConfiguration().getScoreboardLines()) {
      objective.getScore(line.getEntry().replace("&", "§"))
          .setScore(line.getSlot());
    }
  }

  @Override
  public void sendScoreboard(Player player) {
    createScoreboard(player);
    createTeams(player);
    createScore(player);
    player.setScoreboard(scoreboard);
  }

  @Override
  public void updateTime(Player player) {
    for (Line line : session.getScoreboardConfiguration().getScoreboardLines()) {
      if (line.getText().contains("%time%")) {
        player.getScoreboard().getTeam(line.getTeam()).setPrefix(line.getText()
            .replace("&", "§")
            .replace("%time%", timeFormat.format(new Date())));
        break;
      }
    }
  }

  @Override
  public void updateDate(Player player) {
    for (Line line : session.getScoreboardConfiguration().getScoreboardLines()) {
      if (line.getText().contains("%date%")) {
        player.getScoreboard().getTeam(line.getTeam()).setPrefix(line.getText()
            .replace("&", "§")
            .replace("%date%", dateFormat.format(date)));
        break;
      }
    }
  }

  @Override
  public void updateOnlinePlayers(Player player, int players) {
    for (Line line : session.getScoreboardConfiguration().getScoreboardLines()) {
      if (line.getText().contains("%online_players%")) {
        player.getScoreboard().getTeam(line.getTeam()).setPrefix(line.getText()
            .replace("&", "§")
            .replace("%online_players%", Integer.toString(players)));
        break;
      }
    }
  }

  @Override
  public void defaultScoreboard(Line line1, Line line2, Line line3) {
    line1.setText("&a%online_players%");
    line1.setEntry("&a");
    line1.setTeam("players");
    line1.setSlot(1);

    line2.setText("&b%date%");
    line2.setEntry("&b");
    line2.setTeam("date");
    line2.setSlot(2);

    line3.setText("&e%time%");
    line3.setEntry("&e");
    line3.setTeam("time");
    line3.setSlot(3);

    session.getScoreboardConfiguration().getScoreboardLines().add(line1);
    session.getScoreboardConfiguration().getScoreboardLines().add(line2);
    session.getScoreboardConfiguration().getScoreboardLines().add(line3);
  }

}
