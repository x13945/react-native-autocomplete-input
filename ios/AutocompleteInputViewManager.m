#import <React/RCTViewManager.h>

@interface AutocompleteInputViewManager : RCTViewManager
@end

@implementation AutocompleteInputViewManager

RCT_EXPORT_MODULE(AutoCompleteInputView)

- (UITextField *)view
{
  return [[UITextField alloc] init];
}

RCT_CUSTOM_VIEW_PROPERTY(textColor, NSString, UITextField)
{
    [view setTextColor:[self hexStringToColor:json]];
}

RCT_CUSTOM_VIEW_PROPERTY(fontSize, NSInteger, UITextField)
{
    [view setTextColor:[self hexStringToColor:json]];
}

- hexStringToColor:(NSString *)stringToConvert
{
  NSString *noHashString = [stringToConvert stringByReplacingOccurrencesOfString:@"#" withString:@""];
  NSScanner *stringScanner = [NSScanner scannerWithString:noHashString];

  unsigned hex;
  if (![stringScanner scanHexInt:&hex]) return nil;
  int r = (hex >> 16) & 0xFF;
  int g = (hex >> 8) & 0xFF;
  int b = (hex) & 0xFF;

  return [UIColor colorWithRed:r / 255.0f green:g / 255.0f blue:b / 255.0f alpha:1.0f];
}

@end
