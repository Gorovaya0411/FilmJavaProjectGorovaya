<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Film"%>
<%@ page import="domain.Character"%>
<%
Film m1 = new Film(1L, "Крестный отец", 1972, "Фрэнсис Форд Коппола", "Криминальная драма");
Film m2 = new Film(2L, "Побег из Шоушенка", 1994, "Фрэнк Дарабонт", "Драма");
Film m3= new Film(3L, "Темный рыцарь", 2008, "Кристофер Нолан", "Боевик");
Film m4 = new Film(4L, "Начало", 2010, "Кристофер Нолан", "Фантастика");

Film[] movies = new Film[]{m1, m2, m3, m4};
pageContext.setAttribute("movies", movies);

Character ch1 = new Character(1L, "Дон Корлеоне", "Марлон Брандо", "Главный герой", m1);
Character ch2 = new Character(2L, "Энди Дюфрейн", "Тим Роббинс", "Протагонист", m2);
Character ch3 = new Character(3L, "Джокер", "Хит Леджер", "Антагонист", m3);
Character ch4 = new Character(4L, "Доминик Кобб", "Леонардо ДиКаприо", "Главный герой", m4);

Character[] characters = new Character[]{ch1, ch2, ch3, ch4};
pageContext.setAttribute("characters", characters);
%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Персонажи</title>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Characters</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- jQuery -->
    <script defer src="js/jquery.min.js"></script>
    <!-- Bootstrap JS + Popper JS -->
    <script defer src="js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <jsp:include page="/views/header.jsp" />
        <div class="container-fluid">
            <div class="row justify-content-start">
                <div class="col-8 border bg-light px-4">
                    <h3>Список персонажей</h3>
                    <table class="table">
                        <thead>
                            <th scope="col">Код</th>
                            <th scope="col">Имя персонажа</th>
                            <th scope="col">Актер</th>
                            <th scope="col">Статус</th>
                            <th scope="col">Фильм</th>
                            <th scope="col">Редактировать</th>
                            <th scope="col">Удалить</th>
                        </thead>
                        <tbody>
                            <c:forEach var="character" items="${characters}">
                                <tr>
                                    <td>${character.getId()}</td>
                                    <td>${character.getCharacterName()}</td>
                                    <td>${character.getActorName()}</td>
                                    <td>${character.getStatus()}</td>
                                    <td>${character.getMovie().getTitle()}</td>
                                    <td width="20">
                                        <a href="#" role="button" class="btn btn-outline-primary">
                                            <img alt="Редактировать" src="images/icon-edit.png"  width="20">
                                        </a>
                                    </td>
                                    <td width="20">
                                        <a href="#" role="button" class="btn btn-outline-primary">
                                            <img alt="Удалить" src="images/icon-delete.png"  width="20">
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-4 border px-4">
                    <form method="POST" action="">
                        <h3>Новый персонаж</h3>
                        <br>
                        <div class="mb-3 row">
                            <label for="characterName" class="col-sm-3 col-form-label">Имя персонажа</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="characterName" name="characterName" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="actorName" class="col-sm-3 col-form-label">Актер</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="actorName" name="actorName" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="status" class="col-sm-3 col-form-label">Статус</label>
                            <div class="col-sm-7">
                                <input type="text" class="form-control" id="status" name="status" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="movie" class="col-sm-3 col-form-label">Фильм</label>
                            <div class="col-sm-7">
                                <select name="movie" class="form-control">
                                    <option>Выберите фильм</option>
                                    <c:forEach var="movie" items="${movies}">
                                        <option value="${movie.getId()}">
                                            <c:out value="${movie.getTitle()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <p><br>
                            <button type="submit" class="btn btn-primary">Добавить</button>
                        </p>
                    </form>
                </div>
            </div>
        </div>
        <jsp:include page="/views/footer.jsp" />
    </div>
</body>
</html>