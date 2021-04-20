package com.github.andreyul.telegrambot.bot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class TelegramBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String username;

    @Value("${bot.token}")
    private String token;

    // Это и есть точка входа, куда будут поступать сообщения от пользователей
    // Отсюда будет идти вся новая логика
    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {

            String message = update.getMessage().getText().trim();      // Получаем сообщение
            String chatId = update.getMessage().getChatId().toString(); // Получаем ID чата, в котором идет переписка

            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(chatId);
            sendMessage.setText(message);

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
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
