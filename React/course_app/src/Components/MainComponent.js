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
import { addComment, fetchDishes } from '../redux/ActionCreators'



const mapStateToProps = (state) => {
    return {
      mydishes: state.dishes,
      comments: state.comments,
      promotions: state.promotions,
      leaders: state.leaders
    }
}

const mapDispatchToProps = (dispatch) => ({

  addComment: (dishId , rating , author , comment) => dispatch(addComment(dishId , rating , author , comment)),
  fetchDishes: () => {dispatch(fetchDishes())}
});


class MainComponent extends React.Component {


    constructor(props) {
        super(props);
    }

    onDishSelect(dishId) {
        this.setState({ selectedDish: dishId });
    }

    componentDidMount(){
      this.props.fetchDishes();
    }

    render() {

        const Aboutpage = () => {
            return (
                <About leaders={this.props.leaders}/>
            );
        };

        const DishWithId = ({ match }) => {
            return (
                <Dishdisplay dish={this.props.mydishes.dishes.filter((dish) => dish.id === parseInt(match.params.dishId,10))[0]}
                isLoading={this.props.mydishes.isLoading}
                errMess={this.props.mydishes.errMess}
                comments={this.props.comments.filter((comment) => comment.dishId === parseInt(match.params.dishId,10))}
                addComment = {this.props.addComment} />
            );
        };

        const Homepage = () => {
            return (
                <Home dish={this.props.mydishes.dishes.filter((dish) => dish.featured)[0]}
                dishesLoading={this.props.mydishes.isLoading}
                dishesErrMess={this.props.mydishes.errMess}
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
                  <Route exact path="/menu" component = {() => <Menu mydishes={this.props.mydishes.dishes} />} />
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

export default withRouter(connect(mapStateToProps , mapDispatchToProps)(MainComponent));