import s from './Questioin.module.css';

import { IQuestion, questionsListLength } from 'containers/Test/questionsList';
import Image from 'next/image';
import { useAppDispatch, useAppSelector } from 'hooks';
import { nextSlide, addResultPoints, finishTest } from 'ducks';

interface IQuestionProps {
  slide: IQuestion;
};


export const Question: React.FC<IQuestionProps> = ({slide}: IQuestionProps) => {
  const dispatch = useAppDispatch();

  const { currentSlide } = useAppSelector((state) => state.test);

  const onClick = (iqPoints: number) => {
    if (currentSlide == questionsListLength) {
      dispatch(finishTest())
    } else {
      dispatch(nextSlide());
    }
    
    dispatch(addResultPoints(iqPoints));
  }
  return (
    <div className={s.slide + ' ' + s.slide__body}>
      <div className={s.slide__title}>{slide?.question?.text}</div>
      <div className={s.slide__question}>
        <Image src={slide?.question?.image} alt='question' loading='eager'/>
      </div>
      <div className={s.slide__answers}>
        {slide?.answers?.map((item, i) => (
          <div
            className={s.answer + ' ' + s.answer__body}
            onClick={() => onClick(item.value)}
            key={i}
          >
            <Image src={item.image} alt='answer'/>
          </div>
        ))}
      </div>
      <div className={s.slide__counter}>{currentSlide}/{questionsListLength}</div>
    </div>
  );
}
