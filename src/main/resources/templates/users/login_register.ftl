<#include "/layouts/header.ftl">


<body class="login">
<#--include  "/layouts/_loader.ftl"-->
<div>
    <a class="hiddenanchor" id="signup"></a>
    <a class="hiddenanchor" id="signin"></a>

    <div class="login_wrapper">
        <div class="animate form login_form">
            <section class="login_content">
                <form METHOD="POST" enctype="multipart/form-data" action="/userLogin">
                    <h1>Login</h1>
                    <div>
                        <input type="text" class="form-control" name="email" id="email" placeholder="Email" required="" />
                    </div>
                    <div>
                        <input type="password" class="form-control" name="password" id="password" placeholder="Password" required="" />
                    </div>
                    <div>
                        <input type="hidden" name="clinic" value="CH-PLATINUM-JASC">
                        <input type="submit" value="Log In" class="btn btn-default submit"></input>
                        <a class="reset_pass" href="#">Lost your password?</a>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">New to site?
                            <a href="#signup" class="to_register"> Create Account </a>
                        </p>

                        <div class="clearfix"></div>
                        <br />

                        <div>
                            <h1><i class="fa fa-plus-circle"></i> Clinic Helper!</h1>
                            <p>©2016 All Rights Reserved. <br>Clinic Helper Privacy and Terms</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>

        <div id="register" class="animate form registration_form">
            <section class="login_content">
                <form action="/newUser" method="post" enctype="multipart/form-data" >
                    <h1>Create Account</h1>
                    <div>
                        <input type="text" name="username" class="form-control" placeholder="Username" required="" />
                    </div>
                    <div>
                        <input type="email" name="email" class="form-control" placeholder="Email" required="" />
                    </div>
                    <div>
                        <input type="password" name="password" class="form-control" placeholder="Password" required="" />
                    </div>
                    <div>
                        <input type="hidden" name="staff" value="JASC-STAFF-ADMIN" class="form-control" placeholder="Email" required="" />
                        <input type="submit" value="Register" class="btn btn-default submit" ></input>
                    </div>

                    <div class="clearfix"></div>

                    <div class="separator">
                        <p class="change_link">Already a member ?
                            <a href="#signin" class="to_register"> Log in </a>
                        </p>

                        <div class="clearfix"></div>
                        <br />

                        <div>
                            <h1><i class="fa fa-plus-circle"></i> Clinic helper!</h1>
                            <p>©2016 All Rights Reserved. Clinic Helper. Privacy and Terms</p>
                        </div>
                    </div>
                </form>
            </section>
        </div>
    </div>
</div>
</body>
</html>
