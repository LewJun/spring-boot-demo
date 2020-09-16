package com.example.lewjun.common;

public class BussException extends RuntimeException {
    private EnumApiResultStatus status;

    private BussException() {
    }

    private BussException(final EnumApiResultStatus status, final Throwable throwable) {
        super(throwable);
        this.status = status;
    }

    public static BussException of() {
        return of(EnumApiResultStatus.FAIL);
    }

    public static BussException of(final EnumApiResultStatus status) {
        return of(status, null);
    }

    public static BussException of(final Throwable throwable) {
        return of(EnumApiResultStatus.FAIL, throwable);
    }

    public static BussException of(final EnumApiResultStatus status, final Throwable throwable) {
        return new BussException(status, throwable);
    }

    public EnumApiResultStatus getStatus() {
        return status;
    }
}
