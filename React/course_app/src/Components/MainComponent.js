import React from 'react'
import Menu from './Menucomponent'
import '../App.css';
import Dishes from '.././shared/dishes'
import Comments from '../shared/Comments';
import Promotions from '../shared/Promotions';
import Leaders from '../shared/Leaders';
import Dishdisplay from './Dishdisplay'
import Header from './Headercomponent'
import Footer from './Footercomponent'
import Home from './Homecomponent'
import Contact from './Contactcomponent'
import { Switch , Route , Redirect } from 'react-router-dom'

class MainComponent extends React.Component {


  constructor() {
        super();

        this.state = {
            dishes: Dishes,
            comments: Comments,
            promotions: Promotions,
            leaders: Leaders,
            selectedDish: null
        }
    }

    onDishSelect(dishId) {
        this.setState({ selectedDish: dishId});
    }

    render()
    {
        const Homepage = () =>{
          return(
                <Home dish={this.state.dishes.filter((dish) => dish.featured)[0]}
              promotion={this.state.promotions.filter((promo) => promo.featured)[0]}
              leader={this.state.leaders.filter((leader) => leader.featured)[0]}/>
            );
        }

        return (
          <div>
              <div>
                <Header />
                <Switch>
                  <Route path="/home" component = {Homepage} />
                  <Route exact path="/menu" component = {() => <Menu dishes={Dishes} />} />
                  <Route exact path = "/contactus" component = {Contact} />
                  <Redirect to='/home' />
                </Switch>
                
                <Dishdisplay dish = {this.state.dishes.filter((dish) => dish.id === this.state.selectedDish)[0]} />
                <Footer />
              </div>
          </div>
        );
    }

}

export default MainComponent;