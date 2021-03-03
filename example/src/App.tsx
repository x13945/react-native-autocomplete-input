import * as React from 'react';

import { StyleSheet, View } from 'react-native';
import AutoCompleteInputView from 'react-native-autocomplete-input';

export default function App() {
  return (
    <View style={styles.container}>
      <AutoCompleteInputView
        suggestions={['android', 'apple', 'bob', 'banana']}
        onChangeText={(value: string) => {
          console.log(1, value);
        }}
        textColor={'#f00'}
        style={styles.box}
      />
      <View style={{ flexDirection: 'row' }}>
        <AutoCompleteInputView
          onChangeText={(value: string) => {
            console.log(2, value);
          }}
          suggestions={['android', 'apple', 'bob', 'banana']}
          textColor={'#f00'}
          style={[styles.box, { flex: 1 }]}
        />
        <AutoCompleteInputView
          onChangeText={(value: string) => {
            console.log(3, value);
          }}
          suggestions={['android', 'apple', 'bob', 'banana']}
          textColor={'#f00'}
          style={[styles.box, { flex: 1 }]}
        />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    paddingHorizontal: 10,
  },
  box: {
    alignSelf: 'stretch',
    height: 40,
    marginHorizontal: 8,
    borderRadius: 5,
    borderWidth: 1,
    borderColor: '#f00',
    // backgroundColor: 'green'
  },
});
