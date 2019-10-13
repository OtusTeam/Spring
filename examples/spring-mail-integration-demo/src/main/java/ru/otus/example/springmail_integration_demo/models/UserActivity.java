package ru.otus.example.springmail_integration_demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "app_users_activity")
public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "activity_time")
    private LocalDateTime activityTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "activity_type_id")
    private ActivityType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public UserActivity(ActivityType type, AppUser appUser) {
        this.id = id;
        this.type = type;
        this.appUser = appUser;
        this.activityTime = LocalDateTime.now();
    }
}
