package capstone.capstone01.domain.user.domain;

import capstone.capstone01.domain.user.domain.enums.Gender;
import capstone.capstone01.domain.user.domain.enums.UserRole;
import capstone.capstone01.domain.user.domain.enums.UserState;
import capstone.capstone01.global.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(name = "email", unique = true, length = 30, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "nickname", unique = true, nullable = false, length = 8)
    private String nickname;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "profileImageUrl", length = 500)
    private String profileImageUrl;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserRole role = UserRole.USER;

    @Column(name = "userState", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserState userState = UserState.ACTIVE;

    public void updateNickname(final String newNickname) {
        if (newNickname != null) {
            this.nickname = newNickname;
        }
    }

    public void updateProfileImageUrl(final String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

}