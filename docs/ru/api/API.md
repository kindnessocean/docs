# Описание методов для работы с User

## Auth
Префикс контроллера **_/api/v1/auth_**

| HTTP метод | URI postfix       | URI args      | Request Body                                                     | Response Body                                                                    | Description                                                     |
|------------|-------------------|---------------|------------------------------------------------------------------|----------------------------------------------------------------------------------|-----------------------------------------------------------------|
| POST       | /requestEmailCode | -             | [RequestEmailCodeDto](../domain/dto/auth/RequestEmailCodeDto.md) | [RequestEmailCodeResponseDto](../domain/dto/auth/RequestEmailCodeResponseDto.md) | Метод позволяет отправить письмо на электронную почту с кодом   |
| GET        | /requestToken     | code, address | -                                                                | [AuthTokenDto](../domain/dto/auth/AuthTokenDto.md)                               | Метод позволяет получить JWT токен по коду из электронной почты |


## User
Префикс контроллера **_/api/v1/user/_**
