import {createStore, combineReducers, applyMiddleware } from 'redux';
import thunk from 'redux-thunk';
import logger from 'redux-logger';
import Leaders from './leadersreducer';
import Comments from './commentsreducer';
import Promotions from './promotionsreducer';
import Dishes from './dishreducer'


export const Configurestore = () => {
	const store = createStore(
        combineReducers({
            dishes: Dishes,
            comments: Comments,
            promotions: Promotions,
            leaders: Leaders
        }),
        applyMiddleware(thunk, logger)
    );

	return store;
}