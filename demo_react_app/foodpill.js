import React from "react";

function foodpill(props)
{
  return (
    <div>
      <span> {props.name} </span>
      <span> {props.measure} </span>
      <span> {props.calories} </span>
    </div>
  );
}

export default foodpill;
