import { MouseEventHandler } from 'react'
import s from './SliderArrow.module.css'

interface ISliderArrowProps {
  style: object,
  className: string,
  onClick: MouseEventHandler<any>
}

export const SliderArrow: React.FC<any> = ({style, className, onClick}: ISliderArrowProps) => {
  return (
    <div
      className={className + ' ' + s.arrow}  
      style={{ ...style, display: 'block' }}
      onClick={onClick}
    />
      
  )
}
