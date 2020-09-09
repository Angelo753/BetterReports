/*
 * BetterReports - AdminCommand.java
 *
 * Plugin created by Timmy109
 * Github profile: https://github.com/Timmy109
 * Spigot Profile: https://www.spigotmc.org/members/_timmyy_.919057/
 * Discord Server: https://discord.gg/wafV4VP
 *
 * MIT License
 *
 * Copyright (c) 2020 Tim Uding.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package me.timmy109.betterreports.commands;
import me.timmy109.betterreports.BetterReports;
import me.timmy109.betterreports.utils.ArrayUtils;
import me.timmy109.betterreports.utils.ChatUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.List;

public class AdminCommand implements CommandExecutor {

	List<String> debug = ArrayUtils.getDebugList();
	List<String> reload = ArrayUtils.getReloadList();

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		List<String> adminHelp = ArrayUtils.getAdminHelpList();
		List<String> playerHelp = ArrayUtils.getPlayerHelpList();


		if (args.length == 0) {
			if (sender.hasPermission("betterreports.admin")) {
				for (String s : adminHelp) {
					sender.sendMessage(ChatUtils.color(s));
				}
				return true;
			}

			for (String s : playerHelp) {
				sender.sendMessage(ChatUtils.color(s));
			}
			return true;
		}

		switch (args[0]) {
			case "debug":
				debug(sender);
				break;
			case "reload":
				reloading(sender);
				break;
		}

		if (args.length > 1) {
			sender.sendMessage(ChatUtils.color("&cUnknown Command"));
			return true;
		}
		return true;
	}

		private void reloading(CommandSender sender) {
		if (sender.hasPermission("betterreports.reload")) {
			try {
				BetterReports.getInstance().reloadConfig();
				for (String s : reload) {
					sender.sendMessage(ChatUtils.color(s));
				}
			} catch (Exception ex) {
				sender.sendMessage(ChatUtils.color("&cThere was an error reloading the config. Check console for more details."));
			}
			return;
		}
		sender.sendMessage(ChatUtils.color("&cYou do not have permission to execute this command!"));
		}

		private void debug(CommandSender sender) {
		if (sender.hasPermission("betterreports.admin")) {
			for (String s : debug) {
				sender.sendMessage(ChatUtils.color(s));
			}
			return;
		}
		sender.sendMessage(ChatUtils.color("&cYou do not have permission to execute this command!"));
	}
}