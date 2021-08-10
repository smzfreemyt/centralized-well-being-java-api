package com.cewb.app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ResponseMessage<M> {
    {
        init();
    }

    public ResponseMessage() {
        System.out.println("WOW");
    }

    private ResponseMessage<M> init() {
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully deleted");
        return this;
    }
}
