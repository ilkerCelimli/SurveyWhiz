package request.userservice;

public record UserLoginRequest(
        String email,
        String password
) {
}
