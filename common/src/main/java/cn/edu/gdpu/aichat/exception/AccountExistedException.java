package cn.edu.gdpu.aichat.exception;

/**
 * 账号被锁定异常
 */
public class AccountExistedException extends BaseException {

    public AccountExistedException() {
    }

    public AccountExistedException(String msg) {
        super(msg);
    }

}
