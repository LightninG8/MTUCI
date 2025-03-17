import { configureStore } from '@reduxjs/toolkit';
import { setupListeners } from '@reduxjs/toolkit/dist/query';
import { testSlice } from 'ducks/slices';
import { resultApi } from 'ducks/api';
import { createWrapper } from 'next-redux-wrapper';


export const makeStore = () => configureStore({
  reducer: {
    [resultApi.reducerPath]: resultApi.reducer,
    [testSlice.name]: testSlice.reducer
  },
  middleware: getDefaultMiddleware => getDefaultMiddleware().concat(resultApi.middleware)
});

export const store = makeStore();


setupListeners(store.dispatch);

export type RootStore = ReturnType<typeof makeStore>;
export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch;

export const wrapper = createWrapper<RootStore>(makeStore);
