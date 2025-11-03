package com.fiscalliance.config.domain.models.aggregates;

import com.fiscalliance.config.domain.models.commands.CreateSettingCommand;
import com.fiscalliance.config.domain.models.valueobjects.DarkMode;
import com.fiscalliance.config.domain.models.valueobjects.Language;
import com.fiscalliance.config.domain.models.valueobjects.NotificationsEnabled;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "settings")
public class Setting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Embedded
    private Language language;

    @Embedded
    private DarkMode darkMode;

    @Embedded
    private NotificationsEnabled notificationsEnabled;

    protected Setting() {}

    public Setting(Long userId, CreateSettingCommand command) {
        this.userId = userId;
        this.language = new Language(command.language());
        this.darkMode = new DarkMode(command.darkMode());
        this.notificationsEnabled = new NotificationsEnabled(command.notificationsEnabled());
    }

    public void update(CreateSettingCommand command) {
        this.language = new Language(command.language());
        this.darkMode = new DarkMode(command.darkMode());
        this.notificationsEnabled = new NotificationsEnabled(command.notificationsEnabled());
    }
}
