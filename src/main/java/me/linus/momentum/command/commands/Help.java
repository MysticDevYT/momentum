package me.linus.momentum.command.commands;

import me.linus.momentum.Momentum;
import me.linus.momentum.command.Command;
import me.linus.momentum.util.client.external.MessageUtil;

/**
 * @author linustouchtips
 * @since 12/01/2020
 */

public class Help extends Command {
    public Help() {
        super("help");
    }

    @Override
    public void onCommand(String[] args) {
        MessageUtil.sendClientMessage("Current Prefix: " + Momentum.PREFIX);
    }
}
