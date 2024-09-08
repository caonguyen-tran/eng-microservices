package com.engapp.CollectionService.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DownloadEvent {
    private String collectionId;
    private String userId;
}
