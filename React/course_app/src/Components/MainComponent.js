import React from 'react'
import Menu from './Menucomponent'
import '../App.css';
import Dishes from '.././shared/dishes'
import Dishdisplay from './Dishdisplay'
import Header from './Headercomponent'
import Footer from './Footercomponent'
import Homepage from './Homecomponent'
import { Switch , Route , Redirect } from 'react-router-dom'

class MainComponent extends React.Component {


  constructor() {
        super();

        this.state = {
            dishes: Dishes,
            selectedDish: null
        }
    }

    onDishSelect(dishId) {
        this.setState({ selectedDish: dishId});
    }

    render()
    {
        return (
          <div>
              <div>
                <Header />
                <Switch>
                  <Route path="./home" component = {Homepage} />
                {/*without route we used to write 
                  <Menu dishes={Dishes} onSelect = {(dishId) => this.onDishSelect(dishId)} />
                  but in this case we can't pass the onClick method as a prop so we'll have to use some other method
                */}
                  <Route path="./menu" component = {() => <Menu dishes={Dishes} />} />
                </Switch>
                
                <Dishdisplay dish = {this.state.dishes.filter((dish) => dish.id === this.state.selectedDish)[0]} />
                <Footer />
              </div>
          </div>
        );
    }

}

export default MainComponent;