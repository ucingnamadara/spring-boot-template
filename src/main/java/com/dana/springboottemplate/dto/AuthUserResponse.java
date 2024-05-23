package com.dana.springboottemplate.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthUserResponse {

    private User user;

    private Token token;

    @Data
    @Builder
    public static class User{
        private String id;
        private String email;
        private String phoneNumber;
    }


    @Data
    @Builder
    public static class Token{
        private String accessToken;
    }

}
