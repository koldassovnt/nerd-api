package kz.nkoldassov.nerdapi.client.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {
    public String message;

    public static MessageResponse of(String message) {
        return new MessageResponse(message);
    }
}
