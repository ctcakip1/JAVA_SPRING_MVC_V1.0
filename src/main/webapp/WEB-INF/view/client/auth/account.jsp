<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="TuanAnh - Dự án clothershop" />
                <meta name="author" content="TuanAnh" />
                <title>Update Account - TuanAnh</title>
                <link href="/css/styles.css" rel="stylesheet" />

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });
                    });
                </script>
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Thông tin tài khoản</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item"><a href="/">Home Page</a></li>
                            <li class="breadcrumb-item active">View account</li>
                        </ol>
                        <div class=" mt-5">
                            <div class="row">
                                <div class="col-md-6 col-12 mx-auto">
                                    <hr />
                                    <form:form method="post" action="/client/auth/account" modelAttribute="newAccount"
                                        class="row" enctype="multipart/form-data">

                                        <div class="mb-3 col-12 col-md-6" style="display: none;">
                                            <label class="form-label">Id:</label>
                                            <form:input type="text" class="form-control" path="id" />
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Email:</label>
                                            <form:input type="email" class="form-control" path="email"
                                                disabled="true" />
                                        </div>

                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Phone number:</label>
                                            <form:input type="text" class="form-control" path="phone" />
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Full Name:</label>
                                            <form:input type="text" class="form-control" path="fullName" />
                                        </div>
                                        <div class="mb-3 col-12 col-md-6">
                                            <label class="form-label">Address:</label>
                                            <form:input type="text" class="form-control" path="address" />
                                        </div>
                                        <!-- <div class="mb-3 col-12 col-md-6">
                                            <label for="avatarFile" class="form-label">Avatar:</label>
                                            <input class="form-control" type="file" id="avatarFile"
                                                accept=".png, .jpg, .jpeg" name="tuananhFile" />
                                        </div> -->
                                        <div class="col-12 mb-3">
                                            <img style="max-height: 250px; display: none;" alt="avatar preview"
                                                id="avatarPreview" />
                                        </div>
                                        <div class="col-12 mb-3">
                                            <label for="avatarFile" class="form-label">Avatar:</label>
                                            <a href="#">
                                                <img src="/images/avatar/${newAccount.avatar}" class="img-fluid rounded" style="height: 250px; width: 250px; display: flex;"
                                                    alt="Image">
                                            </a>
                                        </div>
                                        <!-- <div class="col-12 mb-5">
                                            <a href="/admin" class="btn btn-warning">Back</a>
                                        </div> -->
                                        <!-- <div class="col-12 mb-5">
                                            <button type="submit" class="btn btn-warning">Update</button>
                                        </div> -->
                                    </form:form>
                                </div>

                            </div>

                        </div>
                    </div>
                </main>

                </div>
                </div>
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                    crossorigin="anonymous"></script>
                <script src="/js/scripts.js"></script>

            </body>

            </html>