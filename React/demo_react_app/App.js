import { StatusBar } from 'expo-status-bar';
import React, {useState , useEffect} from 'react';
import { TextInput, Button, ImageBackground, StyleSheet, Text, View, FlatList} from 'react-native';
import FoodShower from './food_shower';
import Addingcomponent from './Adding_function_file';
import FlatListBasics from './flat-list';
import Pizzatranslator from './pizza-translator';
import emojidictionary from "./emojis"
import Cat from './cat';
import options from './options';
import { Form,Dropdown } from 'react-bootstrap';
import CanvasJSReact from './canvasjs.react';
import { ActivityIndicator} from 'react-native';



var fact = "hello there";

var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;

var mycolor1 = "lightblue";
var mycolor2 = "lightpink";

const List_basics_component = () =>
{

  return (
    <View style={{padding: 10}}>

      <FlatListBasics />

    </View>
  );
}

function MyComponent() {
  const [error, setError] = useState(null);
  const [isLoaded, setIsLoaded] = useState(false);
  const [items, setItems] = useState([]);

  // Note: the empty deps array [] means
  // this useEffect will run once
  // similar to componentDidMount()
  useEffect(() => {
    fetch("https://api.thedogapi.com/v1/breeds?attach_breed=0")
      .then(res => res.json())
      .then(
        (result) => {
          setIsLoaded(true);
          setItems(result);
        },
        // Note: it's important to handle errors here
        // instead of a catch() block so that we don't swallow
        // exceptions from actual bugs in components.
        (error) => {
          setIsLoaded(true);
          setError(error);
        }
      )
  }, [])




class Displayitems extends React.Component
  {

    constructor(props) 
    {
      super(props);
      this.displaydetails = this.displaydetails.bind(this);
      this.state = {dogclicked: null};
    }

    displaydetails (props)
    {
      this.setState({dogclicked: props});
    }


    render()
    {
      
        if (this.state.dogclicked!=null) 
        {   
          return(

          <>

          <ol>
            {this.props.items.slice(0, 20).map((item) => (
              <>
              <li key={item.id} onClick = {() => this.displaydetails(item)}>
                {item.name}
                <ul>
                  <li>
                    <img src={item.image.url} alt={item.image.id} height = "100" width = "100"></img>
                  </li>
                </ul>
              </li>
              </>
            ))}
          </ol>

          <h1>{this.state.dogclicked.life_span}</h1>
          </>

          );
        } 
        else 
        {      
          return(

          <>

          <ol>
            {this.props.items.slice(0, 20).map((item) => (
              <>
              <li key={item.id} onClick = {() => this.displaydetails(item)}>
                {item.name}
                <ul>
                  <li>
                    <img src={item.image.url} alt={item.image.id} height = "100" width = "100"></img>
                  </li>
                </ul>
              </li>
              </>
            ))}
          </ol>

          <h1>"Dog could not be found"</h1>
          </>

          );
        }
        
    }
    
    
  }

  if (error) {
    return <div>Error: {error.message}</div>;
  } else if (!isLoaded) {
    return <div>Loading...</div>;
  } else {
    return (

      <Displayitems items = {items}/>
      
    );
  }
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

const Buttoncomponent = (props) =>{

  const [myname, setName] = useState("");
  const [myCount, setCount] = useState(0);

  

  function mychangehandler(e)
  {
      setName(myname+e.target.value);
  }

  function myclickhandler()
  {
    setCount(myCount+1);
  }
  return(
    <div>
      <input type="text" onChange ={mychangehandler}></input>
    <button onClick={() => myclickhandler()}>Click me</button>
    <p style = {{color:mycolor2}}>{myname}</p>
    <p>{myCount}</p>
    </div>
    
  );
}

const Emojilist = () =>{
  
  const [myemoji, setemoji] = useState("");

  function emojiclickhandler(props)
  {
    setemoji(props.emoji);
  }

  return (
    <div>
     
       {emojidictionary.map((item) => <button key={item.name}><span onClick = {() => emojiclickhandler(item)}>{item.name}</span></button>)}
     <h2>{myemoji}</h2>
     </div>
  );
}

const data = () => {
  const [isLoading, setLoading] = useState(true);
  const [data, setData] = useState([]);

  useEffect(() => {
    fetch('https://api.covid19api.com/all')
      .then((response) => response.json())
      .then((json) => setData(json.movies))
      .catch((error) => console.error(error))
      .finally(() => setLoading(false));
  }, []);

  return (
    <View style={{ flex: 1, padding: 24 }}>
      {isLoading ? <ActivityIndicator/> : (
        <FlatList data={data}
          keyExtractor={({ id }, index) => id}
          renderItem={({ item }) => (
            <Text>{item.title}, {item.releaseYear}</Text>
          )}
        />
      )}
    </View>
  );
};


const Cafe = () => 
{
  return (
    <>
      
      	{/*<Cat name="Munkustrap" />
                    <Cat name="Spot" />
                    <Pizzatranslator />
              
                    <List_basics_component />
              
                    <Enternumber />
              
                    <FoodShowerfunction />
              
                    <Buttoncomponent name = "i am getting displayed due to this"></Buttoncomponent>
                    <Emojilist />*/}

            <MyComponent />

    </>
  );
}

const styles = StyleSheet.create({
  container: {
   flex: 1,
   paddingTop: 22,
   justifyContent: 'center'
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
  },
  
  buttonOuterLayout: {
    flex: 1,
    flexDirection: 'column',
    justifyContent: 'center',
    alignItems: 'center',
  },
  
});



export default Cafe;