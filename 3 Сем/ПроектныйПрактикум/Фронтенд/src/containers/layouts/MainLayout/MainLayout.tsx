import s from './MainLayout.module.css';
import cs from 'styles/common.module.css';
import { Footer, Header } from 'components';

type MainLayoutProps = {
  className?: string;
  children?: React.ReactNode;
}

export const MainLayout: React.FC<MainLayoutProps> = ({className, children}: MainLayoutProps) => {
  return (
    <div className={s.layout}>
      <Header />
      <main className={s.main + ' ' + className}>
        {children}
      </main>
      <Footer />
    </div>
  )
};
