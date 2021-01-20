import React from 'react'
import "./headeroptions.css"

function Headeroptions({Icon , title}) {
	return (
		<div className="headeroption">
			{Icon && <Icon className = "headeroption_icon" />}
			<h3 className="headeroption_title">{title}</h3>
		</div>
	);
}

export default Headeroptions