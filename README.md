# react-native-autocomplete-input

This is an auto complete input component for react-native. For the native, Android use `AutoCompleteTextView`, iOS use `UITextField`.

For now, we only implement the Android, iOS is on the way.

## Installation

```sh
npm install react-native-autocomplete-input
```

## Usage

```js
import AutoCompleteInputView from "react-native-autocomplete-input";

// ...

      <AutoCompleteInputView
        suggestions={['android', 'apple', 'bob', 'banana']}
        onChangeText={(value: string) => {
          console.log(1, value);
        }}
        textColor={'#f00'}
        style={styles.box}
      />

```

## Props

| Prop             | Type            | Description                                                  |
| ---------------- | --------------- | ------------------------------------------------------------ |
| suggestions      | string array    | An array with suggestion items                               |
| textColor        | string of rgba  | Text color of the input view                                 |
| fontSize         | number          | Font size for the input view                                 |
| onChangeText     | Function        | Callback that is called when the text input's text changes. Changed text is passed as an argument to the callback handler |
| defaultValue     | string          | Default value for the input view                             |
| placeholderColor | string of color | Text color of the input view holder                          |



## TODO

- iOS support
- Custom style for the suggest item.

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
