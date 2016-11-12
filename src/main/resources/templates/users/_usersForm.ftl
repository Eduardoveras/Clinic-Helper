<div class="col-md-4 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Register New User</h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-wrench"></i></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">Settings 1</a>
                        </li>
                        <li><a href="#">Settings 2</a>
                        </li>
                    </ul>
                </li>
                <li><a class="close-link"><i class="fa fa-close"></i></a>
                </li>
            </ul>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <br />
            <form action="/newUser" METHOD="POST" enctype="multipart/form-data" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="firstName">First Name <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="firstName" name="firstName" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="lastName">Last Name <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="text" id="lastName" name="lastName" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Gender</label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="radio">
                            <label><input type="radio" name="gender" id="gender1" value="M" required checked>Male</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="gender" id="gender2" value="F" required>Female</label>
                        </div>
                    </div>
                </div>
                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Date Of Birth <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input id="dateOfBirth" name="dateOfBirth" class="date-picker form-control col-md-7 col-xs-12" required="required" type="text">
                    </div>
                </div>

                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="email" id="email" name="email" required="required"  class="form-control col-md-7 col-xs-12">
                    </div>
                </div>

                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">Password <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="password" id="password" name="password" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12" for="occupation">Confirm password <span class="required">*</span>
                    </label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <input type="password" id="password2" name="password2" data-validate-linked="password" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group item">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Role <span class="required">*</span></label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="radio">
                            <label><input type="radio" name="role" id="role1" value="M" required checked>Medic</label>
                        </div>
                        <div class="radio">
                            <label><input type="radio" name="role" id="role2"  value="A">Assistant</label>
                        </div>
                    </div>
                </div>

                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                        <button id="send" type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
