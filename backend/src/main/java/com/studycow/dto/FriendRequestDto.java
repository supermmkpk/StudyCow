package com.studycow.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FriendRequestDto {
    private int id;
    private int counterpartUserId;
    private int requestStatus;
    private LocalDateTime requestDate;
    private LocalDateTime requestUpdateDate;
}
