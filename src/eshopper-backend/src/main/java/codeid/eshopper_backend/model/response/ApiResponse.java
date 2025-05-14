package codeid.eshopper_backend.model.response;

import java.time.Instant;

import codeid.eshopper_backend.model.enumeration.EnumStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private EnumStatus status;
    private String message;
    private T data;
    private Instant timestamp;

    // Constructors
    public ApiResponse(EnumStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }


}