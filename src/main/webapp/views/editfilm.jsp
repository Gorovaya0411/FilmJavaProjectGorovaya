<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Film"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Фильмы</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
            <div class="row justify-content-start">
                <div class="col-6 border bg-light px-4">
                    <h3>Список фильмов</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Название</th>
                                <th scope="col">Год</th>
                                <th scope="col">Режиссер</th>
                                <th scope="col">Жанр</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="film" items="${films}">
                                <tr>
                                    <td>${film.id}</td>
                                    <td>${film.title}</td>
                                    <td>${film.releaseYear}</td>
                                    <td>${film.director}</td>
                                    <td>${film.genre}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-6 border px-4">
                    <form method="POST" action="">
                        <h3>Редактировать фильм</h3>
                        <br><br>
                        <div class="mb-3 row">
                            <label for="idFilm" class="col-sm-3 col-form-label">ID фильма</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly
                                    value="${filmEdit.id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputTitle" class="col-sm-3 col-form-label">Название</label>
                            <div class="col-sm-6">
                                <input type="text" name="title" class="form-control"
                                    value="${filmEdit.title}" id="inputTitle" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputYear" class="col-sm-3 col-form-label">Год выпуска</label>
                            <div class="col-sm-6">
                                <input type="number" name="releaseYear" class="form-control"
                                    value="${filmEdit.releaseYear}" id="inputYear" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputDirector" class="col-sm-3 col-form-label">Режиссер</label>
                            <div class="col-sm-6">
                                <input type="text" name="director" class="form-control"
                                    value="${filmEdit.director}" id="inputDirector" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputGenre" class="col-sm-3 col-form-label">Жанр</label>
                            <div class="col-sm-6">
                                <input type="text" name="genre" class="form-control"
                                    value="${filmEdit.genre}" id="inputGenre" required />
                            </div>
                        </div>
                        <p>
                            <br><br><br>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <a href='<c:url value="/film" />' role="button"
                                class="btn btn-secondary">Отменить</a>
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