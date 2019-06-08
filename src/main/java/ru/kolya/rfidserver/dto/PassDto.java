package ru.kolya.rfidserver.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class PassDto {
    private final LocalDateTime time;
    private final String userCardNumber;
}
