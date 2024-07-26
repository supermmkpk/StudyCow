package com.studycow.dto.user;

import com.studycow.domain.UserGrade;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RegisterRequestDto {
    @NotNull
    @NotBlank
    private String userEmail;

    @NotNull
    @NotBlank
    private String userPassword;

    @NotNull
    @Schema(hidden = true)
    private int userPublic = 0;

    @Schema(hidden = true)
    private String userThumb;

    @Schema(hidden = true)
    private UserGrade userGrade;

    @NotNull
    @Schema(hidden = true)
    private int userExp = 0;

    @NotNull
    @Schema(hidden = true)
    private LocalDateTime userJoinDate = LocalDateTime.now(); ;

    @NotNull
    @Schema(hidden = true)
    private LocalDateTime userUpdateDate = LocalDateTime.now();

    @NotNull
    @NotBlank
    private String userNickName;

}
