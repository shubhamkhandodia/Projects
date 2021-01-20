import React from "react";
import SearchIcon from '@material-ui/icons/Search';
import HomeIcon from '@material-ui/icons/Home';
import MessageIcon from '@material-ui/icons/Message';
import NotificationsIcon from '@material-ui/icons/Notifications';
import BusinessCenterIcon from '@material-ui/icons/BusinessCenter';
import SupervisorAccountIcon from '@material-ui/icons/SupervisorAccount';
import "./header.css";
import Headeroptions from "./headeroptions.js"

const Header = () => {
	return (

    <div className="header">

      <div className="headerleft">
        <img src="https://www.flaticon.com/svg/static/icons/svg/174/174857.svg" alt="linked_in_icon" />
        <div className="headersearch">
          <SearchIcon />
          <input type="text" placeholder = "Search"/>
        </div>  
      </div>

      <div className="headerright">
        <Headeroptions Icon = {HomeIcon} title = "home"/>
        <Headeroptions Icon = {SupervisorAccountIcon} title = "MyNetwork"/>
        <Headeroptions Icon = {BusinessCenterIcon} title = "Jobs"/>
        <Headeroptions Icon = {MessageIcon} title = "Messaging"/>
        <Headeroptions Icon = {NotificationsIcon} title = "Notifications"/>
      </div>

    </div>

  );
}

export default Header;