package com.freelogic.flutter_sunyard_printer;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.socsi.smartposapi.printer.Align;
import com.socsi.smartposapi.printer.FontLattice;
import com.socsi.smartposapi.printer.FontType;
import com.socsi.smartposapi.printer.PrintRespCode;
import com.socsi.smartposapi.printer.Printer2;
import com.socsi.smartposapi.printer.TextEntity;
import com.socsi.utils.HexUtil;
import com.socsi.utils.StringUtil;

import com.socsi.smartposapi.DeviceMaster;

public class FlutterSunyardPrinterPlugin implements FlutterPlugin, MethodCallHandler {
  private MethodChannel channel;
  private FlutterSunyardPrinterModule flutterSunyardPrinterModule;

  private String PRINT_IMAGE = "printImage";
  private String PRINT_TEXT = "printText";

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getFlutterEngine().getDartExecutor(), "flutter_sunyard_printer");
    channel.setMethodCallHandler(this);
    flutterSunyardPrinterModule = new FlutterSunyardPrinterModule();
    flutterSunyardPrinterModule.initAidl(flutterPluginBinding.getApplicationContext());
  }

  // This static function is optional and equivalent to onAttachedToEngine. It
  // supports the old pre-Flutter-1.12 Android projects. You are encouraged to
  // continue supporting plugin registration via this function while apps migrate
  // to use the new Android APIs post-flutter-1.12 via
  // https://flutter.dev/go/android-project-migration.
  //
  // It is encouraged to share logic between onAttachedToEngine and registerWith
  // to keep them functionally equivalent. Only one of onAttachedToEngine or
  // registerWith will be called depending on the user's project.
  // onAttachedToEngine or registerWith must both be defined in the same class.
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_sunyard_printer");
    channel.setMethodCallHandler(new FlutterSunyardPrinterPlugin());
    //flutterSunyardPrinterModule = new FlutterSunyardPrinterModule();
    //flutterSunyardPrinterModule.initAidl(registrar.context());
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    if (call.method.equals(PRINT_TEXT)) {
      String text = call.argument("text");      
      flutterSunyardPrinterModule.printText(text);
      result.success(null);
    } else if (call.method.equals(PRINT_IMAGE)) {
      String base64 = call.argument("base64");
      flutterSunyardPrinterModule.printImage(base64);
      result.success(null);
    } else {
      result.notImplemented();
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }

}
