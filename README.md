# automationintesting.online Booking room case https://automationintesting.online/
Логин и пароль хранятся в "confPrivate.properties" в формате:
login=login
password=password


1.ID: UI-booking

    Заголовок: Бронь комнаты

    Шаги:
        •1	 Открыть страницу (https://automationintesting.online/)
        •2	 Нажать на кнопку “Book this room”
        •3	 В открывшемся окне мышью протянуть от //button[contains(text(), '12')] (12 мая) до //button[contains(text(), '14')] (14 мая)
        •4   В поле Firstname .room-firstname внести имя "testfirstname"
        •5   В поле Lastname .room-lastname внести фамилию "testlastname"
        •6   В поле Email .room-email внести почту "test@testmail.com"
        •7   В поле Phone .room-phone внести телефон "+79282222222"
        •8   Нажать кнопку Book

    Ожидаемый результат:
        •8	Появилось модальное окно .ReactModalPortal с заголовком "Booking Successful!" и содержимым:
            "Congratulations! Your booking has been confirmed for: 2024-05-12 - 2024-05-15"
    Постусловие:
        •  Посмотреть айди брони по https://automationintesting.online/booking/
        •  Удалить бронь по айди через эндпоинт https://automationintesting.online/booking/[id]

1.ID: UI-form

    Заголовок: Отправка формы

    Шаги:
        •1	 Открыть главную страницу (https://automationintesting.online/)
        •2	 Заполнить поля Name(#name), Email(#email), Phone(#phone), Subject(#subject), Message(#description) тестовыми данными:
| #name    | #email            | #phone          | #subject    | #description    |
|----------|-------------------|-----------------|-------------|-----------------|
| TestName | test@testmail.com |  +79000000000 | TestSubject | TestDescription |
        •3	 Нажать на кнопку Submit (#submitContact)

    Ожидаемый результат:
        •В блоке .col-sm-5 div появился заголовок и три блока с текстом:
| h2                                | p                 | p  | p           |
|-----------------------------------|-------------------|----|-------------|
| Thanks for getting in touch [Имя] | We'll get back to you about| [subject] | as soon as possible. |
        •По адресу http://localhost/#/admin/messages в блоке messages появилось сообщение с соответсвующим Name и Subject,
        а при клике на него открывается popup с данными:

| Блок "From:"(.col-10 p) | Блок "Phone:"(.ReactModalPortal .col-2) | Блок "Email:"(.ReactModalPortal div:nth-child(2) div) | Блок без описания (.ReactModalPortal :nth-child(3)) | Блок без описания (".ReactModalPortal :nth-child(4)) |
|-----------------------|-----------------------------------------|-------------------------------------------------------|-----------------------------------------------------|------------------------------------------------------|
| From: [name]          | Phone: [phone]                          | Email: [email]                                        | [subject]                                           | [message]                                            |

    Постусловие:
        •  Удалить сообщение (с помощью эндпоинта https://automationintesting.online/message/)