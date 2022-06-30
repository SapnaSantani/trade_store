package com.example.tradestore.exception;

/**
 * @author Sapna Girdhani
 */
public class InvalidTradeException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private final String msg;

    public InvalidTradeException(String message) {
        this.msg = message;
    }

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
}
