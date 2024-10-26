package capstone.capstone01.domain.user.domain;

import capstone.capstone01.domain.storage.domain.FileSaveInfo;
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

    @Column(name = "email", unique = true, length = 50, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "name", length = 30, nullable = false)
    private String name;

    @Column(name = "nickname", unique = true, nullable = false, length = 15)
    private String nickname;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profileImageId")
    private FileSaveInfo profileImage;

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

    public void updateProfileImage(final FileSaveInfo profileImage) {
        this.profileImage = profileImage;
    }

    public void setPassword(String encode) {
    }
}