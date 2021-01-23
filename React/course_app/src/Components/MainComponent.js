import React from 'react'
import { Navbar, NavbarBrand } from 'reactstrap';
import Menucomponent from './Menucomponent';
import '../App.css';
import Dishes from '.././shared/dishes';
import Dishdisplay from './Dishdisplay';

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
              <Navbar dark color="primary">
                <div className="container">
                  <NavbarBrand href="/">Ristorante Con Fusion</NavbarBrand>
                </div>
              </Navbar>
              <div >
                <Menucomponent dishes={Dishes} onSelect = {(dishId) => this.onDishSelect(dishId)} />
                <Dishdisplay dish = {this.state.dishes.filter((dish) => dish.id === this.state.selectedDish)[0]} />
              </div>
          </div>
        );
    }

}

export default MainComponent;