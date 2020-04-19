# scala_spark_app

## TODO
- [x] Фэйковый источник данных для тестов и тесты
- [x] Фэйковая сущность для отправки сообщений и тесты
- [ ] Алгоритм агрегации данных с последующей валидацией и тесты
- [ ] Фэйковая сущность для передачи настроек
- [ ] Тесты...
- [ ] Использование реальной БД
- [ ] Автоматизация заполнения БД данными
- [ ] Использование библиотеки для работы с tcp пакетами
- [ ] Поддержка фильтрации ip адресов
- [ ] Поддержка обновления настроек сразу после обновления значений в БД

## Build and run guide
1. Установка OpenJDK (Java 11)
   ```
   sudo apt install default-jdk
   ```
2. Установка sbt 1.3.10
   ```
   echo "deb https://dl.bintray.com/sbt/debian /" | sudo tee -a /etc/apt/sources.list.d/sbt.list
   curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | sudo apt-key add
   sudo apt-get update
   sudo apt-get install sbt
   ```
    Или IntelliJ IDEA установит sbt автоматически
3. Установка Docker
   ```
   wget -qO- https://get.docker.com/ | sh
   ```
4. Установка docker-compose
   ```
   sudo curl -L "https://github.com/docker/compose/releases/download/1.25.5/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
   sudo chmod +x /usr/local/bin/docker-compose 
   ```
3. Заполнение БД необходимыми данными
    - Выполнить сл. команду в терминале, находясь в папке проекта
      ```
      docker-compose up -d
      ```
    - Пройти по [ссылке](http://localhost:8080/?pgsql=db&username=postgres_user&db=traffic_limits&ns=public&import=) и авторизоваться (пароль находится в [.ENV](.ENV) файле), после чего выполнить импорт из [limits_per_hour.sql](limits_per_hour.sql)
4. next step...