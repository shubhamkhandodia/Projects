import React from 'react'
import './feed.css'

function Feed() {
	return (
		<div className="feed">
			<div className="feed_inputcontainer">
				<div className="feed_input">
					<CreateIcon />
					<form>
						<input type="text" />
						<button type="submit">send</button>
					</form>
				</div>
				
				
			</div>	
		</div>
	)
}

export default Feed