package com.studycow.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_score")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserSubjectScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "score_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sub_code")
    @NotNull
    private SubjectCode subCode;

    @Column(name = "test_date")
    @NotNull
    private LocalDate testDate;

    @Column(name = "test_score")
    @NotNull
    private int testScore;

    @Column(name = "test_grade")
    private int testGrade;

    @Column(name = "score_update_date")
    @NotNull
    private LocalDateTime updateDate;

    /*public static UserSubjectScore createScore
            (User user, SubjectCode subCode, LocalDate testDate, int testScore, int testGrade){
        UserSubjectScore score = new UserSubjectScore();
        score.setUser(user);
        score.setSubCode(subCode);
        score.setTestDate(testDate);
        score.setTestScore(testScore);
        score.setTestGrade(testGrade);

        return score;
    }*/
}
