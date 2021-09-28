package com.rokomari.videoapp.idm.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer status;

}
