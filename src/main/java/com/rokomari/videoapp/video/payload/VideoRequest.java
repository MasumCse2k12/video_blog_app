package com.rokomari.videoapp.video.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class VideoRequest extends SearchCriteria {
    private Integer id;
    private Integer status;
    private Integer userId;
    private String url;
    private String description;
}
