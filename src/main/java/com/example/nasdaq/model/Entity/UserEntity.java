package com.example.nasdaq.model.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "UserEntity")
@Table(name = "user")
public class UserEntity {
    @Id
    @NotBlank
    @Column(unique = true)
    private String userId;

    @NotBlank
    private String userName;
    
    @NotBlank
    private String userPw;

    @Email
    @Column(unique = true)
    private String userEmail;

    private String userRole;

    @Column(columnDefinition = "tinyint(1) default 0")
    private Boolean isLogin;
}
