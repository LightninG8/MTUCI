import type { NextPage } from 'next';
import { MainLayout } from 'containers';
import Head from 'next/head';
import s from '../styles/404.module.css';
import Image from 'next/image';
import { Button } from 'components';
import Link from 'next/link';


const ErrorPage: NextPage = () => {
  return (
    <>
      <Head>
        <link rel='apple-touch-icon' sizes='180x180' href='/apple-touch-icon.png'/>
        <link rel='icon' type='image/png' sizes='32x32' href='/favicon-32x32.png'/>
        <link rel='icon' type='image/png' sizes='16x16' href='/favicon-16x16.png'/>
        <link rel='manifest' href='/site.webmanifest'/>
        <link rel='mask-icon' href='/safari-pinned-tab.svg' color='#3e4480'/>
        <meta name='msapplication-TileColor' content='#3e4480'/>
        <meta name='theme-color' content='#3e4480'/>

        <title>Такой страницы не существует</title>
      </Head>
      <MainLayout>
        <div className={s.error + ' ' + s.error__body}>
          <div className={s.error__block}>
            <h1 className={s.error__code}>404</h1>
            <h2 className={s.error__title}>Такой страницы не существует</h2>
            <Link href="/"><Button className={s.error__button}>На главную</Button></Link>
          </div>
        </div>
      </MainLayout>
    </>
    
  );
}

export default ErrorPage;
