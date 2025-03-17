export const getFormattedDate = (date: Date) => {
  const day = ("0" + ((new Date()).getDate())).slice(-2);
  const month = ("0" + ((new Date()).getMonth())).slice(-2);
  const year = date.getFullYear().toString().slice(-2);

  const hours = ("0" + ((new Date()).getHours())).slice(-2);
  const minutes = ("0" + ((new Date()).getMinutes())).slice(-2);

  return `${day}/${month}/${year} ${hours}:${minutes}`
}
