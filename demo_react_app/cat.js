import React ,{useState} from 'react';
import { Button,Text, View} from 'react-native';

const Cat = (props) => 
{
  const [getisHungry, setIsHungry] = useState(true);

  return (
    <View>

      <Text>
        I am {props.name}, and I am {getisHungry ? "hungry" : "full"}!
      </Text>

      <Button onPress={() => { setIsHungry(false); }} disabled={!getisHungry} title={getisHungry ? "Pour me some milk, please!" : "Thank you!"} />

    </View>
  );
}

export default Cat;