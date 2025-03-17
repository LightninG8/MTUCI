import s from './Footer.module.css';
import cs from 'styles/common.module.css';
import Link from 'next/link';

interface IFooterProps {

}

export const Footer: React.FC<IFooterProps> = ({}: IFooterProps) => {
  return (
    <footer className={s.footer}>
        <div className={cs.container + ' ' + s.footer__body}>
          <nav className={s.footer__nav}>
            <ul className={s.footer__menu}>
              <li className={s.footer__item}>
                <Link href="/"><a className={s.footer__link}>{(new Date()).getFullYear()} © интеллект-тест.рф </a></Link>
              </li>
              <li className={s.footer__item}>
                <Link href="/contacts"><a className={s.footer__link}>Контакты</a></Link>
              </li>
              <li className={s.footer__item}>
                <Link href="/restore"><a className={s.footer__link}>Восстановить результат</a></Link>
              </li>
              <li className={s.footer__item}>
                <Link href="/conditionals"><a className={s.footer__link}>Условия использования</a></Link>
              </li>
              <li className={s.footer__item}>
                <Link href="/privacy"><a className={s.footer__link}>Политика конфиденциальности</a></Link>
              </li>
            </ul>
          </nav>
        </div>
    </footer>
  )
};
