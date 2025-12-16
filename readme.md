# Запуск
Приложение запускается с атрибутом --search, номер должен быть в формате
+79, 79 или 89 ниже рабочий пример:
Нужна 17 java
```aidl
java.exe -jar ./okved.jar --search=89174501251

--Также можно передать путь до yml конфига через --config=
java.exe -jar ./okved.jar --search=89174501251 --config=./application.yml
```

Сборка происходит через maven
