package com.cewb.app.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
@Setter
public class ExceptionCatcher extends Exception{

    private String message;
}
