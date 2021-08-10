package com.cewb.app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class ResponseMessage<M> {

    private Long id;

    private String message;

    private String operation;

    public ResponseMessage(Long id, String operation) {
        this.id = id;
        this.operation = operation;
//        this.message = String.format("Successfully %s ID # %s", this.getStringValue(), id);
    }

    public String getStringValue() {
        String str;
        operation = operation.toLowerCase();
        switch (operation) {
            case "delete":
                str = "deleted";
                break;
            case "update":
                str = "updated";
                break;
            case "read":
                str = "retrieved";
                break;
            default:
                str = "created";
                break;
        }
        return str;
    }
}
