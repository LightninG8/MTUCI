import type { NextPage } from 'next';
import { MainLayout } from 'containers';
import Head from 'next/head';
import cs from 'styles/common.module.css';
import s from 'styles/Home.module.css';
import { getRecentResults, getRunningOperationPromises, getTopResults, wrapper } from 'ducks';
import { CelebritiesSlider, Sidebar, TestPreview, Charts } from 'components';
import { useAppSelector } from 'hooks';
import { Test } from 'containers/Test';
import { COMMON_API_ADRESS } from 'commonConstants';

interface IHomeProps {
  recentResults: any;
  topResults: any;
};

const Home: NextPage<IHomeProps> = ({recentResults, topResults}) => {
  const { isTestStarted } = useAppSelector((state) => state.test);

  return (
    <>
      <Head>
        <title>IQ Тест онлайн | Интеллект-тест</title>
      </Head>
      <MainLayout>
        <div className={s.home}>
          <div className={s.home__body + ' ' + cs.container}>
            <Sidebar className={s.home__sidebar} recentResults={recentResults} topResults={topResults}/>
            <main className={s.home__main}>
              <section className={s.home__test}>
                {!isTestStarted && <TestPreview />}
                {isTestStarted && <Test />}
              </section>
              <section className={s.home__celebrities}>
                <h2 className={s.home__title + ' ' + cs.title}>IQ рейтинг знаменитостей</h2>
                <CelebritiesSlider className={s.home__slider}/>
              </section>
              <section className={s.section__text}>
                <h2 className={s.home__title + ' ' + cs.title}>Что такое тест на IQ?</h2>
                <p className={cs.container__paragraph}>
                  У каждого человека есть свой уровень интеллекта. Мы все примерно представляем, что мы можем, а чего не можем. Но как этот уровень измерить? Для этого нужно пройти тест IQ. В результаты вы получите свой балл, который покажет ваш уровень интеллекта или уровень IQ.
                </p>
                <p className={cs.container__paragraph}>
                  Коэффициент интеллекта (intelligence quotient) или IQ - количественная оценка уровня интеллекта человека: уровень интеллекта относительно уровня интеллекта среднестатистического человека такого же возраста. Определяется с помощью специальных тестов. Тесты IQ рассчитаны на оценку мыслительных способностей, а не уровня знаний.
                </p>
                <p className={cs.container__paragraph}>
                  Каждый тест состоит из множества различных заданий нарастающей сложности. Среди них тестовые задания на логическое и пространственное мышление, а также задания других типов. По результатам теста подсчитывается IQ. Замечено, что чем больше вариантов теста проходит испытуемый, тем лучшие результаты он показывает. Наиболее известным тестом является тест Айзенка.
                </p>
                <p className={cs.container__paragraph}>
                  Тесты IQ разрабатываются так, чтобы результаты описывались нормальным распределением со средним значением IQ, равным 100 и таким разбросом, чтобы 50 % людей имели IQ между 90 и 110 и по 25 % — ниже 90 и выше 110. Средний IQ выпускников американских вузов составляет 105, отличников — 130—140. Значение IQ менее 70 часто квалифицируется как умственная отсталость.
                </p>
              </section>
              <section className={s.home__average}>
                <h2 className={s.home__title + ' ' + cs.title}>Среднестатистические показатели IQ</h2>
                <p className={cs.container__paragraph}>К каждому из результатов после прохождения нашего международного теста IQ добавляется статистическая информация, в которой каждый, проходящий тестирование, занимает место в соответствии с определенными показателями (населения, возрастная группа, образование, сфера знаний).</p>
                <p className={cs.container__paragraph}>Представленные статистические данные сформированы из собранной на данный момент информации в рамках всего мира, в дальнейшем мы продолжим получать статистику прохождения тестов населением, и сможем актуализировать показатели.</p>
                <Charts className={s.home__charts} />
              </section>
              
            </main>
          </div>
        </div>
      </MainLayout>
    </>
    
  );
}


export const getServerSideProps = wrapper.getServerSideProps((store) => async () => {
    
    const recentResults = await fetch(`${COMMON_API_ADRESS}/recent?limit=20`).then((data) => data.json())
    
    const topResults = await fetch(`${COMMON_API_ADRESS}/recent?days=7&limit=3`).then((data) => data.json())

    return {
      props: {
        recentResults: recentResults?.data || [],
        topResults: topResults?.data || []
      },
    };
  }
);


export default Home
