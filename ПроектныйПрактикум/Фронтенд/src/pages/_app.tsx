import '../styles/globals.css';

import type { AppProps } from 'next/app'
import { Provider } from 'react-redux';
import { store, wrapper } from 'ducks';
import Head from 'next/head';
import { YMInitializer } from 'react-yandex-metrika';

function App({ Component, ...rest }: AppProps) {
  const { store, props } = wrapper.useWrappedStore(rest);
  const { pageProps } = props;

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

        <meta name='viewport' content='width=device-width, initial-scale=1'/>

        {/* Seo */}
        <meta name='description' content='Международный тест на IQ'/>
      </Head>
      <Provider store={store}>
          <YMInitializer accounts={[91307164]} options={{webvisor: true}} version="2" />
          <Component {...pageProps} />
      </Provider>
    </>
  )
}


export default wrapper.withRedux(App);
