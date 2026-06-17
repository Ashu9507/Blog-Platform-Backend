package org.ashutosh.blogplatform.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserProfileResponse {

    private Long id;
    private String name;
    private String email;

    private String bio;

    private String profilePicture;

    private String website;

    private String twitter;

    private String linkedin;
}
