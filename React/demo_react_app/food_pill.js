import React from "react";

function FoodPill(props) 
{
  return (
    <div onClick={() => props.onFoodPillClick(props)}>
      <ul> {props.name} {props.measure} {props.calories}</ul>
    </div>
  );
}

export default FoodPill;
