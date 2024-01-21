/*
 * Copyright (C) 2018 The OmniROM Project
 * Copyright (C) 2022 The Nameless-AOSP Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.awaken.device.OnePlusSettings.Tiles;

import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.UserHandle;

import com.awaken.device.OnePlusSettings.MainSettings;
import com.awaken.device.OnePlusSettings.ModeSwitch;
import com.awaken.device.OnePlusSettings.R;
import com.awaken.device.OnePlusSettings.Services.HBMModeService;
import com.awaken.device.OnePlusSettings.Utils.SwitchUtils;

public class HBMModeTileService extends ModeSwitchTileService {

    private ModeSwitch mSwitch = SwitchUtils.getHBMModeSwitch(this);
    private Intent mHbmIntent;

    @Override
    protected ModeSwitch getModeSwitch() {
        return mSwitch;
    }

    @Override
    protected String getKey() {
        return MainSettings.KEY_HBM_SWITCH;
    }

    @Override
    protected Icon getIcon() {
        return Icon.createWithResource(this, R.drawable.ic_hbm_mode);
    }

    @Override
    protected boolean isSupported() {
        return getModeSwitch().isSupported();
    }

    @Override
    public void onStartListeningExt() {
        if (!enabled) tryStopService();
    }

    @Override
    public void onClickExt() {
        if (!enabled) {
            mHbmIntent = new Intent(this, HBMModeService.class);
            this.startServiceAsUser(mHbmIntent, UserHandle.CURRENT);
        }
    }

    private void tryStopService() {
        if (mHbmIntent == null) return;
        this.stopServiceAsUser(mHbmIntent, UserHandle.CURRENT);
        mHbmIntent = null;
    }
}
