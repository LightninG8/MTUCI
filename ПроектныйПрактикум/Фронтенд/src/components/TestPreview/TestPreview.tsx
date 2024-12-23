import Image from 'next/image';
import s from './TestPreview.module.css';

import timeImage from './storage/time.svg';
import questionImage from './storage/question.svg';
import ageImage from './storage/age.svg';
import awardImage from './storage/award.svg';
import circlesImage from './storage/circles.svg';
import ratingImage from './storage/rating.svg';
import { Button } from 'components';
import { useAppDispatch } from 'hooks';
import { startTest } from 'ducks';
import { questionsListLength } from 'containers/Test/questionsList';


interface ITestPreview {

}

export const TestPreview: React.FC<ITestPreview> = ({}: ITestPreview) => {
  const dispatch = useAppDispatch();

  return (
    <section className={s.section + ' ' + s.section__body}>
      <div className={s.section__header}>
        <h1 className={s.section__title}>Пройдите тест на IQ</h1>
        <h2 className={s.section__subtitle}>Вместе с Интеллект-тест и раскройте весь потенциал мозга</h2>
      </div>
      <div className={s.section__bar + ' ' + s.bar}>
        <div className={s.bar__item}>
          <Image src={timeImage} alt='time' />
          <span>20 минут</span>
        </div>
        <div className={s.bar__item}>
          <Image src={questionImage} alt='question' />
          <span>{questionsListLength} вопросов</span>
        </div>
        <div className={s.bar__item}>
          <Image src={ageImage} alt='age' />
          <span>18+</span>
        </div>
        <div className={s.bar__item}>
          <Image src={ratingImage} alt='rating' />
          <span>≈ 100 IQ</span>
        </div>
      </div>
      <div className={s.section__description + ' ' + s.description}>
        <div className={s.description__item}>
          <div className={s.description__image}>
            <Image src={circlesImage} alt='circles' />
          </div>
          <p className={s.description__text}>
            Тест от IQ-Check состоит из 40 вопросов. После прохождения теста вы получите результат IQ по отношению к населению, основанный на статистических данных.
          </p>
        </div>
        <div className={s.description__item}>
          <div className={s.description__image}>
            <Image src={awardImage} alt='award' />
          </div>
          <p className={s.description__text}>
            Вопросы на основе изображений
          </p>
        </div>
        <div className={s.description__item}>
          <div className={s.description__image}>
            <Image src={awardImage} alt='award' />
          </div>
          <p className={s.description__text}>
            Получите сертификат со своим IQ, а также профессиональный отчет.
          </p>
        </div>
      </div>
      <Button className={s.section__button} onClick={() => dispatch(startTest())}>Начать тестирование</Button>
    </section>
  )
};
