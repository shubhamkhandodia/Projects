import React ,{useState} from 'react';
import { TextInput,Text, View} from 'react-native';

const Pizzatranslator = (props) =>
{
  const [getText,setText] = useState('');

  return (
    <View style={{padding: 10}}>

      <TextInput style={{height: 40}} placeholder="Type here to translate!" onChangeText={getText => setText(getText)} defaultValue={getText} />

      <Text style={{padding: 10, fontSize: 42}}>
        {getText.split(' ').map((word) => word && '🍕').join(' ')}
      </Text>

    </View>
  );
}

export default Pizzatranslator;