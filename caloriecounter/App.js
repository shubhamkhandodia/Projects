import React from "react";
import FoodPill from "./food-pill";
import calorieData from "./data/calorie-data";

function TotalCalory({ Calories , foodclicked }) {
  return <h2> Your total calorie for today: {Calories} after eating {foodclicked}</h2>;
}

function Header({ heading }) {
  return <h1>{heading} </h1>;
}

function FoodTable({calorieData, foodPillClickHandler }) {
  return calorieData.slice(0, 10).map(({ name, measure, calories }) => (

    //key attribute is given here just so we can differentiate between similar fields (just like primary key in sql , here name is the primary key)
    <FoodPill key={name} name={name} measure={measure} calories={calories} onFoodPillClick={foodPillClickHandler} />
    ));
}

class App extends React.Component 
{
  state = 
  {
    foodclicked:"",
    totalCalorie :0
  };
  // Put the click handler here.
  foodPillClickHandler = (Calories,name) => {
    this.setState({foodclicked : (this.state.foodclicked === "")?(name):(this.state.foodclicked+" and "+name) , totalCalorie: this.state.totalCalorie + Calories });
  };

  render() 
  {
    return (
      <div className="App">
      <Header heading={"Let's see how many calories"} />
      <FoodTable calorieData={calorieData} foodPillClickHandler={this.foodPillClickHandler} />
      <TotalCalory totalCalorie={this.state.totalCalorie} foodclicked ={this.state.foodclicked} />
      </div>
      );
    }
}

  export default App;
