package request.userservice;

import java.util.Date;

public record UserRegisterRequest(
        String name,
        String surname,
        String email,
        String password,
        Date birtday
) {
}
