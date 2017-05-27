package com.cmlteam.controller;


import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Logger;

@Controller
public class SafePropertyController {
    private static final Logger log = Logger.getLogger(SafePropertyController.class.getName());

    private final static TelegramBot bot = TelegramBotAdapter.build("TBD");

    @ResponseBody
    @RequestMapping("/ping")
    public String ping() {
        return "OK";
    }

    @ResponseBody
    @RequestMapping(value = "/webhook", method = RequestMethod.POST, consumes = "application/json")
    public String webhook(@RequestBody String body) {
        log.info("Request: " + body);

        Update update = BotUtils.parseUpdate(body);
        Message message = update.message();

        Chat chat = message.chat();
        SendMessage sendMessage = new SendMessage(chat.id(), "Chat id: " + chat.id() + "\n" +
                "First: " + chat.firstName() + "\n" +
                "Last: " + chat.lastName() + "\n" +
                "Username: " + chat.username() + "\n" +
                "From id: " + message.from().id() + "\n" +
                "Date: " + new Date() + "\n" +
                "TZ: " + TimeZone.getDefault());

        bot.execute(sendMessage);

        return "";
    }
}
