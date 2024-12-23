import Slider from 'react-slick';
import 'slick-carousel/slick/slick.css';
import 'slick-carousel/slick/slick-theme.css';

import s from './CelebritiesSlider.module.css'
import { Slide, SliderArrow } from './components';
import { celebritiesList } from './constants';


interface ICelebritiesSliderProps {
  className?: string;
}

export const CelebritiesSlider: React.FC<ICelebritiesSliderProps> = ({className}: ICelebritiesSliderProps) => {
  const settings = {
    dots: true,
    infinite: true,
    speed: 500,
    slidesToShow: 3,
    slidesToScroll: 3,
    className: s.slider__body,
    nextArrow: <SliderArrow />,
    prevArrow: <SliderArrow />,
    responsive: [
      {
        breakpoint: 1240,
        settings: {
          slidesToShow: 2,
          slidesToScroll: 2,
        }
      },
      {
        breakpoint: 576,
        settings: {
          slidesToShow: 1,
          slidesToScroll: 1,
        }
      },
    ]
  };

  return (
    <div className={s.slider + ' ' + className}>
      <Slider {...settings}>
          {celebritiesList.map((props) => (
            <Slide key={props.name} {...props}/>
          ))}
        </Slider>
    </div>
  )
}
