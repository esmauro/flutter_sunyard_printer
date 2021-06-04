# flutter_sunyard_printer

Plugin allows to print thermal receipts using Sunmi device with a built-in printer. Tested on Sunmi V2.

## Features

- `fixedText`: print text 
- `fixedImage`: print an image

## Getting Started

```dart
import 'package:flutter_sunyard_printer/flutter_sunyard_printer.dart';

// print a fixed text
SunyardPrinter.fixedText();

// print a local jpg asset
ByteData bytes = await rootBundle.load('assets/snes.jpg');
final buffer = bytes.buffer;
final imgData = base64.encode(Uint8List.view(buffer));
SunyardPrinter.fixedImage(imgData);
