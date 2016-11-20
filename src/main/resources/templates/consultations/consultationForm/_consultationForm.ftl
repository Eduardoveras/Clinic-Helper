
    <form action="/complete_consultation_process/{consultationId}" method="post"  class="form-horizontal form-label-left">



        <div class="form-group item">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">Patient: </label>
            <div class="col-md-9 col-sm-9 col-xs-12">
                <select name="patient" id="patient" class="select2_single form-control" tabindex="-1" required>
                    <option></option>
                <#list userList as patient>
                    <option value="${patient.getPatientId()}">${patient.getPatientFullName()}</option>
                </#list>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="visitobjective">Visit Objective
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <textarea id="visitobjective" required="required" class="form-control" name="visitobjective" data-parsley-trigger="keyup" data-parsley-minlength="3" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 3 caracters long comment.." data-parsley-validation-threshold="10"></textarea>

            </div>
        </div>
        <div class="form-group">
            <label for="observations" class="control-label col-md-3 col-sm-3 col-xs-12">Observations </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
            <textarea id="observations" required="required" class="form-control" name="observations" data-parsley-trigger="keyup" data-parsley-minlength="3" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 3 caracters long comment.." data-parsley-validation-threshold="10"></textarea>

             </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="insuranceCode">Special conditions
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <textarea id="specialconditions" required="required" class="form-control" name="specialconditions" data-parsley-trigger="keyup" data-parsley-minlength="3" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 3 caracters long comment.." data-parsley-validation-threshold="10"></textarea>

            </div>
        </div>

        <div class="form-group item">
            <label class="control-label col-md-3 col-sm-3 col-xs-12">Surgery type </label>
            <div class="col-md-9 col-sm-9 col-xs-12">
                <select name="surgeryType" id="surgeryType" class="select2_single form-control" tabindex="-1" required>
                    <option th:each="surgeryType : ${T(surgeryType).values()}"
                            th:value="${surgeryType}"
                            th:text="${surgeryType}">
                    </option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="medicaldata">Medical data
            </label>
            <div class="col-md-6 col-sm-6 col-xs-12">
                <textarea id="medicaldata" required="required" class="form-control" name="medicaldata" data-parsley-trigger="keyup" data-parsley-minlength="3" data-parsley-maxlength="100" data-parsley-minlength-message="Come on! You need to enter at least a 3 caracters long comment.." data-parsley-validation-threshold="10"></textarea>

            </div>
        </div>

    </form>
