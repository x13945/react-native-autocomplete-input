import React, { Component } from 'react';
import { requireNativeComponent, ViewProps, View } from 'react-native';
import { omit } from 'lodash';

interface AutocompleteInputProps extends ViewProps {
  textColor?: string;
  placeholderColor?: string;
  suggestions: string[];
  fontSize?: number;
  onChangeText?: (value: string) => void;
}

const NativeView = requireNativeComponent<AutocompleteInputProps>(
  'AutoCompleteInputView'
);

interface S {}

class AutoCompleteInputView extends Component<AutocompleteInputProps, S> {
  constructor(props: AutocompleteInputProps) {
    super(props);
  }

  private _onChangeText = (event: any): void => {
    const text = event.nativeEvent.text;
    const { onChangeText } = this.props;
    onChangeText && onChangeText(text);
  };

  public render() {
    const { style } = this.props;
    const nativeProps = omit({ ...this.props }, ['style']);
    return (
      <View style={style}>
        <NativeView
          {...nativeProps}
          onChangeText={this._onChangeText}
          style={{ flex: 1, alignSelf: 'stretch' }}
        />
      </View>
    );
  }
}

export default AutoCompleteInputView;
