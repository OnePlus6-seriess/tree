/*
 * Copyright (C) 2021-2022 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package com.awaken.device.OnePlusSettings.Services

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import android.util.Log

import com.awaken.device.OnePlusSettings.Utils.DozeUtils
import com.awaken.device.OnePlusSettings.Doze.PickupSensor

class DozeService : Service() {
    private lateinit var pickupSensor: PickupSensor

    private val screenStateReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            when (intent.action) {
                Intent.ACTION_SCREEN_ON -> onDisplayOn()
                Intent.ACTION_SCREEN_OFF -> onDisplayOff()
            }
        }
    }

    override fun onCreate() {
        Log.d(TAG, "Creating service")
        pickupSensor = PickupSensor(
            this,
            "android.sensor.tilt_detector",
            0.0f
        )

        val screenStateFilter = IntentFilter()
        screenStateFilter.addAction(Intent.ACTION_SCREEN_ON)
        screenStateFilter.addAction(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenStateReceiver, screenStateFilter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiver(screenStateReceiver)
        pickupSensor.disable()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun onDisplayOn() {
        if (DozeUtils.isPickUpEnabled(this)) {
            pickupSensor.disable()
        }
    }

    private fun onDisplayOff() {
        if (DozeUtils.isPickUpEnabled(this)) {
            pickupSensor.enable()
        }
    }

    companion object {
        private const val TAG = "DozeService"
    }
}
