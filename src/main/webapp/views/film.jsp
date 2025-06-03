<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Film"%>
<%
Film r1 = new Film(1L, "Крестный отец", 1972, "Фрэнсис Форд Коппола", "Криминальная драма");
Film r2 = new Film(2L, "Побег из Шоушенка", 1994, "Фрэнк Дарабонт", "Драма");
Film r3= new Film(3L, "Темный рыцарь", 2008, "Кристофер Нолан", "Боевик");
Film r4 = new Film(4L, "Начало", 2010, "Кристофер Нолан", "Фантастика");
Film[] films = new Film[]{r1, r2, r3, r4};
int length = films.length;
pageContext.setAttribute("films", films);
%>
<!DOCTYPE html>
<html>
<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
<title>Фильмы</title>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Фильмы</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<!-- Bootstrap CSS -->

<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- jQuery -->
<script defer src="js/jquery-3.6.4.js"></script>
<!-- Bootstrap JS + Popper JS -->
<script defer src="js/bootstrap.min.js"></script>
</head>
<body>
 <div class="container-fluid">
 <jsp:include page="/views/header.jsp" />
 <div class="container-fluid">
 <div class="row justify-content-start ">
 <div class="col-8 border bg-light px-4">
 <h3>Список фильмов</h3>
 <table class="table">
 <thead>
 <th scope="col">Код</th>
 <th scope="col">Название</th>
 <th scope="col">Год выпуска</th>
 <th scope="col">Режиссер</th>
 <th scope="col">Жанр</th>
 <th scope="col"> Редактировать</th>
 <th scope="col">Удалить</th>
 </thead>
 <tbody>
 <c:forEach var="film" items="${films}">
 <tr>
 <td>${film.getId()}</td>
 <td>${film.getTitle()}</td>
 <td>${film.getReleaseYear()}</td>
 <td>${film.getDirector()}</td>
 <td>${film.getGenre()}</td>
 <td width="20"><a href="#" role="button"
 class="btn btn-outline-primary">
 <img alt="Редактировать"
 src="images/icon-edit.png" width="20"></a></td>
 <td width="20"><a href="#" role="button"
 class="btn btn-outline-primary">
 <img alt="Удалить" 
 src="images/icon-delete.png" width="20"></a></td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>
 <div class="col-4 border px-4">
 <form method="POST" action="">
 <h3>Новый фильм</h3>
 <div class="mb-3">
 <br> <label for="inputRole"
 class="col-sm-3 col-form-label">Фильм</label>
<div class="col-sm-6">
    <input type="text" name="inputFilm" class="form-control" id="filmsFilm" />
</div>
 </div>
 <p>
 <br> <br> <br>
 <button type="submit"
 class="btn btn-primary">Добавить</button>
 <br>
 </p>
 </form>
 </div>
 </div>
 </div>
 <jsp:include page="/views/footer.jsp" />
 </div>
</body>
</html>