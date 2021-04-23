package com.github.andreyul.telegrambot.bot;

import com.github.andreyul.telegrambot.command.CommandContainer;
import com.github.andreyul.telegrambot.service.SendBotMessageServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.github.andreyul.telegrambot.command.CommandName.NO;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    public static String COMMAND_PREFIX = "/";

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    private final CommandContainer commandContainer;

    public TelegramBot() {
        this.commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this));
    }

    // Это и есть точка входа, куда будут поступать сообщения от пользователей
    // Отсюда будет идти вся новая логика
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = update.getMessage().getText().trim();      // Получаем сообщение

            if (message.startsWith(COMMAND_PREFIX)) {
                String commandIdentifier = message.split(" ")[0].toLowerCase();
                commandContainer.retrieveCommand(commandIdentifier).execute(update);
            } else {
                commandContainer.retrieveCommand(NO.getCommandName()).execute(update);
            }
        }

    }

    // Здесь нужно добавить username нашего бота, к которому будем соединяться
    @Override
    public String getBotUsername() {
        return username;
    }

    // Токен бота
    @Override
    public String getBotToken() {
        return token;
    }

}
