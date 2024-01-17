package ru.gb.springdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    // http://localhost:8080/swagger-ui.html

    /**
     * 0. Переварить все, что было изучано.
     * 1. Доделать сервис управления книгами:
     * 1.1 Реализовать контроллер по управлению книгами с ручками: GET /book/{id} - получить описание книги, DELETE /book/{id} - удалить книгу, POST /book - создать книгу
     * 1.2 Реализовать контроллер по управлению читателями (аналогично контроллеру с книгами из 1.1)
     * 1.3 В контроллере IssueController добавить ресурс GET /issue/{id} - получить описание факта выдачи
     * <p>
     * 2.1 В сервис IssueService добавить проверку, что у пользователя на руках нет книг. Если есть - не выдавать книгу (статус ответа - 409 Conflict)
     * 2.2 В сервис читателя добавить ручку GET /reader/{id}/issue - вернуть список всех выдачей для данного читателя
     * <p>
     * 3.1* В Issue поле timestamp разбить на 2: issued_at, returned_at - дата выдачи и дата возврата
     * 3.2* К ресурс POST /issue добавить запрос PUT /issue/{issueId}, который закрывает факт выдачи. (т.е. проставляет returned_at в Issue).
     * Замечание: возвращенные книги НЕ нужно учитывать при 2.1
     * 3.3** Пункт 2.1 расширить параметром, сколько книг может быть на руках у пользователя.
     * Должно задаваться в конфигурации (параметр application.issue.max-allowed-books). Если параметр не задан - то использовать значение 1.
     */


	/*
	 * слои spring-приложения
	 *
	 * 1. controllers (api)
	 * 2. сервисный слой (services)
	 * 3. репозитории (repositories, dao (data access objects), ...)
	 * 4. jpa-сущности (entity, model, domain)
	 *
	 *
	 * Сервер, отвечающий за выдачу книг в библиотеке.
	 * Нужно напрограммировать ручку, которая либо выдает книгу читателями, либо отказывает в выдаче.
	 *
	 * /book/** - книга
	 * GET /book/25 - получить книгу с идентификатором 25
	 *
	 * /reader/** - читатель
	 * /issue/** - информация о выдаче
	 * POST /issue {"readerId": 25, "bookId": 57} - выдать читателю с идентификатором 25 книгу с идентификатором 57
	 *
	 *

	/*
			Tomcat - контейнер сервлетов (веб-сервер)

			/student/...
			spring-student.war -> tomcat
			/api/...
			spring-api.war -> tomcat

			spring-web.jar
	 */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
