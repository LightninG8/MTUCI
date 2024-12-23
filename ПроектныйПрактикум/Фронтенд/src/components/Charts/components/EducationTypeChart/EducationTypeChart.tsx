import { S } from 'chart.js/dist/chunks/helpers.core';
import { useState } from 'react';
import { Bar } from 'react-chartjs-2';
import { backgroundOpacityColors, backgroundColors } from '../../constants';

import s from '../../Chart.module.css';
import { educationTypes } from 'commonConstants';
import { IResult } from 'interfaces';
import { resourceLimits } from 'worker_threads';

interface IEducationTypeChartProps {
  result?: IResult;
}

export const EducationTypeChart = ({result}: IEducationTypeChartProps) => {
  const options = {
    indexAxis: 'y' as const,
    responsive: true,
    maintainAspectRatio: false,
  };

  const [data, setData] = useState({
    labels: educationTypes.labels,
    datasets: [{
        data: educationTypes.data,
        backgroundColor: backgroundColors,
        label: 'Средний показатель IQ'
    }]
  });

  const resultAffiliationPercent = result ? Math.floor(100 - (result.iq / 145 * 100)) : 0;
  const resultSuperiorityPercent = 100 - resultAffiliationPercent;

  return (
    <div className={s.chart + ' ' + s.chart__body}>
      <h6 className={s.chart__title}>Распределение по отношению к области знаний</h6>
      <div className={s.chart__chart}>
        <Bar data={data} options={options}/>
      </div>
      {result && (
        <div className={s.chart__result}>Вы принадлежите к {resultAffiliationPercent}% самых умных людей принадлежащих вашей области знаний ({result.educationType}). Вы умнее, чем {resultSuperiorityPercent}% людей принадлежащих вашей области знаний.</div>
      )}
    </div>
  );
}
