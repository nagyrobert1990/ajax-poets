<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <c:url value="/style.css" var="styleUrl"/>
        <c:url value="/index.js" var="indexScriptUrl"/>
        <c:url value="/login.js" var="loginScriptUrl"/>
        <c:url value="/profile.js" var="profileScriptUrl"/>
        <c:url value="/poems.js" var="poemsScriptUrl"/>
        <c:url value="/poem.js" var="poemScriptUrl"/>
        <c:url value="/logout.js" var="logoutScriptUrl"/>
        <link rel="stylesheet" type="text/css" href="${styleUrl}">
        <script src="${indexScriptUrl}"></script>
        <script src="${loginScriptUrl}"></script>
        <script src="${profileScriptUrl}"></script>
        <script src="${poemsScriptUrl}"></script>
        <script src="${poemScriptUrl}"></script>
        <script src="${logoutScriptUrl}"></script>
        <title>App</title>
    </head>
<body>

    <div id="login-content" class="content">
        <h1>Login</h1>
        <form id="login-form" onsubmit="return false;">
            <input type="text" name="email">
            <input type="password" name="password">
            <button id="login-button">Login</button>
        </form>
    </div>

    <div id="profile-content" class="hidden content">
        <h2>Logged in as: <span id="poet-name"></span></h2>
    </div>

    <div id="poems-content" class="hidden content">
        <h3>Poems:</h3>
        <table id="poems">
            <thead>
            <tr>
                <th>ID</th>
                <th>Title</th>
                <th>Published in</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>
    </div>

    <div id="poem-content" class="hidden content">
        <h3>What expression are you looking for?</h3>
        <form id="seeker" onsubmit="return false;">
            <input name="substring" type="text">
            <button type="button" onclick="onSearchClicked()">Search</button><br>
            <br><h3>Number of the expression: <span id="numberOfSubstring"></span></h3>
            <div id="error"></div>
        </form>
        <h3>Content:</h3>
        <p><span id="contentOfPoem"></span></p>
    </div>

    <div id="logout-content" class="hidden content">
        <br><button type="button" id="logout-button">Log out!</button>
    </div>

</body>
</html>
