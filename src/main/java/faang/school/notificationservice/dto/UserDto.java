package faang.school.notificationservice.dto;

import faang.school.notificationservice.enums.PreferredContact;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private long id;
    private String username;
    private String email;
    private String phone;
    private PreferredContact preference;

}