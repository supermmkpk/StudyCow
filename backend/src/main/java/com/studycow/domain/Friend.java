package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 친구 도메인 클래스
 * @author 채기훈
 * @since  JDK17
 */


@Entity
@Table(name = "t_friend")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FriendId.class)
public class Friend {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID1",nullable = false)
    @NotNull
    private User user1;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID2",nullable = false)
    @NotNull
    private User user2;

    @Column(name = "FRIEND_DATE")
    @CreationTimestamp
    private LocalDateTime friendDate;

}

/* 임시 회원 더미데이터
INSERT INTO `t_user` (`USER_NAME`, `USER_EMAIL`, `USER_PASSWORD`, `USER_PUBLIC`, `USER_THUMB`, `GRADE_CODE`, `USER_EXP`, `USER_JOIN_DATE`, `USER_UPDATE_DATE`, `USER_NICKNAME`, `USER_BIRTHDAY`)
VALUES
	('John Doe', 'john.doe@example.com', 'password123', 1, 'profile_pic.jpg', 1, 500, '2023-04-01 10:00:00', '2023-04-01 10:00:00', 'johndoe', '1990-05-15'),
	('Jane Smith', 'jane.smith@example.com', 'password456', 0, NULL, 2, 200, '2023-04-02 14:30:00', '2023-04-02 14:30:00', 'janesmith', '1985-11-20'),
	('Michael Johnson', 'michael.johnson@example.com', 'password789', 1, 'profile_pic2.jpg', 3, 1000, '2023-04-03 08:45:00', '2023-04-03 08:45:00', 'mjohnson', '1992-07-01'),
	('Emily Davis', 'emily.davis@example.com', 'password321', 0, NULL, 1, 50, '2023-04-04 16:20:00', '2023-04-04 16:20:00', 'emilyd', '1998-03-10'),
	('David Lee', 'david.lee@example.com', 'password654', 1, 'profile_pic3.jpg', 2, 300, '2023-04-05 11:55:00', '2023-04-05 11:55:00', 'davidlee', '1993-09-25')
;

SELECT * FROM t_user;
 */
