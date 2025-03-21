package com.backEndMobile.backEndMobile.handler;

import com.backEndMobile.backEndMobile.exceptions.BlankValueException;
import com.backEndMobile.backEndMobile.exceptions.ExceptionResponse;
import com.backEndMobile.backEndMobile.exceptions.InvalidTimeFormatException;
import com.backEndMobile.backEndMobile.exceptions.InvalidTimeRangeException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class HandlerExceptions {

    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Erro de solicitação inválida")
    })
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de solicitação inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @ExceptionHandler(BlankValueException.class)
    public final ResponseEntity<ExceptionResponse> handleBlankValueException(BlankValueException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de solicitação inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @ExceptionHandler(InvalidTimeFormatException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidTimeFormatException(InvalidTimeFormatException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "400", description = "Erro de solicitação inválida",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @ExceptionHandler(InvalidTimeRangeException.class)
    public final ResponseEntity<ExceptionResponse> handleInvalidTimeRangeException(InvalidTimeRangeException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
