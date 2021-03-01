import { requireNativeComponent, ViewStyle } from 'react-native';

type AutocompleteInputProps = {
  color: string;
  style: ViewStyle;
};


export const AutocompleteInputViewManager = requireNativeComponent<AutocompleteInputProps>(
  'AutocompleteInputView'
);

export default AutocompleteInputViewManager;
