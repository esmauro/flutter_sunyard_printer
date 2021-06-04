#import "FlutterSunyardPrinterPlugin.h"
#if __has_include(<flutter_sunyard_printer/flutter_sunyard_printer-Swift.h>)
#import <flutter_sunyard_printer/flutter_sunyard_printer-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_sunyard_printer-Swift.h"
#endif

@implementation FlutterSunyardPrinterPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterSunyardPrinterPlugin registerWithRegistrar:registrar];
}
@end
