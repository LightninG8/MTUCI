import s from './Button.module.css';
import { MouseEventHandler } from 'react';

interface IButtonProps {
  children?: React.ReactNode;
  className?: string;
  onClick?: MouseEventHandler<HTMLElement>;
  type?: 'button' | 'submit' | 'reset' | undefined;
}

export const Button: React.FC<IButtonProps> = ({children, className, onClick, type}: IButtonProps) => {
  return (
    <button
      className={className + ' ' + s.button + ' ' + s.button__body}
      onClick={onClick}
      type={type}
    >
      {children}
    </button>
  )
};
