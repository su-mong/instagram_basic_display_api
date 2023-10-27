package com.ryderchen.plugins.instagram_basic_display_api

import android.util.Log
import androidx.annotation.NonNull
import com.ryderchen.plugins.instagram_basic_display_api.utils.getKoinInstance

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding

/** InstagramBasicDisplayApiPlugin */
class InstagramBasicDisplayApiPlugin : FlutterPlugin, ActivityAware {

  private val TAG = javaClass.name

  private lateinit var methodCallHandler: MethodCallHandlerImpl
  private val instagramBasicDisplayApi: InstagramBasicDisplayApi = getKoinInstance()

  override fun onAttachedToEngine(@NonNull flutterPluginBinding: FlutterPlugin.FlutterPluginBinding) {
    Log.d(TAG, "onAttachedToEngine #1")
    methodCallHandler = MethodCallHandlerImpl(instagramBasicDisplayApi)
    Log.d(TAG, "onAttachedToEngine #2")
    methodCallHandler.startListening(flutterPluginBinding.binaryMessenger)
    Log.d(TAG, "onAttachedToEngine #3")
  }

  override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
    methodCallHandler.stopListening()
  }

  override fun onAttachedToActivity(binding: ActivityPluginBinding) {
    instagramBasicDisplayApi.setActivityPluginBinding(binding)
  }

  override fun onDetachedFromActivity() {
    instagramBasicDisplayApi.detachActivity()
  }

  override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
    onAttachedToActivity(binding)
  }

  override fun onDetachedFromActivityForConfigChanges() {
    onDetachedFromActivity()
  }
}
