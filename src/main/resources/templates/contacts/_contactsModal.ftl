<!-- Modal -->
<div class="modal fade" id="contactModal" role="dialog">

    <div class="modal-dialog modal-lg">


        <!-- Modal content-->

        <div class=" x_panel modal-content">

            <!--<div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Create task</h4>
            </div>-->
            <div class="modal-body">
                <div class="x_title">
                    <ul class="nav navbar-left panel_toolbox">
                        <li><h4>Add new contactz</h4>
                        </li>
                    </ul>
                    <ul class="nav navbar-right panel_toolbox">
                        <li>
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                        </li>
                    </ul>

                    <div class="clearfix"></div>

                </div>

                <div class="x_content">
                    <br/>
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
                                <input type="email" id="email" name="email" required="required" class="form-control col-md-7 col-xs-12">
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
    </div>
</div>
