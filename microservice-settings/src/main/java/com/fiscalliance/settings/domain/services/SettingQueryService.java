package com.fiscalliance.settings.domain.services;

import com.fiscalliance.settings.domain.models.aggregates.Setting;
import com.fiscalliance.settings.domain.models.queries.GetAllSettingsQuery;
import com.fiscalliance.settings.domain.models.queries.GetSettingByIdQuery;

import java.util.List;
import java.util.Optional;

public interface SettingQueryService {

    Optional<Setting> handle(GetSettingByIdQuery query);

    List<Setting> handle(GetAllSettingsQuery query);
}
