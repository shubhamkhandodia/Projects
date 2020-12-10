import { StatusBar } from 'expo-status-bar';
import React, {useState} from 'react';
import { TextInput, Button, ImageBackground, StyleSheet, Text, View, FlatList} from 'react-native';
import FoodShower from './food_shower';
import Addingcomponent from './Adding_function_file';
import FlatListBasics from './flat-list';
import Pizzatranslator from './pizza-translator';
import Cat from './cat';




const List_basics_component = () =>
{

  return (
    <View style={{padding: 10}}>

      <FlatListBasics />

    </View>
  );
}




const Enternumber = () =>
{
  const [myNumber, setNumber] = useState(0);
  return (
    <>
    <View>
      <Text> Enter Number:</Text>
      <TextInput keyboardType = 'numeric' style={styles.input} value={myNumber} onChangeText={(newValue)=> setNumber(newValue)}/>
    </View>
    
    <Addingcomponent number = {myNumber} />
    </>
  );
}

const FoodShowerfunction = () =>
{
  return(

    <FoodShower />

  );
}


const Cafe = () => 
{
  return (
    <>
      <Cat name="Munkustrap" />
      <Cat name="Spot" />
      <Pizzatranslator />

      <List_basics_component />

      <Enternumber />

      <FoodShowerfunction />

    </>
  );
}

const styles = StyleSheet.create({
  container: {
   flex: 1,
   paddingTop: 22
  },
  item: {
    padding: 10,
    fontSize: 18,
    height: 44,
  },
  input:{
    margin: 15,
    borderColor: 'black',
    borderWidth: 1
  }
});



export default Cafe;