import React from 'react'
import { Card, CardImg, CardText, CardBody,CardTitle } from 'reactstrap';


function RenderDish({dish}) {
    
      if (dish != null)
            return(
                <Card>
                    <CardImg top src={dish.image} alt={dish.name} />
                    <CardBody>
                      <CardTitle>{dish.name}</CardTitle>
                      <CardText>{dish.description}</CardText>
                    </CardBody>
                </Card>
            );
        else
            return(
                <div></div>
            );

    }

    function RenderComments({comments}) {
      
      if (comments != null)
        {
            const reviews = comments.map((comment) =>{
                return(
                <div>
                <Card>
                    <CardBody>
                      <CardText>--{comment.author} , {new Intl.DateTimeFormat('en-US', {year: 'numeric', month: 'long', day: '2-digit' }).format(new Date(comment.date))}</CardText>
                      <CardText>{comment.comment}</CardText>
                    </CardBody>
                </Card>
                </div>
            );
            });

            return(

                <div>
                    <h4>Comments</h4>
                    {reviews}
                </div>
            );
            
        }
            
        else
            return(
                <div></div>
            );
      
    }

const Dishdisplay = (props) => {


        if(props.dish != null)
      return (
            <div className="row">
                <div className="col-12 col-md-5 m-1">
                    <RenderDish dish = {props.dish} />
                </div>
                <div className="col-12 col-md-5 m-1">
                    <RenderComments comments = {props.dish.comments} />
                </div>
            </div>
        );

        else
            return(
            <div></div>
        );
      
    }

export default Dishdisplay;