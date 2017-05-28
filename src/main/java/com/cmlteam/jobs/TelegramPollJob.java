package com.cmlteam.jobs;

import com.cmlteam.model.lun.Building;
import com.cmlteam.util.LunUtil;
import com.cmlteam.util.PropertyUtil;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * Created by Oleksandr Shchetynin on 5/27/2017.
 */
@Component
public class TelegramPollJob {

    private static Logger log = LoggerFactory.getLogger(TelegramPollJob.class);

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
            if (text.contains(" ") || text.contains(",")) {
                try {
                    Building building = LunUtil.getClosestBuilding(text);
                    if (building != null) {
                        String result = String.format(
                                "Результат перевірки за адресою %s:\nВстановив рейтинг: %s",
                                building.formattedAddress,
                                "9/10");
                        if (false) {

                        }
                        bot.execute(new SendMessage(chatId, result));
                    } else {
                        shitHappens(chatId);
                    }
                } catch (IOException e) {
                    log.warn("", e);
                    shitHappens(chatId);
                }
            } else {
                bot.execute(new SendMessage(chatId, "Введите адрес объекта"));
            }
        }
    }

    private void shitHappens(long chatId) {
        bot.execute(new SendMessage(chatId, "Введите адрес объекта еще раз"));
    }

}
