import { createSlice } from '@reduxjs/toolkit';

interface ITestSlice {
  isTestStarted: boolean;
  currentSlide: number;
  resultPoints: number;
  isTestFinished: boolean;
}

const initialState: ITestSlice = {
  currentSlide: 0,
  isTestStarted: false,
  resultPoints: 40,
  isTestFinished: false,
};

export const testSlice = createSlice({
  name: 'test',
  initialState,
  reducers: {
    nextSlide: (state) => {
      state.currentSlide += 1;
    },
    startTest: (state) => {
      state.currentSlide = 1;
      state.isTestStarted = true
    },
    addResultPoints: (state, action) => {
      state.resultPoints += action.payload
    },
    resetTest: (state) => {
      console.log(initialState);
  
      state = {
        ...state
      };
    },
    finishTest: (state) => {
      state.isTestFinished = true;
    }
  }
});


export const { nextSlide, startTest, addResultPoints, resetTest, finishTest } = testSlice.actions;
