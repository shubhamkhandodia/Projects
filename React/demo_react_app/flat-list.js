import React from 'react';
import { Text, View, FlatList} from 'react-native';
import styles from './App';

const FlatListBasics = () => 
{
  return (
    <View style={styles.container}>

      <FlatList
        data={[
          {key: 'Devin'},
          {key: 'Dan'},
          {key: 'Dominic'},
          {key: 'Jackson'},
          {key: 'James'},
          {key: 'Joel'},
          {key: 'John'},
          {key: 'Jillian'},
          {key: 'Jimmy'},
          {key: 'Julie'},
          {key: 'Andrew'},
          {key: 'Kyle'},
          {key: 'Stan'},
          {key: 'Eric'},
          {key: 'Kenny'},
        ]}
        renderItem={ ({item}) => <Text style={styles.item}> {item.key} </Text> }
      />

    </View>
  );
}

export default FlatListBasics;