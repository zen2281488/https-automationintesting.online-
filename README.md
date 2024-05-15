# automationintesting.online Booking room case

1.ID: UI-booking

    Заголовок: Бронь комнаты

    Шаги:
        •1	 Открыть страницу https://automationintesting.online/
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
        •  Посмотреть айди брони по http://localhost:3000/booking/
        •  Удалить бронь по айди через эндпоинт http://localhost:3000/booking/[id]

        