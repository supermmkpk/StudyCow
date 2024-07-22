package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_freind")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@IdClass(FriendId.class)
public class Friend {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID1")
    @NotNull
    private User user1;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID2")
    @NotNull
    private User user2;

    @Column(name = "FREIND_DATE")
    @CreationTimestamp
    private LocalDateTime friendDate;

}
