/*
 * -----------------------------------
 *  Project: poultry-system
 *  Author: chappyd-0
 *  Date: 6/10/25
 * -----------------------------------
 */
package com.blacksystem.poultry_system.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class MessageService {

    private final MessageSource messageSource;
    public MessageService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String get(String key) {
        return messageSource.getMessage(key, null, Locale.getDefault());
    }

    public String get(String key, Object[] args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }
}
