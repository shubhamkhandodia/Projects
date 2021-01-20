import React from 'react'
import {Avatar} from '@material-ui/core';
import './sidebar.css'

function Sidebar() {


	const recentItem = (item) =>{

		return(

				<div className="myrecentItem">
					<p>#{item}</p>
				</div>

			);
	}



	return (
		<div className = "sidebar">
			<div className="sidebar_top">
				<img src="" alt="" />
				<Avatar />
				<h2>Shubham Khandodia</h2>
				<h6>shubhamkhandodia27@gmail.com</h6>
			</div>


			<div className="sidebar_stats">
				<div className="sidebar_stat">
					<p>who viewed your profile ? </p>
					<p className = "stat_number">18</p>
				</div>

				<div className="sidebar_stat">
					<p>views of your post </p>
					<p className = "stat_number">243</p>
				</div>

			</div>	

			<div className="sidebar_bottom">
				<p>Recent</p>
				{recentItem('React.js')}
				{recentItem('Android development')}
				{recentItem('jobsearch')}
			</div>
		</div>
	);
}

export default Sidebar