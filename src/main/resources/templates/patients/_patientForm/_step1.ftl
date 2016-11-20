<div id="step-1">
    <form action="/newPatient" method="post" class="form-horizontal form-label-left">

        <div class="form-group"  >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="firstname"><@spring.message "first" /> <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="firstname" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="lastname"><@spring.message "last" /> <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="lastname" name="lastname" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>

        <div class="form-group"  >
            <label for="idCard" class="control-label col-md-6 col-sm-6 col-xs-12"><@spring.message "id" /> <span class="required">*</span></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="idCard" class="form-control col-md-7 col-xs-12" type="text" name="idCard">
            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12"><@spring.message "bday" /> <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="dateofbirth" name="dateofbirth" class="date-picker form-control col-md-7 col-xs-12" required="required" type="text">
            </div>
        </div>

        <div class="form-group" >
            <label  class="control-label col-md-3 col-sm-3 col-xs-12"><@spring.message "Gender" /> <span class="required">*</span></label>
            <div class="text-left" class="col-md-6 col-sm-6 col-xs-12" >
                <div id="gender" class="btn-group" data-toggle="buttons">
                    <label  class="btn btn-default" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                        <input type="radio" name="gender" value="M"> &nbsp; <@spring.message "male" /> &nbsp;
                    </label>
                    <label class="btn btn-primary" data-toggle-class="btn-primary" data-toggle-passive-class="btn-default">
                        <input type="radio" name="gender" value="F"> <@spring.message "female" />
                    </label>
                </div>
            </div>
        </div>

        <div class="form-group"  >
            <label for="occupation" class="control-label col-md-6 col-sm-6 col-xs-12"><@spring.message "job" /> </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="occupation" class="form-control col-md-7 col-xs-12" type="text" name="occupation">
            </div>
        </div>
        <div class="form-group" >
            <label  for="religion" class="control-label col-md-6 col-sm-6 col-xs-12"><@spring.message "religion" /> </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="religion" class="form-control col-md-7 col-xs-12" type="text" name="religion">
            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="country"><@spring.message "country" /> <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="country" name="country" required="required" class="form-control col-md-7 col-xs-12"></select>

            </div>
        </div>

        <div class="form-group" >
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="state"><@spring.message "city" /> <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <select id="state" name="state" required="required" class="form-control col-md-7 col-xs-12"></select>
            </div>
        </div>

        <div class="form-group"  >
            <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="address"><@spring.message "address" /> <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="address" name="address" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>

        <div class="form-group" >
            <label  class="control-label col-md-6 col-sm-6 col-xs-12" for="nationality"><@spring.message "nationality" /> <span class="required">*</span>
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="nationality" name="nationality" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>


</div>