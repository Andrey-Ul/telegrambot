package com.github.andreyul.telegrambot.command;

import com.github.andreyul.telegrambot.service.SendBotMessageService;
import com.google.common.collect.ImmutableMap;

import static com.github.andreyul.telegrambot.command.CommandName.*;

/**
 * Container of the {@link Command}s, which are using for handling telegram commands.
 */
public class CommandContainer {

    private final ImmutableMap<String, Command> commandMap;
    private final Command unknownCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService) {

        commandMap = ImmutableMap.<String, Command>builder()
                .put(START.getCommandName(), new StartCommand(sendBotMessageService))
                .put(STOP.getCommandName(), new StopCommand(sendBotMessageService))
                .put(HELP.getCommandName(), new HelpCommand(sendBotMessageService))
                .put(NO.getCommandName(), new NoCommand(sendBotMessageService))
                .build();

        unknownCommand = new UnknownCommand(sendBotMessageService);

    }

    public Command retrieveCommand(String commandIdentifer) {
        return commandMap.getOrDefault(commandIdentifer, unknownCommand);
    }

}
