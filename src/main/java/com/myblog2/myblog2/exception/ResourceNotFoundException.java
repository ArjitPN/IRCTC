package com.myblog2.myblog2.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{
    //Generate the message
    private String resourceName;//ex-Table name
    private String fieldName;//ex-id
    private long fieldValue;//ex-with id no.

    public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s :'%s'",resourceName,fieldName,fieldValue));
        //Post not found with id :100
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
    //here we only need get message and response so we need only getters
    public String getResourceName() {
        return resourceName;
    }
    public String getFieldName() {
        return fieldName;
    }
    public long getFieldValue() {
        return fieldValue;
    }
}
