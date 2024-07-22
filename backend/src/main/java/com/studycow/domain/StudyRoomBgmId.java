package com.studycow.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Data
public class StudyRoomBgmId implements Serializable {
    private int id;
    private Long studyRoom;
}
