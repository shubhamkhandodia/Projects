import React from "react";
import ReactDOM from "react-dom";
import FoodPill from "./food-pill";
import calorieData from "./data/calorie-data";

import "./styles.css";

function TotalCalory({ totalCalorie , foodclicked }) {
  return <h2> Your total calorie for today: {totalCalorie} after eating {foodclicked}</h2>;
}

function Header({ heading }) {
  return <h1>{heading} </h1>;
}

function FoodTable({calorieData, foodPillClickHandler }) {
  return calorieData
    .slice(0, 10)
    .map(({ name, measure, calories }) => (
      <FoodPill
        key={name}
        name={name}
        measure={measure}
        calories={calories}
        onFoodPillClick={foodPillClickHandler}
      />
    ));
}

class App extends React.Component {
  state = {
    foodclicked:"",
    totalCalorie :0
  };
  // Put the click handler here.
  foodPillClickHandler = (totalCalorie,name) => {
    this.setState({foodclicked : (this.state.foodclicked === "")?(name):(this.state.foodclicked+" and "+name) , totalCalorie: this.state.totalCalorie + totalCalorie });
  };

  render() {
    return (
      <div className="App">
        <Header heading={"Let's see how many calories"} />
        <FoodTable
          calorieData={calorieData}
          foodPillClickHandler={this.foodPillClickHandler}
        />
        <TotalCalory totalCalorie={this.state.totalCalorie} foodclicked ={this.state.foodclicked} />
      </div>
    );
  }
}

const rootElement = document.getElementById("root");
ReactDOM.render(<App />, rootElement);
