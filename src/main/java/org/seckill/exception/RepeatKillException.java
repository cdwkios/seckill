package org.seckill.exception;

/**
 * Created by jianjun-wu on 2017/2/24.
 */
public class RepeatKillException extends SeckillException
{


    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
