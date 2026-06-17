package org.ashutosh.blogplatform.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponse {

    private Long blogId;
    private long totalLikes;
    private boolean likedByCurrentUser;
}
