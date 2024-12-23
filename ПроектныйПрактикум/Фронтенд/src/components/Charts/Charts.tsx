import { Chart, BarElement, CategoryScale, ArcElement, Legend, LinearScale, Title, Tooltip } from 'chart.js';

import { AgeChart, EducationLevelChart, EducationTypeChart, GenderChart, LevelChart } from './components'

import s from './Charts.module.css';
import { IResult } from 'interfaces';

Chart.register(
  CategoryScale,
  LinearScale,
  BarElement,
  Title,
  Tooltip,
  Legend,
  ArcElement
);

interface IChartsProps {
  className?: string;
  result?: IResult
}

export const Charts = ({className, result}: IChartsProps) => {
  return (
    <div className={s.charts + ' ' + s.charts__body + ' ' + className}>
      <LevelChart result={result}/>
      <AgeChart result={result}/>
      <EducationLevelChart result={result}/>
      <EducationTypeChart result={result}/>
      <GenderChart result={result}/>

    </div>
  )
}
