package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;

@Entity
@Table(name = "t_freind_request")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FriendRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FREIND_REQUEST_ID")
    @NotNull
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FROM_USER_ID")
    @NotNull
    private User fromUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TO_USER_ID")
    @NotNull
    private User toUser;

    @Column(name = "REQUEST_STATUS")
    @NotNull
    @ColumnDefault("0")
    private int requestStatus;

    @Column(name = "REQUEST_DATE")
    @NotNull
    @CreationTimestamp
    private Timestamp requestDate;

    @Column(name = "REQUEST_UPDATE_DATE")
    @NotNull
    @UpdateTimestamp
    private Timestamp requestUpdateDate;

}
