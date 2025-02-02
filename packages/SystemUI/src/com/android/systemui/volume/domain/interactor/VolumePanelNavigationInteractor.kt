/*
 * Copyright (C) 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.systemui.volume.domain.interactor

import android.content.Context
import android.util.FeatureFlagUtils
import com.android.systemui.dagger.qualifiers.Application
import com.android.systemui.volume.domain.model.VolumePanelRoute
import com.android.systemui.volume.panel.shared.flag.VolumePanelFlag
import javax.inject.Inject

/** Provides navigation routes for Volume space. */
class VolumePanelNavigationInteractor
@Inject
constructor(
    @Application private val context: Context,
    private val volumePanelFlag: VolumePanelFlag,
) {

    fun getVolumePanelRoute(): VolumePanelRoute {
        return when {
            volumePanelFlag.canUseNewVolumePanel() -> VolumePanelRoute.COMPOSE_VOLUME_PANEL
            FeatureFlagUtils.isEnabled(
                context,
                FeatureFlagUtils.SETTINGS_VOLUME_PANEL_IN_SYSTEMUI
            ) -> VolumePanelRoute.SYSTEM_UI_VOLUME_PANEL
            else -> VolumePanelRoute.SETTINGS_VOLUME_PANEL
        }
    }

    fun getAppVolumePanelRoute(): VolumePanelRoute {
        return VolumePanelRoute.APP_VOLUME_PANEL
    }
}
