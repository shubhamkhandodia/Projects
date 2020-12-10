import React , {useState} from 'react';
import foodData from './food_item_list';
import FoodPill from './food_pill';



function FoodTable(props) 
{
  return props.fooddata.map((fooditem) => (

      <FoodPill key={fooditem.name} name={fooditem.name} measure={fooditem.measure} calories={fooditem.calories} onFoodPillClick={props.foodPillClickHandler} /> 

      ));
}


const FoodShower = () =>
{
	const[itemseaten,additemseaten] = useState("");
	const[currentCalories,addCalories] = useState(0);

	

	function foodPillClickHandler(props)
	{

		if(itemseaten == "")
		{
			additemseaten(props.name);
		}
		else
		{
			additemseaten(itemseaten+" and "+props.name);
		}

		addCalories(currentCalories+props.calories);
    	
  };

  
  	return (

  	<div>
  		<FoodTable fooddata = {foodData} foodPillClickHandler = {foodPillClickHandler} />
  		<TotalCalory currentCalories={currentCalories} foodclicked ={itemseaten} />

  	</div>

  		
  	);
  }
 

function TotalCalory(props) 
{
  return (<h2> Your total calorie for today: {props.currentCalories} after eating {props.foodclicked}</h2>);
}


export default FoodShower;