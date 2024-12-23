import { Button } from 'components';
import { resetTest, useAddResultMutation } from 'ducks';
import { useAppDispatch, useAppSelector } from 'hooks';
import { useForm, SubmitHandler } from 'react-hook-form';
import s from './SendResultForm.module.css';
import { useRouter } from 'next/router';
import { educationLevels, educationTypes, genderTypes } from 'commonConstants';

interface ISendResultFormProps {}

interface IFormInput {
  name: string;
  email: string;
  gender: string;
  yearOfBirth: number;
  educationLevel: string;
  educationType: string;
}
export const SendResultForm: React.FC<
  ISendResultFormProps
> = ({}: ISendResultFormProps) => {
  const dispatch = useAppDispatch();
  const router = useRouter();

  const { resultPoints } = useAppSelector((state) => state.test);

  const [trigger] = useAddResultMutation();
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm<IFormInput>();

  const onSubmit: SubmitHandler<IFormInput> = async (data) => {
    await trigger({
      ...data,
      iq: resultPoints,
      countryCode: navigator.language.slice(-2)
    }).then((result: any) => {
      dispatch(resetTest());

      return result;
    }).then((result: any) => {
      window.location.href = `/certificate/${result.data?.data?._id}`;
    })
  };

  const renderYearOfBirthOptions = () => {
    const result = [];

    for(let i = 2010; i > 1920; i--) {
      result.push(
        <option value={i} key={i}>{i}</option>
      )
    }

    return result;
  }

  return (
    <form className={s.form} onSubmit={handleSubmit(onSubmit)}>
      <div className={s.form__header}>
        <h2 className={s.form__title}>
          Поздравляем, вы только что закончили тест!
        </h2>
        <h3 className={s.form__subtitle}>
          Для того, чтобы издать ваш показатель и статистики (уровень IQ,
          позиции в соответствии с возрастом, сфере обучения и уровня
          образования), просим вас заполнить следующую информацию:
        </h3>
      </div>
      <div className={s.form__body}>
        <label className={s.form__label + ' ' + s.label}>
          <div className={s.label__title}>Имя пользователя :</div>
          <input
            className={s.label__input}
            type='text'
            {...register('name', { required: 'Необходимо заполнить «Имя»', maxLength: 40 })}
            aria-invalid={errors.name ? 'true' : 'false'} 
          />
          { errors.name && <p className={s.label__error}>{errors.name.message}</p> }
        </label>
        <label className={s.form__label + ' ' + s.label}>
          <div className={s.label__title}>Email :</div>
          <input
            className={s.label__input}
            type='email'
            {...register('email', {
              required: 'Необходимо заполнить «Email»',
              pattern: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g,
            })}
            aria-invalid={errors.email ? 'true' : 'false'} 
          />
          { errors.email && <p className={s.label__error}>{errors.email.message}</p> }
        </label>
        <label className={s.form__label + ' ' + s.label}>
          <div className={s.label__title}>Пол :</div>
          <select className={s.label__input} {...register('gender')}>
            <option value=''></option>
            {genderTypes.labels.map((item) => (
              <option value={item} key={item}>{item}</option>
            ))}
          </select>
        </label>
        <label className={s.form__label + ' ' + s.label}>
          <div className={s.label__title}>Год рождения :</div>
          <select className={s.label__input} {...register('yearOfBirth')}>
            <option value=''></option>
            {renderYearOfBirthOptions()}
          </select>
        </label>
        <label className={s.form__label + ' ' + s.label}>
          <div className={s.label__title}>Образование :</div>
          <select
            className={s.label__input}
            {...register('educationType', {required: 'Необходимо выбрать «Образование»'})}
            aria-invalid={errors.educationType ? 'true' : 'false'}
          >
            <option value=''></option>
            {educationTypes.labels.map((item) => (
              <option value={item} key={item}>{item}</option>
            ))}
          </select>
          { errors.educationType && <p className={s.label__error}>{errors.educationType.message}</p> }
        </label>
        <label className={s.form__label + ' ' + s.label}>
          <div className={s.label__title}>Уровень образования :</div>
          <select
            className={s.label__input}
            {...register('educationLevel', {required: 'Необходимо выбрать «Уровень образования»'})}
            aria-invalid={errors.educationLevel ? 'true' : 'false'}
          >
            <option value=''></option>
            {educationLevels.labels.map((item) => (
              <option value={item} key={item}>{item}</option>
            ))}
          </select>
          { errors.educationLevel && <p className={s.label__error}>{errors.educationLevel.message}</p> }
        </label>
        <div className={s.form__submit}>
          <Button type='submit'>Получить результат</Button>
        </div>
      </div>
    </form>
  );
};
