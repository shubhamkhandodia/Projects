import React from "react";
import ReactDOM from "react-dom";
import App from "./app";

ReactDOM.render(<App />, document.getElementById("root"));

if (module && module.hot) {

   module.hot.accept('./app.js', function() {

     console.log('Accepting the updated printMe module!');

     printMe();

   })

 }