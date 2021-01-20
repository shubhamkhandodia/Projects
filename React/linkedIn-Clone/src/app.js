import React from "react";
import Header from "./header/header.js"
import Sidebar from "./Sidebar/Sidebar.js"
import Feed from "./feed/feed.js"
import { hot } from 'react-hot-loader';

const App = () => {
  return (
    <div className="app">
      <Header />

      <div className="app_body">
        <Sidebar />
        <Feed />
      </div>
      
    </div>
    
  );
};

export default hot(module) (App);