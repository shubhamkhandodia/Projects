import React from 'react';

const addinternalnumber = (number) =>
{
  const mynumber = 24;

  return (<div> your sum is {parseInt(number)+mynumber} </div>);
}

const Addingcomponent = (props) =>
{
  return(

    <div>

    <h2> welcome to react live 3 </h2>

    {addinternalnumber(props.number)}

  </div>

  );
}

export default Addingcomponent;