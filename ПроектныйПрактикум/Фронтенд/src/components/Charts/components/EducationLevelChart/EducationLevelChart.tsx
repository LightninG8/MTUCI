import { useState } from 'react';
import { Bar } from 'react-chartjs-2';
import { backgroundOpacityColors, backgroundColors } from '../../constants';


import s from '../../Chart.module.css';
import { educationLevels } from 'commonConstants';
import { IResult } from 'interfaces';

interface IEducationLevelChartProps {
  result?: IResult;
}

export const EducationLevelChart = ({result}: IEducationLevelChartProps) => {

  const options = {
    responsive: true,
    maintainAspectRatio: false,
  };

  const [data, setData] = useState({
    labels: educationLevels.labels,
    datasets: [{
        data: educationLevels.data,
        label: 'Средний показатель IQ',
        backgroundColor: backgroundColors,
    }]
  });

  const resultAffiliationPercent = result ? Math.floor(100 - (result.iq / 145 * 100)) : 0;
  const resultSuperiorityPercent = 100 - resultAffiliationPercent;

  return (
    <div className={s.chart + ' ' + s.chart__body}>
      <h6 className={s.chart__title}>Распределение относительно уровня образования</h6>
      <div className={s.chart__chart}>
        <Bar data={data} options={options}/>
      </div>
      {result && (
        <div className={s.chart__result}>Вы принадлежите к {resultAffiliationPercent}% самых умных людей вашего уровня образования ({result.educationLevel}). Вы умнее, чем {resultSuperiorityPercent}% людей вашего уровня образования.</div>
      )}
    </div>
  );
}
