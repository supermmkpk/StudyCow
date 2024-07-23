package com.studycow.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_plan", uniqueConstraints =
@UniqueConstraint(name = "UNI_USER_DATE", columnNames = {"USER_ID", "PROC_DATE"}))
public class UserSubjectPlan {
}
