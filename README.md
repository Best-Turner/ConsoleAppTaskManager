![License](https://img.shields.io/badge/license-MIT-blue.svg)

## Описание

Task Manager — это консольное однопользовательское приложение, предназначенное для управления задачами. Оно поддерживает
операции CRUD (Создание, Чтение, Обновление, Удаление) и предоставляет возможности логирования. Приложение использует
паттерны проектирования "Команда" и "Компоновщик" для обеспечения гибкости и расширяемости.

## Функциональные возможности

- **Создание задач**: Добавляйте новые задачи с описанием.
- **Чтение задач**: Просматривайте список всех задач с их статусами.
- **Обновление задач**: Изменяйте описание и статус существующих задач.
- **Удаление задач**: Удаляйте ненужные задачи.
- **Логирование**: Все операции записываются в лог для отслеживания действий.

## Установка

1. Убедитесь, что у вас установлен [Java JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (
   версия 11 или выше).
2. Клонируйте репозиторий:

   ```bash
   https://github.com/Best-Turner/ConsoleAppTaskManager.git

Перейдите в директорию проекта:

cd task-manager
Скомпилируйте проект:

``
mvn clean install
``

Использование
Запустите приложение, выполнив следующую команду:

``
mvn exec:java -DexecmainClass="org.example.Main"
``

После запуска приложения вы увидите меню с доступными командами. Вы можете использовать следующие команды для управления
задачами:

- Добавить новую задачу.
- Показать все задачи.
- Обновить задачу по ID.
- Удалить задачу по ID.
- Выход из приложения.


- **Паттерны проектирования**

1 - Паттерн "Команда"

Паттерн "Команда" используется для инкапсуляции всех операций, которые может выполнять приложение. Каждая команда
реализует интерфейс и может быть выполнена независимо, что упрощает добавление новых команд в будущем.

2 - **Паттерн "Компоновщик"**

Паттерн "Компоновщик" позволяет работать с иерархией задач как с единым объектом. Это позволяет группировать задачи и
управлять ими более эффективно, что особенно полезно для сложных проектов.

Логирование
Все действия пользователя логируются в файл log.txt с уровнем логирования INFO и выше и в файл debug.txt с уровнем DEBUG и выше, что позволяет отслеживать изменения и операции. Логи содержат
информацию о времени выполнения операций и их результатах.
