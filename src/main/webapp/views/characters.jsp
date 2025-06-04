<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="domain.Film"%>
<%@ page import="domain.Character"%>

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
                               <td width="20"><a
									href='<c:url value="/editcharacter?id=${character.getId()}" />'
									role="button" class="btn btn-outline-primary">
										<img alt="Редактировать"
								src="images/icon-edit.png" width="20"></a></td>
	
 
<td width="20">
    <a href='<c:url value="/deletecharacter?id=${character.getId()}"/>' 
       role="button" 
       class="btn btn-outline-primary"
       onclick="return confirm('Удалить фильм с кодом: ${character.getId()}?')">
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
                            <label for="id" class="col-sm-3 col-form-label">Код</label>
                            <div class="col-sm-7">
                                <input type="number" class="form-control" id="id" name="id" />
                            </div>
                        </div>
                   <div class="mb-3 row">
    <label for="films" class="col-sm-3 col-form-label">Фильм</label>
    <div class="col-sm-7">
        <select name="films" class="form-control" required>
            <option value="">Выберите фильм</option>
            <c:forEach var="film" items="${films}">
                <option value="${film.id}">
                    ${film.title}
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