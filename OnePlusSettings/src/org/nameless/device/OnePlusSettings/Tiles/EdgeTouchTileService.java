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

public class EdgeTouchTileService extends ModeSwitchTileService {

    private ModeSwitch mSwitch = SwitchUtils.getEdgeModeSwitch(this);

    @Override
    protected ModeSwitch getModeSwitch() {
        return mSwitch;
    }

    @Override
    protected String getKey() {
        return MainSettings.KEY_EDGE_TOUCH;
    }

    @Override
    protected Icon getIcon() {
        return Icon.createWithResource(this, R.drawable.ic_edge_touch);
    }

    @Override
    protected boolean isSupported() {
        return getModeSwitch().isSupported();
    }
}
