<div id="step-5">
    <form class="form-horizontal form-label-left">

        <div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="insuranceCode"><@spring.message "iCode" />
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="insuranceCode" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="supplier"><@spring.message "iCompany" />
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input type="text" id="supplier" name="supplier" required="required" class="form-control col-md-7 col-xs-12">
            </div>
        </div>
        <div class="form-group">
            <label for="plan" class="control-label col-md-3 col-sm-3 col-xs-12"><@spring.message "iPlan" /></label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <input id="plan" class="form-control col-md-7 col-xs-12" type="text" name="plan">
            </div>
        </div>

    </form>
</div>