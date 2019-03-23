<%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>

<head>
    <title>TFG</title>
    <meta charset="utf-8" />
    <link rel="stylesheet" href="../static/css/utils.css" />
    <link rel="stylesheet" href="../static/css/form.css" />
	<script src="../static/js/jquery-3.3.1.min.js" type="text/javascript"></script>
    <script src="../static/js/form.js" type="text/javascript"></script>
</head>

<body>
    <main>
        <h1>Film Form</h1>
        <form method="POST" action="/film/save" enctype="multipart/form-data">
            UUID<input type="text" name="uuid" id="uuid" required>
            NAME<input type="text" name="name" id="name" required>
            DIRECTOR<input type="text" name="director" id="director">
            TRAILER URL<input type="text" name="trailerURL" id="trailerURL">
            INFO URL<input type="text" name="infoURL" id="infoURL">
            IMAGE URL<input type="text" name="imageURL" id="imageURL" required>
            GENRE<input type="text" name="genre" id="genre" required>
            DURATION<input type="number" name="duration" id="duration" required>
            RATING<input type="number" name="rating" id="rating" required>
            COUNTRY<input type="text" name="country" id="country" required>
            <input id="send" type="button" value="Save">
        </form>
    </main>
</body>

</html>