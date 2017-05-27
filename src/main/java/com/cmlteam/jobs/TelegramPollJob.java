package com.cmlteam.jobs;

import com.cmlteam.util.PropertyUtil;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by Oleksandr Shchetynin on 5/27/2017.
 */
@Component
public class TelegramPollJob {

    private TelegramBot bot;
    private static int offset = -1;

    @PostConstruct
    private void init() {
        String apiKey = PropertyUtil.getProperty("telegram-api-key");
        bot = TelegramBotAdapter.build(apiKey);
    }

    @Scheduled(fixedRate = 247)
    public void mainLoop() {
        GetUpdatesResponse updatesResponse = bot.execute(new GetUpdates().offset(offset));
        List<Update> updates = updatesResponse.updates();
        for(Update update: updates) {
            offset = Math.max(offset, update.updateId() + 1);
            Message message = update.message();
            String text = message.text();
            System.out.println(text);
            long chatId = message.chat().id();
            bot.execute(new SendMessage(chatId, "Heya!"));
        }
    }

}
