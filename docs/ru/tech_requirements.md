# Технические требования

*Dapamoha - будет использоваться в данном контексте как наименование продукта. В конечном продукте название может быть
другим.*

## Цель проекта
Dapamoha - это приложение где можно найти и предоставить помощь.
Dapamoha - это сервис позволяющий находить, следить, создавать, оценивать благотворительные организации, а также участвовать в их жизни.

Данное приложение будет позволять искать и создавать благотворительные организации и участвовать в их жизни.

## 1. Основные функции проекта
В приложение у любого пользователя есть два типа прав:
- Права во всём проекте: Guest, User, Moderator, Admin.
- Права для работы с организацией: Volunteer, Stuff, Owner.

### 1.1 Описание прав во всём проекте
- Guest - Любой пользователь который не вошёл и\или не создал свой аккаунт.
- User - Пользователь создавший аккаунт и который в данный момент авторизирован.
- Moderator - По своей сути "User" с привилегированными правами.
- Admin - Пользователь с неограниченными правами.
 
| Функция                                      | Guest | USER | MODERATOR | ADMIN |
|----------------------------------------------|:------|------|-----------|-------|
| Поиск Организаций по карте/поиску и фильтрам | +     | +    | +         | +     |
| Просмотр профиля организации                 | +     | +    | +         | +     |
| Просмотр профиля пользователя                | -     | +    | +         | +     |
| Создание Организации                         | -     | +    | +         | +     |
| Создание Отзыва у Организации                | -     | +    | +         | +     |

### 1.2 Описание прав работы с организациями
- Volunteer - Волонтёр организации с возможностью чтения дополнительной информации о сущностях
- Stuff - Пользователь с возможностями частичного изменения различных сущностей (описание, местоположение и тд.) в организации
- Owner - Пользователь с неограниченными правами в этой организации

| Функция                               | Volunteer | Stuff | Owner | MODERATOR | ADMIN |
|---------------------------------------|:----------|-------|-----------|-----------|-------|
| Комментирование отзывов у Организации | +         | +     | +         | -         | -     |
| Изменение описания у Организации      | -         | +     | +         | -         | -     |
| Отключение организации                | -         | -     | +         | -         | +     |
| Удаление организации                  | -         | -     | +         | -         | -     |

## 2. Описание системы
### 2.1 Список сущностей и их связи между собой
### 2.2 Описание сущностей
#### 2.2.1 BaseEntity
и тд.

### 2.3 Список функциональных страниц
#### 2.3.1 Страница авторизации и аунтификации (/signIn, /signUp)
#### 2.3.2 Страница просмотра профиля организации (/org/<ORGANIZATION_ID>)
#### 2.3.3 Страница просмотра пользовательского профиля (/user/<USERNAME>)
#### 2.3.4 Страница просмотра профиля организации (/org/<ORGANIZATION_ID>)
и тд.

### 2.4 Список HTTP методов API
#### 2.3.1 SingIn, SignUp
и тд.

## 3. Требования к UI/UX дизайну
Использование стандартного набора инструментов css, js и тд. и/или библиотек с открытым исходным кодом с возможностью коммерческого использования 
*Данный проект не подразумевает коммерческой составляющей, но данные права не будут лишними. Пожертвования могут рассматриваться как коммерческая деятельность*

## 4. Предлагаемый стек технологий
Для реализации системы предлагается следующий стек технологий:

### 4.1 Предлагаемый стек технологий для серверной части приложения
Стек технологий для разработки серверной части приложения

| Аспект | Технология |
|---|---|
| Язык программирования | Java |
| Фреймворк | Spring Framework |
| Очередь сообщений (MQ) | Apache Kafka |
| Хранилище данных в памяти (Cache) | Redis |
| Основная база данных (Entity DB) | PostgreSQL |
| База данных для временных файлов (TTL) | MongoDB |

### 4.2 Предлагаемый стек технологий для разработки пользовательской части приложения
##### 4.3 Предлагаемый стек технологий для разработки браузерного приложения
| Аспект | Технология   |
|---|--------------|
| Язык программирования | TypeSript    |
| Фреймворк | React        |

##### 4.4 Предлагаемый стек технологий для разработки Android приложения
| Аспект | Технология |
|---|------------|
| Язык программирования | ?          |
| Фреймворк | ?          |

##### 4.5 Предлагаемый стек технологий для разработки iOS приложения
| Аспект | Технология |
|---|------------|
| Язык программирования | ?          |
| Фреймворк | ?          |