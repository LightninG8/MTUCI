import { useState } from 'react';
import { Bar } from 'react-chartjs-2';
import { backgroundOpacityColors, backgroundColors } from '../../constants';


import s from '../../Chart.module.css';
import { genderTypes } from 'commonConstants';
import { IResult } from 'interfaces';

interface IGenderChartProps {
  result?: IResult;
}

export const GenderChart = ({result}: IGenderChartProps) => {
  const options = {
    indexAxis: 'y' as const,
    responsive: true,
    maintainAspectRatio: false,
  };

  const [data, setData] = useState({
    labels: genderTypes.labels,
    datasets: [{
        data: genderTypes.data,
        backgroundColor: backgroundColors,
        label: 'Средний уровень IQ'
    }]
  });

  const resultAffiliationPercent = result ? Math.floor(100 - (result.iq / 145 * 100)) : 0;
  const resultSuperiorityPercent = 100 - resultAffiliationPercent;

  return (
    <div className={s.chart + ' ' + s.chart__body + ' ' + s.chart__body_gender}>
      <h6 className={s.chart__title}>Уровень IQ у мужчин и женщин</h6>
      <div className={s.chart__chart}>
        <Bar data={data} options={options}/>
      </div>
      {result && (
        <div className={s.chart__result}>Вы принадлежите к {resultAffiliationPercent}% представителей вашего пола. Вы умнее, чем {resultSuperiorityPercent}% людей вашего пола</div>
      )}
    </div>
  );
}
