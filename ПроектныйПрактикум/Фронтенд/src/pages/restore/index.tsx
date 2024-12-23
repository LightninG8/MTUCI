import type { NextPage } from 'next';
import { MainLayout } from 'containers';
import Head from 'next/head';
import s from './Restore.module.css';
import cs from 'styles/common.module.css';
import { FormEventHandler, ReactNode, useState } from 'react';
import { Button } from 'components';
import { resultApi, useRestoreResultMutation } from 'ducks';
import { useForm } from 'react-hook-form';

const Restore: NextPage = () => {
  const [email, setEmail] = useState('');
  const [trigger, result ] = useRestoreResultMutation();
  const { handleSubmit, register, formState: { errors } } = useForm();

  const { isError, error } = result;

  const onSubmit = async (data: any) => {
    await trigger(data.email);
  };

  console.log(result);

  return (
    <>
      <Head>
        <title>Восстановить результат | Интеллект-тест</title>
      </Head>
      <MainLayout className={s.section}>
        <div className={cs.container + ' ' + s.section__body}>
          <div className={s.recovery + ' ' + s.recovery__body}>
            <h1 className={s.recovery__title}>Вы потеряли свой результат?</h1>
            <p className={s.recovery__text}>Не волнуйтесь. Просто укажите адрес электронной почты, с которым вы связали свой результат, и мы вскоре отправим вам ссылку. Это бесплатно!</p>
            <form
              className={s.recovery__form + ' ' + s.form}
              onSubmit={handleSubmit(onSubmit)}
            >
              <input
                className={s.form__input}
                type='email'
                placeholder='Введите ваш email'
                {...register('email', {
                  required: 'Необходимо заполнить «Email»',
                  pattern: /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/g,
                })}
              />
              { errors.email && <p className={s.form__message}>{(errors.email.message as ReactNode)}</p> }
              { result.isSuccess && <p className={s.form__message}>Письмо отправлено на почту</p>}
              { result.isError && <p> className={s.form__message}Результат не найден</p>}

              <Button
                className={s.form__submit}
                type='submit'
              >Восстановить результат</Button>
            </form>
          </div>
        </div>
      </MainLayout>
    </>
    
  );
}

export default Restore;
