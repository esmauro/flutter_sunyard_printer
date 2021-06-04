/*
 * flutter_sunmi_printer
 * Created by Andrey U.
 * 
 * Copyright (c) 2020. All rights reserved.
 * See LICENSE for distribution and usage details.
 */

import 'dart:async';
import 'dart:convert';
import 'package:flutter/services.dart';
import 'package:flutter_sunyard_printer/src/enums.dart';

class SunyardPrinter {
  static const String PRINT_FIXED_TEXT = "printFixedText";
  static const String PRINT_FIXED_IMAGE = "printFixedImage";

  static const MethodChannel _channel =
      const MethodChannel('flutter_sunyard_printer');

  /// Print horizontal full width separator
  static Future<String> fixedText({
    String ch = '-',
    int len = 31,
    linesAfter = 0,
  }) async {
    await _channel.invokeMethod(PRINT_FIXED_TEXT);
  }

  static Future<String> fixedImage(String base64) async {
    await _channel.invokeMethod(PRINT_FIXED_IMAGE, {
      "base64": base64,
    });
  }
/*
  static Future<void> image(
    String base64, {
    SunmiAlign align: SunmiAlign.center,
  }) async {
    await _channel.invokeMethod(PRINT_IMAGE, {
      "base64": base64,
      "align": align.value,
    });
  }
  */
}
