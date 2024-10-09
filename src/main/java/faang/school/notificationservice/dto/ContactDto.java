package faang.school.notificationservice.dto;

import faang.school.notificationservice.enums.ContactType;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDto {

    private long id;
    @NotNull
    private Long userId;
    @NotNull
    private String contact;
    @NotNull
    private ContactType type;

}