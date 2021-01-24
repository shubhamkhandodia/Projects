import React from 'react'
import { Card , CardImg , CardText , CardBody , CardTitle , CardSubtitle} from 'reactstrap'



function RenderCard({item}) {

    return(
        <Card>
            <CardImg src={item.image} alt={item.name} />
            <CardBody>
        	{/*now for the card title we need to fetch the designation from whatever item we render...but we only have a designation field in leader file so what should we render for other files ? (dishes , comments , promotions).....we'll just put null

				so basically this is what our next piece of code is doing i.e if this value exists just render that as a subtitle component or else render null
        */}
            <CardTitle>{item.name}</CardTitle>
            {item.designation ? <CardSubtitle>{item.designation}</CardSubtitle> : null }
            <CardText>{item.description}</CardText>
            </CardBody>
        </Card>
    );

}

function Home(props) {
	return (
		  <div className="container">
            <div className="row align-items-start">
                <div className="col-12 col-md m-1">
                    <RenderCard item={props.dish} />
                </div>
                <div className="col-12 col-md m-1">
                    <RenderCard item={props.promotion} />
                </div>
                <div className="col-12 col-md m-1">
                    <RenderCard item={props.leader} />
                </div>
            </div>
        </div>
	)
}

export default Home