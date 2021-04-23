package com.github.andreyul.telegrambot.command;

import com.github.andreyul.telegrambot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.andreyul.telegrambot.command.CommandName.*;

/**
 * Help {@link Command}.
 */
public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    public static final String HELP_MESSAGE = String.format("<b>Available commands</b>\n\n"
                    + "<b>Start\\stop working with the bot</b>\n"
                    + "%s - Start working with me\n"
                    + "%s - Stop working with me\n\n"
                    + "%s - Get help in working with me\n",
            START.getCommandName(), STOP.getCommandName(), HELP.getCommandName());

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        sendBotMessageService.sendMessage(update.getMessage().getChatId().toString(), HELP_MESSAGE);
    }
}
