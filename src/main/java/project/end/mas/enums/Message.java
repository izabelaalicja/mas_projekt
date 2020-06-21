package project.end.mas.enums;

import lombok.Getter;

public enum Message {
    FAILED_JOIN("failed"),
    SUCCESS_JOIN("success"),
    HORSES_NONE("horses-none"),
    COMPETITIONS_NONE("competitions-none");

    @Getter
    String message;

    Message(String message) {
        this.message = message;
    }
}
