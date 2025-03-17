import Image, { StaticImageData } from 'next/image';
import s from './Slide.module.css';
import brainImg from './storage/brain.svg';

interface ISlideProps {
  name: string,
  description: string,
  image: string,
  iq: number
}

export const Slide: React.FC<ISlideProps> = ({name, description, image, iq}: ISlideProps) => {
  return (
    <div className={s.slide + ' ' + s.slide__body}>
      <div className={s.slide__header} style={{backgroundImage: `url(${image.toString()})` }}/>
      <div className={s.slide__main}>
        <h6 className={s.slide__title}>{name}</h6>
        <p className={s.slide__description}>{description}</p>
      </div>
      <div className={s.slide__footer}>
        <div className={s.slide__icon}>
          <Image src={brainImg} alt='iq' loading='eager'/>
        </div>
        
        <div className={s.slide__iq}>{iq} IQ</div>
      </div>
    </div>
  )
}
