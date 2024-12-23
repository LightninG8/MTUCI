import type { NextPage } from 'next';
import { MainLayout } from 'containers';
import Head from 'next/head';
import s from './Contacts.module.css';
import cs from 'styles/common.module.css';

const Contacts: NextPage = () => {

  return (
    <>
      <Head>
        <title>Контакты | Интеллект-тест</title>
      </Head>
      <MainLayout className={s.section}>
        <div className={cs.container + ' ' + cs.container__wrapper}>
          <div className={s.contacts + ' ' + s.contacts__body}>
            <h1 className={cs.title}>Контакты</h1>
            <p className={cs.container__paragraph}>Email: intellect.test.ru@gmail.com</p>
          </div>
        </div>
        
      </MainLayout>
    </>
    
  );
}

export default Contacts;
