package com.engapp.CollectionService.utils;

import lombok.Getter;

@Getter
public enum TopicEnum {
        COLLECTION_DOWNLOAD("collection-download")
    ;
    private String topic;

    TopicEnum(String topic) {
        this.topic = topic;
    }
}
