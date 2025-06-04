<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ page import="domain.Character"%>
<%@ page import="domain.Film"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Персонажи</title>
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
                    <h3>Список персонажей</h3>
                    <table class="table">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Имя персонажа</th>
                                <th scope="col">Актер</th>
                                <th scope="col">Статус</th>
                                <th scope="col">Фильм</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="character" items="${characters}">
                                <tr>
                                    <td>${character.id}</td>
                                    <td>${character.characterName}</td>
                                    <td>${character.actorName}</td>
                                    <td>${character.status}</td>
                                    <td>${character.movie.title}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col-6 border px-4">
                    <form method="POST" action="">
                        <h3>Редактировать персонажа</h3>
                        <br><br>
                        <div class="mb-3 row">
                            <label for="idCharacter" class="col-sm-3 col-form-label">ID персонажа</label>
                            <div class="col-sm-6">
                                <input type="text" class="form-control" readonly
                                    value="${characterEdit.id}" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputCharacterName" class="col-sm-3 col-form-label">Имя персонажа</label>
                            <div class="col-sm-6">
                                <input type="text" name="characterName" class="form-control"
                                    value="${characterEdit.characterName}" id="inputCharacterName" required />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputActorName" class="col-sm-3 col-form-label">Актер</label>
                            <div class="col-sm-6">
                                <input type="text" name="actorName" class="form-control"
                                    value="${characterEdit.actorName}" id="inputActorName" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="inputStatus" class="col-sm-3 col-form-label">Статус</label>
                            <div class="col-sm-6">
                                <input type="text" name="status" class="form-control"
                                    value="${characterEdit.status}" id="inputStatus" />
                            </div>
                        </div>
                        <div class="mb-3 row">
                            <label for="selectFilm" class="col-sm-3 col-form-label">Фильм</label>
                            <div class="col-sm-6">
                                <select name="filmId" class="form-control" id="selectFilm" required>
                                    <option value="">-- Выберите фильм --</option>
                                    <c:forEach items="${films}" var="film">
                                        <option value="${film.id}" 
                                            ${characterEdit.movie.id == film.id ? 'selected' : ''}>
                                            ${film.title}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <p>
                            <br><br><br>
                            <button type="submit" class="btn btn-primary">Сохранить</button>
                            <a href='<c:url value="/character" />' role="button"
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