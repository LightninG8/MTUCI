import type { NextPage } from 'next';
import { MainLayout } from 'containers';
import Head from 'next/head';
import s from './Privacy.module.css';
import cs from 'styles/common.module.css';

const Privacy: NextPage = () => {

  return (
    <>
      <Head>
        <title>Политика конфиденциальности | Интеллект-тест</title>
      </Head>
      <MainLayout className={s.section}>
        <div className={cs.container + ' ' + cs.container__wrapper}>
          <div className={s.contacts + ' ' + s.contacts__body}>
            <h1 className={cs.title}>Политика конфиденциальности</h1>
            <p className={cs.container__paragraph}>Мы не собираем никаких данных, позволяющих установить личность, за исключением вашего адреса электронной почты, который собирается только для того, чтобы вы могли восстановить свои результаты с помощью функции «Восстановить мои результаты».</p>
            <p className={cs.container__paragraph}>
              Другая информация (псевдоним, пол, год рождения, область обучения, уровень обучения, страна) - это общие данные, которые не позволяют напрямую идентифицировать вас, и эти данные используются только для генерации статистики, которая согласуется с вашими результатами. или для общего содержания на сайте.
            </p>
            <p className={cs.container__paragraph}>
              Мы не передаем какие-либо данные, позволяющие установить личность, которые мы сохранили, и не отправляем маркетинговые электронные письма.
            </p>
            <p className={cs.container__paragraph}>
              В соответствии с общими правилами защиты данных, вы имеете право в любое время попросить нас удалить ваши результаты и данные, которые вы ввели для их создания, отправив нам электронное письмо на адрес &quot;intellect.test.ru@gmail.com&quot; с того же адреса электронной почты, который вы использовали для получения результатов.
            </p>
          </div>
        </div>
        
      </MainLayout>
    </>
    
  );
}

export default Privacy;
