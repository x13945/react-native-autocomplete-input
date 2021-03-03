# react-native-autocomplete-input

AutoComplete component for react-native

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

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
