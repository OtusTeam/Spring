package ru.otus.example.springmail_integration_demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "activity_stat")
public class ActivityStatElem {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JsonProperty("Имя пользователя")
    @Column(name = "app_user_name")
    private String appUserName;

    @JsonProperty("Тип активности")
    @Column(name = "activity_type")
    private String activityType;

    @JsonProperty("Количество")
    @Column(name = "activities_count")
    private long activitiesCount;

    public ActivityStatElem(String appUserName, String activityType, long activitiesCount) {
        this.appUserName = appUserName;
        this.activityType = activityType;
        this.activitiesCount = activitiesCount;
    }
}
