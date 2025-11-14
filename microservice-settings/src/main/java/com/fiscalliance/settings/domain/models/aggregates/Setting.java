package com.fiscalliance.settings.domain.models.aggregates;

import com.fiscalliance.settings.domain.models.commands.CreateSettingCommand;
import com.fiscalliance.settings.domain.models.valueobjects.DarkMode;
import com.fiscalliance.settings.domain.models.valueobjects.Language;
import com.fiscalliance.settings.domain.models.valueobjects.NotificationsEnabled;
import com.fiscalliance.settings.domain.models.valueobjects.UserId;
import com.fiscalliance.shared.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class Setting extends AuditableAbstractAggregateRoot<Setting> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Embedded
    @AttributeOverride(name = "userId", column = @Column(name = "user_id", unique = true, nullable = false))
    private UserId userId;

    @Getter
    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "language", length = 10))
    private Language language;

    @Getter
    @Embedded
    @AttributeOverride(name = "enabled", column = @Column(name = "dark_mode"))
    private DarkMode darkMode;

    @Getter
    @Embedded
    @AttributeOverride(name = "enabled", column = @Column(name = "notifications_enabled"))
    private NotificationsEnabled notificationsEnabled;

    protected Setting() {
        // Constructor requerido por JPA
    }

    public Setting(CreateSettingCommand command) {
        this.userId = new UserId(command.userId());
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
