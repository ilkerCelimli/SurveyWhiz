package org.portifolyo.response;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class GenericResponse<T> {
    private final Integer statusCode;
    private final String message;
    private final String localDateTime;
    private T data;


    public GenericResponse(Integer statusCode, String message, T data) {
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.localDateTime = LocalDateTime.now().toString();
    }
    public GenericResponse(Integer statusCode,String message) {
        this.message = message;
        this.statusCode = statusCode;
        this.localDateTime = LocalDateTime.now().toString();
    }


    public Integer getStatusCode() {
        return statusCode;
    }

    public String getMessage() {
        return message;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }

    public T getData() {
        return data;
    }

    public static <T> GenericResponse<T> SUCCESS(T data){
        return new GenericResponse<>(200,"sucess",data);
    }
    public static <T> GenericResponse<T> SUCCESS(){
        return new GenericResponse<>(200,"success");
    }
    public static GenericResponse<Void> BAD_REQUEST(String message){return new GenericResponse<>(400,message);
    }


}
