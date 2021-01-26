import React from 'react'
import Menu from './Menucomponent'
import '../App.css';
import Dishdisplay from './Dishdisplay'
import Header from './Headercomponent'
import Footer from './Footercomponent'
import Home from './Homecomponent'
import Contact from './Contactcomponent'
import About from './AboutComponent'
import { Switch, Route, Redirect, withRouter } from 'react-router-dom'
import {connect} from 'react-redux'



const mapStateToProps = (state) => {
    return {
      dishes: state.dishes,
      comments: state.comments,
      promotions: state.promotions,
      leaders: state.leaders
    }
}


class MainComponent extends React.Component {


    constructor(props) {
        super(props);
    }

    onDishSelect(dishId) {
        this.setState({ selectedDish: dishId });
    }

    render() {

        const Aboutpage = () => {
            return (
                <About leaders={this.props.leaders}/>
            );
        };

        const DishWithId = ({ match }) => {
            return (
                <Dishdisplay dish={this.props.dishes.filter((dish) => dish.id === parseInt(match.params.dishId,10))[0]} 
                comments={this.props.comments.filter((comment) => comment.dishId === parseInt(match.params.dishId,10))} />
            );
        };

        const Homepage = () => {
            return (
                <Home dish={this.props.dishes.filter((dish) => dish.featured)[0]}
              promotion={this.props.promotions.filter((promo) => promo.featured)[0]}
              leader={this.props.leaders.filter((leader) => leader.featured)[0]}/>
            );
        }

        return (
            <div>
              <div>
                <Header />
                <Switch>
                  <Route path="/home" component = {Homepage} />
                  <Route exact path="/menu" component = {() => <Menu dishes={this.props.dishes} />} />
                  <Route exact path = "/contactus" component = {Contact} />
                  <Route exact path = "/aboutus" component = {Aboutpage} />
                  <Route path='/menu/:dishId' component={DishWithId} />
                  <Redirect to='/home' />
                </Switch>
                <Footer />
              </div>
          </div>
        );
    }

}

export default withRouter(connect(mapStateToProps)(MainComponent));