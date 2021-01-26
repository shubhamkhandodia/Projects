import React from 'react'
import Comments from '../shared/Comments';
import Promotions from '../shared/Promotions';
import Leaders from '../shared/Leaders';
import Dishes from '.././shared/dishes'

export const initialState = {
	dishes: Dishes,
	comments: Comments,
	promotions: Promotions,
	leaders: Leaders,
}

export const Reducer = (state = initialState,action) => {
	return state;
}
