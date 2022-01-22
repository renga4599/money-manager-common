package com.renga.money.manager.common.models;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Data
public class Login {
    private String email;
    private String password;
}
