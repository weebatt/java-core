#### DataAndRequestProcessor нужно разбить на три составляющие:
1) чтение используя ForkJoinPool
2) обработку используя FixedThreadPool
3) отправку используя FixedThreadPool

#### Server
при отключении клиента от сервера прерыванием последнего сервер зацикливается на сообщение о конце буфера и при повторном переподключение клиента не перестает спамить говно - ИСПРАВЬ

#### Main tasks
при регистрации при неверном вводе пароле не перекидывать на полный повтор ввода данных?
1. Организовать хранение коллекции в реляционной СУБД (PostgresQL). Убрать хранение коллекции в файле.
2. ~~Для генерации поля id использовать средства базы данных (sequence).~~
3. Обновлять состояние коллекции в памяти только при успешном добавлении объекта в БД
4. Все команды получения данных должны работать с коллекцией в памяти, а не в БД
5. ~~Организовать возможность регистрации и авторизации пользователей. У пользователя есть возможность указать пароль.~~
6. ~~Пароли при хранении хэшировать алгоритмом SHA-384.~~
7. Запретить выполнение команд не авторизованным пользователям.
8. ~~При хранении объектов сохранять информацию о пользователе, который создал этот объект.~~
9. Пользователи должны иметь возможность просмотра всех объектов коллекции, но модифицировать могут только принадлежащие им.
10. Для идентификации пользователя отправлять логин и пароль с каждым запросом. 

#### Необходимо реализовать многопоточную обработку запросов.

1. ~~Для многопоточного чтения запросов использовать ForkJoinPool.~~
2. ~~Для многопотчной обработки полученного запроса использовать Fixed thread pool.~~
3. ~~Для многопоточной отправки ответа использовать Fixed thread pool.~~
4. Для синхронизации доступа к коллекции использовать синхронизацию чтения и записи с помощью java.util.concurrent.locks.ReentrantLock


Нужно разобраться с сохранением
#### Commands with troubles
- filter_contains_name we
- изменить на автоматическое заполнение пользователя при создании объекта коллекции 