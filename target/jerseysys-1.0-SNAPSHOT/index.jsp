<%-- 
    Document   : index
    Created on : 1 Jun 2024, 20:57:48
    Author     : admin
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
</head>
<body>
    <h1>Book List</h1>
    <button id="loadBooks">Load Books</button>
    <ul id="bookList"></ul>

    <script>
        document.getElementById('loadBooks').addEventListener('click', function () {
            fetch('/bookstore/api/books')
                .then(response => response.json())
                .then(data => {
                    let bookList = document.getElementById('bookList');
                    bookList.innerHTML = '';
                    data.forEach(book => {
                        let li = document.createElement('li');
                        li.textContent = book.title + ' by ' + book.author;
                        bookList.appendChild(li);
                    });
                });
        });
    </script>
</body>
</html>

