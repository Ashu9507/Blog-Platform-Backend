package org.ashutosh.blogplatform.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String name;
    private String bio;
    private String website;
    private String twitter;
    private String linkedin;
}
