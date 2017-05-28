package com.cmlteam.jobs;

import com.cmlteam.model.lun.Building;
import com.cmlteam.util.LunUtil;
import com.cmlteam.util.PropertyUtil;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.TelegramBotAdapter;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.ParseMode;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.request.SendPhoto;
import com.pengrad.telegrambot.request.SendVideo;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

import static com.cmlteam.util.MessageUtil.isGreeting;
import static com.cmlteam.util.MessageUtil.oneOf;

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
            String text = StringUtils.trimToEmpty(message.text());
            System.out.println(text);
            long chatId = message.chat().id();
            if(isGreeting(text)) {
                bot.execute(new SendMessage(chatId, oneOf(
                    "Привіт, введи адресу об'єкту або назву ЖК",
                    "Вітаю, будь ласка, введіть адресу або назву об'єкту"
                )));
                sendFraudAlert(chatId);
            }
            else if (text.contains(" ") || text.contains(",")) {
                try {
//                    Building building = LunUtil.getClosestBuilding(text);
                    if(building == null || !StringUtils.contains(building.formattedAddress, "Kyiv")) {
                        building = LunUtil.getClosestBuilding(String.format("%s, Kyiv, Ukraine", text));
                    }
//                    if (building != null) {
                        /*String result = String.format(
                            "Результат перевірки за вашим запитом:\n\n" +
                                "<b>Адреса:</b> %s\n" +
                                "<b>Замовник будівництва:</b> %s\n" +
                                "<b>Генпідрядник:</b> %s\n" +
                                "<b>Строк введення в експлуатацію:</b> %s\n" +
                                "<b>Дозвільні документи:</b> %s\n" +
                                "<b>Статус землі:</b> %s\n" +
                                "<b>Виявлені ризики:</b> %s\n" +
                                "<b>Рейтинг: %d/%d</b>",
                            building.formattedAddress,
                            "ZAMOV",
                            "GEN",
                            "STROK",
                            "DOCS",
                            "STATUS",
                            "RISKS",
                            building.developers.rank,
                            LunUtil.RATING_NORMALIZER
                        );*/
                        String result = String.format(
                            "Результат перевірки за вашим запитом:\n\n" +
                                "<b>Адреса:</b> %s\n" +
                                "<b>Висновок:</b> %s\n",
                            "перетин проспектів Броварського та Визволителів у Дніпровському районі міста Києва",
                            "є суттєві ризики"
                        );
                        /*if (building.img != null) {
                        if(building.developers.rank < (LunUtil.RATING_NORMALIZER / 2 - 1)) {
                            sendFraudAlert(chatId);
                        }
                        else if (building.img != null) {
                            String img = building.img.small;
                            if (StringUtils.isEmpty(img))
                                img = building.img.mainThumb;
                            if (img.startsWith("//"))
                                img = "https:" + img;
                            bot.execute(new SendPhoto(chatId, img));
                        }*/
                    bot.execute(new SendPhoto(chatId, "http://jk-freedom.com.ua/Media/images/renders/projects_big/beefc0111101056383a36babb0ec05d2.jpg"));
                        SendResponse response = bot.execute(new SendMessage(chatId, result).parseMode(ParseMode.HTML)
                                .replyMarkup(horizontalKeyboardFrom("Детальніше...")));
                        if (!response.isOk()) {
                            log.error(response.description());
                        }
//                    }
//                    else {
//                        wrongAddress(chatId);
//                    }
                }
                catch (Exception e) {
                    log.warn("", e);
                    wrongAddress(chatId);
                }
            }
            else {
                bot.execute(new SendMessage(chatId, oneOf(
                    "Введіть адресу об'єкту або назву ЖК:",
                    "Будь ласка, введіть адресу або назву об'єкту:"
                )));
            }
        }
    }
    private static Keyboard horizontalKeyboardFrom(String... keys) {
        return new ReplyKeyboardMarkup(keys).oneTimeKeyboard(true).resizeKeyboard(true).selective(true);
    }
    private void wrongAddress(long chatId) {
        bot.execute(new SendMessage(chatId, oneOf("Назва введена невірно, будь ласка спробуйте ще раз:")));
    }

    private void sendFraudAlert(long chatId) {
        bot.execute(
                new SendVideo(
                        chatId,
                        oneOf(
                                "https://media1.giphy.com/media/hgjNPEmAmpCMM/200.mp4",
                                "https://media4.giphy.com/media/yoJC2NhEwhBSQhu6WY/200.mp4",
                                "https://media1.giphy.com/media/ORUfXNGgFVy2Q/200.mp4",
                                "https://media3.giphy.com/media/ABoaCMjkoqmIg/200.mp4",
                                "https://media1.giphy.com/media/WFJwD9IWisMQE/200.mp4",
                                "https://media1.giphy.com/media/Ub8XEam5vXbMY/200.mp4"
                        )
                )
        );
    }
}
