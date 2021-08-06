package com.cewb.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestErrorResponse {
	
	private @NonNull Integer status;
    private @NonNull String message;
    private @NonNull Long timeStamp;
    
}
