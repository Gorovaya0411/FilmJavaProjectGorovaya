<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Film"%>

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
<td width="20"><a
href='<c:url value="/editfilm?id=${film.getId()}" />'
role="button" class="btn btn-outline-primary">
<img alt="Редактировать"
src="images/icon-edit.png" width="20"></a></td>

 
<td width="20">
    <a href='<c:url value="/deletefilm?id=${film.getId()}"/>' 
       role="button" 
       class="btn btn-outline-primary"
       onclick="return confirm('Удалить фильм с кодом: ${film.getId()}?')">
        <img alt="Удалить" src="images/icon-delete.png" width="20">
    </a>
</td>
 </tr>
 </c:forEach>
 </tbody>
 </table>
 </div>
 <div class="col-4 border px-4">
 <form method="POST" action="">
 <h3>Новый фильм</h3>
<div class="mb-3">
    <label for="inputTitle" class="col-sm-3 col-form-label">Название фильма</label>
    <div class="col-sm-6">
        <input type="text" name="title" class="form-control" id="inputTitle" maxlength="100" required />
    </div>
</div>

<div class="mb-3">
    <label for="inputYear" class="col-sm-3 col-form-label">Год выпуска</label>
    <div class="col-sm-6">
        <input type="number" name="release_year" class="form-control" id="inputYear" min="1900" max="2030" required />
    </div>
</div>

<div class="mb-3">
    <label for="inputDirector" class="col-sm-3 col-form-label">Режиссер</label>
    <div class="col-sm-6">
        <input type="text" name="director" class="form-control" id="inputDirector" maxlength="50" required />
    </div>
</div>

<div class="mb-3">
    <label for="inputGenre" class="col-sm-3 col-form-label">Жанр</label>
    <div class="col-sm-6">
        <input type="text" name="genre" class="form-control" id="inputGenre" maxlength="30" required />
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