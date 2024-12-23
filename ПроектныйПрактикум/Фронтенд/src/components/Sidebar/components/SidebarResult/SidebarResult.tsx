import s from './SidebarResult.module.css';
import { getFormattedDate } from 'utils';

interface ISidebarProps {
  result: any;
}

export const SidebarResult: React.FC<ISidebarProps> = ({result}: ISidebarProps) => {
  const date = getFormattedDate(new Date(result.createdAt));

  return (
    <div className={s.result + ' ' + s.result__body}>
      <div className={s.result__image} style={{ backgroundImage: `url(/images/flags/${result.countryCode.toLowerCase()}.png)` }}></div>
      <div className={s.result__info}>
        <div className={s.result__value}>
          <span className={s.result__name}>{result.name.split(' ')[0]}</span> 
          <span className={s.result__dot}>·</span>
          <strong className={s.result__iq}>{result.iq} IQ</strong>
        </div>
        <div className={s.result__date}>{date}</div>
      </div>
    </div>
  )
};
