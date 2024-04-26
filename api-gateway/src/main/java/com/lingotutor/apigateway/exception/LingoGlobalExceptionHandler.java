package com.lingotutor.apigateway.exception;

import java.util.Map;

import org.springframework.boot.autoconfigure.web.WebProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ResponseStatusException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import reactor.core.publisher.Mono;

@Component
@Order(Integer.MIN_VALUE)
class LingoGlobalExceptionHandler extends AbstractErrorWebExceptionHandler {

    public LingoGlobalExceptionHandler(final ErrorAttributes errorAttributes,
                                        final WebProperties.Resources resources,
                                        final ApplicationContext applicationContext,
                                        final ServerCodecConfigurer configurer) {
        super(errorAttributes, resources, applicationContext);
        setMessageReaders(configurer.getReaders());
        setMessageWriters(configurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::prepareErrorResponse);
    }

    private Mono<ServerResponse> prepareErrorResponse(ServerRequest request) {

        ErrorAttributeOptions options = ErrorAttributeOptions.of(ErrorAttributeOptions.Include.MESSAGE);
        Map<String, Object> errorMap = getErrorAttributes(request, options);
        Throwable throwable = getError(request);
   
    
        return  buildResponseStatusAndMessage(throwable,errorMap);
    }
    
    private Mono<ServerResponse> buildResponseStatusAndMessage(Throwable throwable,  Map<String, Object> errorMap) {
       HttpStatus httpStatus =  null;
       
    	errorMap.remove("error");
        errorMap.remove("status");

        if (throwable instanceof ResponseStatusException) {
            httpStatus = (HttpStatus) ((ResponseStatusException) throwable).getStatusCode();
        } 
        else if (throwable instanceof ExpiredJwtException) {
        	errorMap.put("message","Session Expired!, Kindly login again");
            httpStatus= HttpStatus.UNAUTHORIZED;
        } 
        else if (throwable instanceof JwtException) {
            httpStatus= HttpStatus.UNAUTHORIZED;
        } 
        else {
        	httpStatus =  HttpStatus.INTERNAL_SERVER_ERROR;
        }
        
        return ServerResponse.status(httpStatus)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(errorMap));
    }
}