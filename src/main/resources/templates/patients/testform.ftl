

<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Form Wizards <small>Sessions</small></h2>
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


            <!-- Smart Wizard -->
            <p>This is a basic form wizard example that inherits the colors from the selected scheme.</p>
            <div id="wizard" class="form_wizard wizard_horizontal">
                <ul class="wizard_steps anchor">
                    <li>
                        <a href="#step-1" class="selected" isdone="1" rel="1">
                            <span class="step_no">1</span>
                            <span class="step_descr">
                                              Step 1<br>
                                              <small>Basic Information</small>
                                          </span>
                        </a>
                    </li>
                    <li>
                        <a href="#step-2" class="disabled" isdone="0" rel="2">
                            <span class="step_no">2</span>
                            <span class="step_descr">
                                              Step 2<br>
                                              <small>Personal Information</small>
                                          </span>
                        </a>
                    </li>
                    <li>
                        <a href="#step-3" class="disabled" isdone="0" rel="3">
                            <span class="step_no">3</span>
                            <span class="step_descr">
                                              Step 3<br>
                                              <small>Personal Contact Information</small>
                                          </span>
                        </a>
                    </li>
                    <li>
                        <a href="#step-4" class="disabled" isdone="0" rel="4">
                            <span class="step_no">4</span>
                            <span class="step_descr">
                                              Step 4<br>
                                              <small>Friend's Contact Information</small>
                                          </span>
                        </a>
                    </li>
                    <li>
                        <a href="#step-5" class="disabled" isdone="0" rel="4">
                            <span class="step_no">5</span>
                            <span class="step_descr">
                                              Step 5<br>
                                              <small>Insurance</small>
                                          </span>
                        </a>
                    </li>
                </ul>





                <div class="stepContainer" style="height: 287px;"><div id="step-1" class="content" style="display: block;">
                    <form class="form-horizontal form-label-left">

                        <div>

                        <div class="form-group" style="display: inline-block;" style="height: 150px;">
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="firstname">First Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="firstname" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group" style="display: inline-block;">
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="lastname">Last Name <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="last-name" name="lastname" required="required" class="form-control col-md-7 col-xs-12">
                            </div>

                        </div>
                        </div>

                        <div >
                            <div class="form-group" style="display: inline-block;" style="height: 150px;">
                                <label for="idCard" class="control-label col-md-6 col-sm-6 col-xs-12">Id/ Passport </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input id="idCard" class="form-control col-md-7 col-xs-12" type="text" name="idCard">
                                </div>
                            </div>

                        <div class="form-group" style="display: inline-block;">
                            <label class="control-label col-md-6 col-sm-6 col-xs-12">Date Of Birth <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="birthdate" class="date-picker form-control col-md-7 col-xs-12" required="required" type="text">
                            </div>
                        </div>
                        </div>


                        <div class="form-group" >
                            <label  class="control-label col-md-3 col-sm-3 col-xs-12">Gender</label>
                            <div class="text-left" class="col-md-6 col-sm-6 col-xs-12" style="display: inline-block;">
                                <div id="gender" class="btn-group" data-toggle="buttons">
                                    <label  class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                        <input type="radio" name="gender" value="male"> &nbsp; Male &nbsp;
                                    </label>
                                    <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                                        <input type="radio" name="gender" value="female"> Female
                                    </label>
                                </div>
                            </div>
                        </div>


                        <div>
                        <div class="form-group" style="display: inline-block;" style="height: 150px;">
                            <label for="occupation" class="control-label col-md-6 col-sm-6 col-xs-12">Occupation </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="occupation" class="form-control col-md-7 col-xs-12" type="text" name="occupation">
                            </div>
                        </div>
                        <div class="form-group" style="display: inline-block;">
                            <label  for="religion" class="control-label col-md-6 col-sm-6 col-xs-12">Religion </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input id="religion" class="form-control col-md-7 col-xs-12" type="text" name="religion">
                            </div>
                        </div>
                        </div>

                        <div>
                        <div class="form-group" style="display: inline-block;" style="height: 150px;">
                            <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="address">Address <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="address" name="address" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group" style="display: inline-block;">
                            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="city">City <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="city" name="city" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        </div>


                        <div>
                        <div class="form-group" style="display: inline-block;" style="height: 150px;">
                            <label " class="control-label col-md-6 col-sm-6 col-xs-12" for="country">Country <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="country" name="country" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group" style="display: inline-block;">
                            <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="nationality">Nationality <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="nationality" name="nationality" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        </div>

                    </form>

                    </div>

                </div>



                <div class="stepContainer" style="height: 287px;">
                    <div id="step-2" class="content" style="display: block;">
                        <form class="form-horizontal form-label-left">

                            <div class="form-group" >
                                <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="nationality"> Height <span class="required">*</span>
                                </label>

                                <div class="input-group number-spinner">
                                    <span class="input-group-btn data-dwn">
                                        <button class="btn btn-default btn-info" data-dir="dwn">
                                            <span class="glyphicon glyphicon-minus">
                                            </span>
                                        </button>
                                    </span>
                                    <input type="text" id="height" class="form-control text-center"  min="0" max="7.0">
                                    <span class="input-group-btn data-up">
					                    <button class="btn btn-default btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></button>
				                    </span>
                                </div>
                            </div>

                            <div class="form-group" >
                                <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="nationality"> Weight <span class="required">*</span>
                                </label>

                                <div class="input-group number-spinner">
                                    <span class="input-group-btn data-dwn">
                                        <button class="btn btn-default btn-info" data-dir="dwn">
                                            <span class="glyphicon glyphicon-minus">
                                            </span>
                                        </button>
                                    </span>
                                    <input type="text" id="weight" class="form-control text-center"  min="0" max="500">
                                    <span class="input-group-btn data-up">
					                    <button class="btn btn-default btn-info" data-dir="up"><span class="glyphicon glyphicon-plus"></span></button>
				                    </span>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Blood Type</label>
                                <div class="col-md-9 col-sm-9 col-xs-12">
                                    <select id="bloodType" class="form-control">
                                        <option>A+</option>
                                        <option>A-</option>
                                        <option>O+</option>
                                        <option>O-</option>
                                        <option>AB+</option>
                                        <option>AB-</option>
                                    </select>
                                </div>
                            </div>

                            <div class="control-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Allergies</label>
                                <div id="tags_1_tagsinput" class="tagsinput" style="width: auto; min-height: 100px; height: 100px;">
                                <span class="tag"><span>social&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                                    <span class="tag"><span>adverts&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                                    <span class="tag"><span>sales&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                                    <div id="allergies"><input id="allergies" value="" data-default="add a tag" style="color: rgb(102, 102, 102); width: 72px;">
                                </div>
                                    <div class="tags_clear"></div>
                                </div>
                            </div>


                            <div class="control-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">Conditions</label>
                                <div id="tags_1_tagsinput" class="tagsinput" style="width: auto; min-height: 100px; height: 100px;">
                                <span class="tag"><span>social&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                                    <span class="tag"><span>adverts&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                                    <span class="tag"><span>sales&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                                    <div id="conditions1">
                                        <input id="conditions" value="" data-default="add a tag" style="color: rgb(102, 102, 102); width: 72px;">
                                    </div>
                                    <div class="tags_clear"></div>
                                </div>
                            </div>


                        </form>
                    </div>

                </div>

                <div class="stepContainer" style="height: 287px;">
                    <div id="step-3" class="content" style="display: block;">
                    <form class="form-horizontal form-label-left">
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="telephoneNumber">Phone <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="telephoneNumber" name="telephoneNumber" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactTelephoneNumber">Workphone <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="workphone" name="contactTelephoneNumber" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactTelephoneNumber">Cellphone <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="cellphone" name="contactTelephoneNumber" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="email">Email <span class="required">*</span>
                            </label>
                            <div class="col-md-6 col-sm-6 col-xs-12">
                                <input type="text" id="email" name="email" required="required" class="form-control col-md-7 col-xs-12">
                            </div>
                        </div>

                    </form>

                    </div>
                 </div>
                <div class="stepContainer" style="height: 287px;">
                    <div id="step-4" class="content" style="display: block;">
                        <form class="form-horizontal form-label-left">
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactfirstName">First Name <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="contactfirstName" name="contactfirstName" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactlastName">Last Name <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="contactlastName" name="contactlastName" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactaddress">Address <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="contactaddress" name="contactaddress" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactphone">Phone <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="contactphone" name="contactphone" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12" for="contactcellphone">Cellphone <span class="required">*</span>
                                </label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="text" id="contactcellphone" name="contactcellphone" required="required" class="form-control col-md-7 col-xs-12">
                                </div>
                            </div>
                        </form>
                </div>
                </div>


                    <div class="actionBar">
                        <div class="msgBox">
                            <div class="content">

                            </div><a href="#" class="close">X</a></div><div class="loader">Loading</div>
                        <a href="#" class="buttonFinish buttonDisabled btn btn-default">Finish</a>
                        <a href="#" class="buttonNext btn btn-success">Next</a>
                        <a href="#" class="buttonPrevious buttonDisabled btn btn-primary">Previous</a>
                    </div>
                </div>
            <!-- End SmartWizard Content -->




