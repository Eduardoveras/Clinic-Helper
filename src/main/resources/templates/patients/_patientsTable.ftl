<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>
                <!--<if canUse>--><a href="/new_patient" role="button" class="btn btn-primary">New Patient</a><!--<else><strong>Only ASSISTANT accounts can register new patients</strong></if>-->
            </h2>
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
            <table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Identification #/Passport</th>
                    <th>Main Phone</th>
                    <th>Secondary Phone</th>
                    <th>Email</th>
                    <th>Birthday</th>
                    <th>Gender</th>
                    <th>Register Date</th>
                    <th>Occupation</th>
                    <th>Nationality</th>
                    <th>Address</th>
                    <th>City</th>
                    <th>Country</th>
                </tr>
                </thead>
                <tbody>
                <#list patientList as patient>
                <tr>
                    <td><a href="/patient/${patient.patientId}">${patient.patientFullName}</a></td>
                    <td>${patient.patientIdCard}</td>
                    <td>${patient.patientTelephoneNumber}</td>
                    <td>${patient.patientContactTelephoneNumber}</td>
                    <td>${patient.patientEmail}</td>
                    <td>${patient.patientBirthDate}</td>
                    <td>${patient.patientGender}</td>
                    <td>${patient.patientRegisteredDate}</td>
                    <td>${patient.occupation}</td>
                    <td>${patient.patientNationality}</td>
                    <td>${patient.patientAddress}</td>
                    <td>${patient.patientCity}</td>
                    <td>${patient.patientCountry}</td>
                </tr>
                <#else>
                <tr>
                    <th scope="row">NO PATIENTS AVAIBLE</th>

                </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</div>
<!--I Know this is wrong, i just dont care-->
<br><br><br><br><br><br><br><br><br>

