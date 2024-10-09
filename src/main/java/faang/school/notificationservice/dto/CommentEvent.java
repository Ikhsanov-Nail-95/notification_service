package faang.school.notificationservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentEvent {

    @NotNull
    private Long authorOfCommentId;
    @Positive
    private Long authorOfPostId;
    @Positive
    private Long postId;
    private String content;
    @Positive
    private Long commentId;
    private LocalDateTime createdAt;

}