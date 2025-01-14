package faang.school.notificationservice.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import faang.school.notificationservice.client.UserServiceClient;
import faang.school.notificationservice.dto.LikeEvent;
import faang.school.notificationservice.message.MessageBuilder;
import faang.school.notificationservice.service.NotificationService;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Component
public class LikeEventListener extends AbstractListener<LikeEvent> {

    protected LikeEventListener(ObjectMapper objectMapper,
                                UserServiceClient userServiceClient,
                                MessageBuilder<LikeEvent> messageBuilder,
                                List<NotificationService> notificationServicesList) {
        super(objectMapper, userServiceClient, messageBuilder, notificationServicesList);
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {
        handleEvent(message, LikeEvent.class, likeEvent -> {
            String textMessage = getMessage(likeEvent, Locale.getDefault());
            sendNotification(likeEvent.getAuthorLikeId(), textMessage);
        });
    }
}