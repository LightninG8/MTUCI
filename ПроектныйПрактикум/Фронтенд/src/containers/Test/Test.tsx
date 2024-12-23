import { useDispatch } from 'react-redux';
import { useAppSelector } from 'hooks';
import { questionsList } from './questionsList';
import { Question, SendResultForm } from './components';

interface ITestProps {

};


export const Test: React.FC<ITestProps> = ({}: ITestProps) => {
  const dispatch = useDispatch();
  const { currentSlide, isTestFinished } = useAppSelector((state) => state.test);

  return (
    <>
      { !isTestFinished && (
        <Question slide={questionsList[currentSlide - 1]}/>
      )}
      { isTestFinished && (
        <SendResultForm />
      )}
      
      
    </>
  );
}
