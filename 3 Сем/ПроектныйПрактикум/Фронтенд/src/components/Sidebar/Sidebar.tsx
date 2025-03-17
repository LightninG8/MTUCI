import { useAppSelector } from 'hooks';
import Link from 'next/link';
import s from './Sidebar.module.css';
import { SidebarResult } from './components';

interface ISidebarProps {
  className?: string;
  recentResults: any;
  topResults: any;
}

export const Sidebar: React.FC<ISidebarProps> = ({className, recentResults, topResults}: ISidebarProps) => {
  const state = useAppSelector((state) => state);

  return (
    <aside className={className + ' ' + s.sidebar + ' ' + s.sidebar__body}>
      <div className={s.sidebar__row + ' ' + s.sidebar__row_top}>
        <h3 className={s.sidebar__title}>
          <div>3 лучших результата</div>
          <div className={s.sidebar__title_period}>(неделя)</div>
        </h3>
        <div className={s.sidebar__results}>
          <div className={s.sidebar__track}>
            {topResults?.map((result: any) => (
              <SidebarResult result={result} key={result._id}/>
            ))}
          </div>
          
        </div>
        
      </div>
      <div className={s.sidebar__row}>
        <h3 className={s.sidebar__title}>20 последних результатов</h3>
        <div className={s.sidebar__results}>
          <div className={s.sidebar__track}>
            {recentResults?.map((result: any) => (
              <SidebarResult result={result} key={result._id}/>
            ))}
          </div>
          
        </div>
        
      </div>
    </aside>
  )
};
