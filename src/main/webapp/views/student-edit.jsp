<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Student</title>
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div class="container">
  <div class="d-flex justify-content-center">
    <div class="col-lg-6 mt-5">
      <h1 class="text-center mb-3">Edit student</h1>
      <form action="<%=request.getContextPath()%>/student" method="post">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="id" value="${studentToEdit.id}">

        <div class="form-group">
          <label for="studentName">Student Name</label>
          <input type="text" class="form-control" id="studentName" name="name" value="${studentToEdit.name}">
        </div>

        <div class="form-group">
          <label for="age">Birthday</label>
          <input type="date" class="form-control" id="age" name="birthday" value="${studentToEdit.birthday}">
        </div>

        <div class="form-group">
          <label>Class</label>
          <select class="form-control" name="classId">
            <c:forEach items="${classList}" var="classItem">
              <option value="${classItem.id}" ${classItem.id == studentToEdit.classId ? "selected" : ""}>${classItem.name}</option>
            </c:forEach>
          </select>
        </div>

        <button type="submit" class="btn btn-primary">Update</button>
      </form>
    </div>
  </div>
</div>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>
</html>
