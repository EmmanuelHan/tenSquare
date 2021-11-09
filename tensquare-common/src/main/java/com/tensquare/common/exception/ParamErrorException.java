package com.tensquare.common.exception;

import java.io.Serializable;

public class ParamErrorException extends RuntimeException {

    private static final long serialVersionUID = 6480141727967650507L;

    /**
     * Constructs a {@code ParamErrorException} with no detail message.
     */
    public ParamErrorException() {
        super();
    }

    /**
     * Constructs a {@code ParamErrorException} with the specified
     * detail message.
     *
     * @param   s   the detail message.
     */
    public ParamErrorException(String s) {
        super(s);
    }


}
