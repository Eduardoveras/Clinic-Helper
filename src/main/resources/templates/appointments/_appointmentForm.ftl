<div class="col-md-4 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Register New Appointment</small></h2>
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
            <form action="/newAppointment" METHOD="POST" enctype="multipart/form-data" id="demo-form2" data-parsley-validate class="form-horizontal form-label-left">

                <div class="form-group">
                    <div class='input-group date' id='datetimepicker1'>
                        <input type='text' class="form-control" />
                        <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="text-left" class="control-label col-md-6 col-sm-6 col-xs-12" for="appointmentDate">Appointment Date (MM/dd/yyyy): <span class="required">*</span>
                    </label>
                    <div class="date-picker col-md-10 col-sm-10 col-xs-16">
                        <input type="text" id="appointmentDate" name="appointmentDate" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="text-left" class="control-label col-md-6 col-sm-6 col-xs-12" class="date-picker" for="appointmentTime"> Appointment Time (hh:mm:ss): <span class="required">*</span>
                    </label>
                    <div class="col-md-10 col-sm-10 col-xs-16">
                        <input type="text" id="appointmentTime" name="appointmentTime" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="text-left" class="control-label col-md-6 col-sm-6 col-xs-12 " for="patient">Identification Number: <span class="required">*</span>
                    </label>
                    <div class="col-md-10 col-sm-10 col-xs-16">
                        <input type="text" id="patient" name="patient" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>
                <div class="form-group">
                    <label class="text-left" class="control-label col-md-6 col-sm-6 col-xs-12" for="description">Description: <span class="required">*</span>
                    </label>
                    <div class="col-md-10 col-sm-10 col-xs-16">
                        <input type="text" id="description" name="description" required="required" class="form-control col-md-7 col-xs-12">
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-md-3 col-sm-3 col-xs-12">Type: <span class="required">*</span></label>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="radio" style="display: inline-block">
                            <label><input type="radio" name="type" id="type" value="C">Consultation</label>
                        </div>
                        <div style="display: inline-block"></div>
                        <div class="radio" style="display: inline-block">
                            <label><input type="radio" name="type" id="type" value="S">Surgery</label>
                        </div>
                    </div>
                </div>

                <div class="ln_solid"></div>
                <div class="form-group">
                    <div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
                        <button type="submit" class="btn btn-primary">Cancel</button>
                        <button type="submit" class="btn btn-success">Submit</button>
                    </div>
                </div>

            </form>
        </div>
    </div>
</div>
