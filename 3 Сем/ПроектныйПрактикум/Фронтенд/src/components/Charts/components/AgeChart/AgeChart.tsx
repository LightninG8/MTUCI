import { useState } from 'react';
import { Bar } from 'react-chartjs-2';
import { backgroundOpacityColors, backgroundColors } from '../../constants';


import s from '../../Chart.module.css';
import { IResult } from 'interfaces';

interface IAgeChartProps {
  result?: IResult;
}

export const AgeChart = ({result}: IAgeChartProps) => {
  const options = {
    responsive: true,
    maintainAspectRatio: false,
  };

  const [data, setData] = useState({
    labels: ['< 18 лет', '18-39 лет', '40-59 лет', '60-79 лет', '> 80 лет'],
    datasets: [{
        data: [92, 98, 97, 92, 74],
        label: 'Средний показатель IQ',
        backgroundColor: backgroundColors,
    }]
  });

  const resultAffiliationPercent = result ? Math.floor(100 - (result.iq / 145 * 100)) : 0;
  const resultSuperiorityPercent = 100 - resultAffiliationPercent;
  

  return (
    <div className={s.chart + ' ' + s.chart__body}>
      <h6 className={s.chart__title}>Уровень IQ по возрасту</h6>
      <div className={s.chart__chart}>
        <Bar data={data} options={options}/>
      </div>
      {result && (
        <div className={s.chart__result}>Вы принадлежите к {resultAffiliationPercent}% людей вашего возраста. Вы умнее, чем {resultSuperiorityPercent}% людей вашего возраста</div>
      )}
    </div>
  );
}
