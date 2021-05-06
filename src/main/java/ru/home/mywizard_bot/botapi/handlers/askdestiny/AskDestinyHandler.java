package ru.home.mywizard_bot.botapi.handlers.askdestiny;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.home.mywizard_bot.botapi.BotState;
import ru.home.mywizard_bot.botapi.InputMessageHandler;
import ru.home.mywizard_bot.cache.UserDataCache;
import ru.home.mywizard_bot.service.ReplyMessagesService;

//Спрашивает пользователя хоче ли он получить предсказания
@Slf4j
@Component
public class AskDestinyHandler implements InputMessageHandler {
    private UserDataCache userDataCache;
    private ReplyMessagesService messageService;

    public AskDestinyHandler(UserDataCache userDataCache, ReplyMessagesService messageService) {
        this.userDataCache = userDataCache;
        this.messageService = messageService;

    }

    @Override
    public SendMessage handle(Message message) {
        return processUsersInput(message);
    }

    @Override
    public BotState getHandlerName() {
        return BotState.ASK_DESTINY;
    }

    private SendMessage processUsersInput(Message inputMsg) {
        int userId = inputMsg.getFrom().getId();
        long chatId = inputMsg.getChatId();

        SendMessage replyToUser = messageService.getReplyMessage(chatId, "reply.askDestiny");
        userDataCache.setUsersCurrentBotState(userId, BotState.FILLING_PROFILE);

        return replyToUser;
    }
}
