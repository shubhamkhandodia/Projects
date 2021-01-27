import { createStore , combineReducers } from 'redux';
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
        })
    );

	return store;
}