package net.foxxi.application.commands;

import net.foxxi.application.Application;
import net.foxxi.application.session.Session;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GamemodeCommand implements CommandExecutor {

  private Session session;

  public GamemodeCommand() {
    session = Application.getInstance().getSession();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label,
      String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("You must be a player!");
      return true;
    }

    Player player = (Player) sender;

    if (!player.hasPermission("application.command.gamemode")) {
      player.sendMessage(session.getMessagesConfiguration()
          .getNopermission().replace("&", "§"));
      return true;
    }

    if (args.length == 1) {
      if (args[0].equalsIgnoreCase("0")) {
        session.changeGamemode(player, "0");
      } else if (args[0].equalsIgnoreCase("1")) {
        session.changeGamemode(player, "1");
      } else if (args[0].equalsIgnoreCase("2")) {
        session.changeGamemode(player, "2");
      } else if (args[0].equalsIgnoreCase("3")) {
        session.changeGamemode(player, "3");
      }
    } else if (args.length == 2) {
      String target = args[1];

      if (Bukkit.getPlayer(target) == null) {
        player.sendMessage(session.getMessagesConfiguration()
            .getPlayerNotOnline().replace("&", "§"));
        return true;
      }

      Player targetPlayer = Bukkit.getPlayer(target);

      if (args[0].equalsIgnoreCase("0")) {
        targetPlayer.sendMessage(session.getMessagesConfiguration().getGamemodeMessage()
            .replace("&", "§")
            .replace("%type%", GameMode.SURVIVAL.name()));
        targetPlayer.setGameMode(GameMode.SURVIVAL);
        player.sendMessage(session.getMessagesConfiguration().getGamemodePlayerMessage()
            .replace("&", "§")
            .replace("%player%", targetPlayer.getName())
            .replace("%type%", GameMode.SURVIVAL.name()));
      } else if (args[0].equalsIgnoreCase("1")) {
        targetPlayer.sendMessage(session.getMessagesConfiguration().getGamemodeMessage()
            .replace("&", "§")
            .replace("%type%", GameMode.CREATIVE.name()));
        targetPlayer.setGameMode(GameMode.CREATIVE);
        player.sendMessage(session.getMessagesConfiguration().getGamemodePlayerMessage()
            .replace("&", "§")
            .replace("%player%", targetPlayer.getName())
            .replace("%type%", GameMode.CREATIVE.name()));
      } else if (args[0].equalsIgnoreCase("2")) {
        targetPlayer.sendMessage(session.getMessagesConfiguration().getGamemodeMessage()
            .replace("&", "§")
            .replace("%type%", GameMode.ADVENTURE.name()));
        targetPlayer.setGameMode(GameMode.ADVENTURE);
        player.sendMessage(session.getMessagesConfiguration().getGamemodePlayerMessage()
            .replace("&", "§")
            .replace("%player%", targetPlayer.getName())
            .replace("%type%", GameMode.ADVENTURE.name()));
      } else if (args[0].equalsIgnoreCase("3")) {
        targetPlayer.sendMessage(session.getMessagesConfiguration().getGamemodeMessage()
            .replace("&", "§")
            .replace("%type%", GameMode.SPECTATOR.name()));
        targetPlayer.setGameMode(GameMode.SPECTATOR);
        player.sendMessage(session.getMessagesConfiguration().getGamemodePlayerMessage()
            .replace("&", "§")
            .replace("%player%", targetPlayer.getName())
            .replace("%type%", GameMode.SPECTATOR.name()));
      }
    }
    return true;
  }
}
