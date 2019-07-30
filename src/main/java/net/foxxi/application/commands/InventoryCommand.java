package net.foxxi.application.commands;

import net.foxxi.application.Application;
import net.foxxi.application.session.Session;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InventoryCommand implements CommandExecutor {

  private Application application;
  private Session session;

  public InventoryCommand() {
    application = Application.getInstance();
    session = application.getSession();
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label,
      String[] args) {

    if (!(sender instanceof Player)) {
      sender.sendMessage("You must be a player!");
      return true;
    }

    Player player = (Player) sender;

    if (!player.hasPermission("application.command.inventory")) {
      player.sendMessage(session.getMessagesConfiguration()
          .getNopermission().replace("&", "§"));
      return true;
    }

    application.getInventory().open(player);

    return true;
  }
}
