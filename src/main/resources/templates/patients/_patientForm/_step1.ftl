<div id="step-1">
    <form class="form-horizontal form-label-left">

        <div class="form-group"  >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="firstname">First Name <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="firstname" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="lastname">Last Name <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="last-name" name="lastname" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>

        <div class="form-group"  >
            <label for="idCard" class="control-label col-md-6 col-sm-6 col-xs-12">Id/ Passport <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="idCard" class="form-control col-md-7 col-xs-12" type="text" name="idCard">
            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12">Date Of Birth <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="birthdate" class="date-picker form-control col-md-7 col-xs-12" required="required" type="text">
            </div>
        </div>

        <div class="form-group" >
            <label  class="control-label col-md-3 col-sm-3 col-xs-12">Gender <span class="required">*</span></label>
            <div class="text-left" class="col-md-6 col-sm-6 col-xs-12" >
                <div id="gender" class="btn-group" data-toggle="buttons">
                    <label  class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                        <input type="radio" name="gender" value="M"> &nbsp; Male &nbsp;
                    </label>
                    <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                        <input type="radio" name="gender" value="F"> Female
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group"  >
            <label for="occupation" class="control-label col-md-6 col-sm-6 col-xs-12">Occupation </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="occupation" class="form-control col-md-7 col-xs-12" type="text" name="occupation">
            </div>
        </div>
        <div class="form-group" >
            <label  for="religion" class="control-label col-md-6 col-sm-6 col-xs-12">Religion </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="religion" class="form-control col-md-7 col-xs-12" type="text" name="religion">
            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="country">Country <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="country" name="country" required="required" class="form-control col-md-7 col-xs-12"></select>

            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="state">City <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="state" name="state" required="required" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>

        <div class="form-group"  >
            <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="address">Address <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="address" name="address" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>

        <div class="form-group" >
            <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="nationality">Nationality <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="nationality" name="nationality" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
    </form>

</div>