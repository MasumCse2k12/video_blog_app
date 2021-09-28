package com.rokomari.videoapp.video.payload;

import com.rokomari.videoapp.common.enums.ReactionStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Videos implements Serializable {
    protected Integer id;
    private String url;
    private String description;
    private Integer views;
    private String uploadedAt;
    private Integer uploadedBy;
    private String uploadedUser;
    private ReactionStatus status;
    private List<String> likedUser;
    private List<String> dislikeUser;
    private Integer liked;
    private Integer disliked;
    private Boolean isOwnLike;
    private Boolean isOwnDislike;
}
