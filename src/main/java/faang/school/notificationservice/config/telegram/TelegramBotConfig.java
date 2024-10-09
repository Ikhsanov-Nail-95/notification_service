package faang.school.notificationservice.config.telegram;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class TelegramBotConfig {

    @Value("${telegram-bot.name}")
    String botName;
    @Value("${telegram-bot.token}")
    String botToken;

}
