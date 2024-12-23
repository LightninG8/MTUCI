import { useState } from 'react';
import { Pie } from 'react-chartjs-2';
import { backgroundOpacityColors, backgroundColors } from '../../constants';


import s from '../../Chart.module.css';
import { IResult } from 'interfaces';

interface ILevelChartProps {
  result?: IResult;
}


export const LevelChart = ({result}: ILevelChartProps) => {
  const options = {
    responsive: true,
    maintainAspectRatio: false,
  };

  const [data, setData] = useState({
    labels: ['< 70', '70-79', '80-89', '90-109', '110-119', '120-129', '130+'],
    datasets: [{
        data: [13, 11, 14, 31, 18, 10, 3],
        backgroundColor: backgroundOpacityColors,
        borderColor: backgroundColors
    }]
  });

  const iq = result ? result.iq : 0;

  let  resultAffiliationPercent = 0;
  let resultSuperiorityPercent = 0;

  if (iq < 70) {
    resultAffiliationPercent = 13
    resultSuperiorityPercent = 13
  
  } else if (iq >= 70 && iq <= 79) {
    resultAffiliationPercent = 11
    resultSuperiorityPercent = 24
  
  } else if (iq >= 80 && iq <= 89) {
    resultAffiliationPercent = 14
    resultSuperiorityPercent = 38
    
  } else if (iq >= 90 && iq <= 109) {
    resultAffiliationPercent = 31
    resultSuperiorityPercent = 69
    
  } else if (iq >= 110 && iq <= 119) {
    resultAffiliationPercent = 18
    resultSuperiorityPercent = 87
    
  } else if (iq >= 120 && iq <= 129) {
    resultAffiliationPercent = 10
    resultSuperiorityPercent = 97
    
  } else if (iq >= 130) {
    resultAffiliationPercent = 3
    resultSuperiorityPercent = 99
    
  }

  return (
    <div className={s.chart + ' ' + s.chart__body}>
      <h6 className={s.chart__title}>Международный уровень показателей IQ</h6>
      <div className={s.chart__chart}>
        <Pie data={data} options={options}/>
      </div>
      {result && (
        <div className={s.chart__result}>Вы принадлежите к {resultAffiliationPercent}% людей мира. Вы умнее, чем. {resultSuperiorityPercent}% населения.</div>
      )}
    </div>
  );
}
