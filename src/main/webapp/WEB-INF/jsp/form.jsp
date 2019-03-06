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
        <form action="" method="POST">
            UUID<input type="text" name="uuid" id="uuid" required>
            NAME<input type="text" name="name" id="name" required>
            DIRECTOR<input type="text" name="director" id="director">
            TRAILER URL<input type="text" name="trailerURL" id="trailerURL">
            INFO URL<textarea name="infoURL" id="infoURL"></textarea>
            IMAGE<input type="image" name="image" id="image" required>
            GENRE<input type="text" name="genre" id="genre" required>
            DURATION<input type="text" name="duration" id="duration" required>
            RATING<input type="text" name="rating" id="rating" required>
            COUNTRY<input type="text" name="country" id="country" required>
            <input type="button" value="SEND" id="send">
        </form>
    </main>
</body>

</html>