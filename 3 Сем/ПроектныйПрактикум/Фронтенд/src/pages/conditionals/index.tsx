import type { NextPage } from 'next';
import { MainLayout } from 'containers';
import Head from 'next/head';
import s from './Conditionals.module.css';
import cs from 'styles/common.module.css';
import { questionsListLength } from 'containers/Test/questionsList';

const Conditionals: NextPage = () => {

  return (
    <>
      <Head>
        <title>Условия использования | Интеллект-тест</title>
      </Head>
      <MainLayout className={s.section}>
        <div className={cs.container + ' ' + cs.container__wrapper}>
          <div className={s.contacts + ' ' + s.contacts__body}>
            <h1 className={cs.title}>Условия использования</h1>
            <p className={cs.container__paragraph}>
              Сервис &quot;Интеллект тест&quot; предлагает определить пользователю свой уровень IQ. Определение уровня IQ осуществляется посредством ответов на {questionsListLength} вопросов в течении заданного времени. После прохождения теста, вместе с результатом, пользователь получает подробную статистику, в которой может сравнить свой результат, с результатами в своей возрастной, социальной и профессиональной группах, а так же провести сравнения с другими группами</p>
          </div>
        </div>
        
      </MainLayout>
    </>
    
  );
}

export default Conditionals;
