/*
 * Copyright (C) 2022 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.awaken.device.OnePlusSettings.Tiles;

import android.graphics.drawable.Icon;

import com.awaken.device.OnePlusSettings.MainSettings;
import com.awaken.device.OnePlusSettings.ModeSwitch;
import com.awaken.device.OnePlusSettings.R;
import com.awaken.device.OnePlusSettings.Utils.SwitchUtils;

public class GameModeTileService extends ModeSwitchTileService {

    private ModeSwitch mSwitch = SwitchUtils.getGameModeSwitch(this);

    @Override
    protected ModeSwitch getModeSwitch() {
        return mSwitch;
    }

    @Override
    protected String getKey() {
        return MainSettings.KEY_GAME_SWITCH;
    }

    @Override
    protected Icon getIcon() {
        return Icon.createWithResource(this, R.drawable.ic_game_mode);
    }

    @Override
    protected boolean isSupported() {
        return SwitchUtils.isGamingModeSupported() && getModeSwitch().isSupported();
    }
}
