package io.flutter.plugins;

import io.flutter.plugin.common.PluginRegistry;
import com.freelogic.flutter_sunyard_printer.FlutterSunyardPrinterPlugin;

/**
 * Generated file. Do not edit.
 */
public final class GeneratedPluginRegistrant {
  public static void registerWith(PluginRegistry registry) {
    if (alreadyRegisteredWith(registry)) {
      return;
    }
    FlutterSunyardPrinterPlugin.registerWith(registry.registrarFor("com.freelogic.flutter_sunyard_printer.FlutterSunyardPrinterPlugin"));
  }

  private static boolean alreadyRegisteredWith(PluginRegistry registry) {
    final String key = GeneratedPluginRegistrant.class.getCanonicalName();
    if (registry.hasPlugin(key)) {
      return true;
    }
    registry.registrarFor(key);
    return false;
  }
}
