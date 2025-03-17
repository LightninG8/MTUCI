import elonMuskImg from './storage/elon-musk.jpg'
import warrentBuffetImg from './storage/warrent-buffet.jpg'
import steveJobskImg from './storage/steve-jobs.jpg'


interface celebrity {
  name: string;
  description: string;
  image: string;
  iq: number;
}

export const celebritiesList: celebrity[] = [
  {
    name: 'Илон Маск',
    description: 'Физик из Южной Африки, предприниматель, изобретатель и магнат, национализированный канадцем и американцем',
    image: '/images/celebrities/elon-musk.jpg',
    iq: 155
  },
  {
    name: 'Уоррен Баффетт',
    description: 'Считается одним из крупнейших инвесторов в мире, а также крупнейшим акционером',
    image: '/images/celebrities/warrent-buffet.jpg',
    iq: 140
  },
  {
    name: 'Стив Джобс',
    description: 'Был директором компании Apple и единственным индивидуальным акционером The Walt Disney Company',
    image: '/images/celebrities/steve-jobs.jpg',
    iq: 160
  },
  {
    name: 'Майк Тайсон',
    description: 'Майк Тайсон - самый молодой чемпион в супертяжелом весе в истории бокса',
    image: '/images/celebrities/mike-thyson.jpg',
    iq: 105
  },
  {
    name: 'Коби Брайант',
    description: 'Брайант был самым молодым игроком в истории NBA - 18 лет, 2 месяца и 11 дней',
    image: '/images/celebrities/koby-brayant.jpg',
    iq: 114
  },
  {
    name: 'Брэд Питт',
    description: 'Брэд бросил колледж ради актерского мастерства, но не добрал 2 баллов до боразования',
    image: '/images/celebrities/bradd-pitt.jpg',
    iq: 115
  },
  {
    name: 'Усейн Болт',
    description: 'Болт является рекордсменом в беге на 100 метров со временем 9,58 секунды',
    image: '/images/celebrities/useyn-bolt.jpg',
    iq: 117
  },
  {
    name: 'Дэвид Бэкхем',
    description: 'Дэвид Бэкхем подписал контракт с "Манчестер Юнайтед" 8 июля 1991 года',
    image: '/images/celebrities/david-backhem.jpg',
    iq: 124
  },
  {
    name: 'Билли Айлиш',
    description: 'Билли - самая молодая женщина с альбомом №1 в Великобритании',
    image: '/images/celebrities/billy-eilish.jpg',
    iq: 129
  },
  {
    name: 'Лионель Месси',
    description: 'Лионель Месси 6 раз становился обладателем Золотого мяча',
    image: '/images/celebrities/lionel-messi.jpg',
    iq: 131
  },
  {
    name: 'Роберт Дауни-младший',
    description: 'Роберт Дауни-младший - вторая по кассовым сборам звезда всех времён',
    image: '/images/celebrities/robert-dauni.jpg',
    iq: 158
  },
  {
    name: 'Конор Макгрегор',
    description: 'МакГрегор - самый богатый боец ММА всех времён',
    image: '/images/celebrities/conor-macgregor.jpg',
    iq:  135
  }
  
]
