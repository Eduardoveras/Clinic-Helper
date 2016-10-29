<div class="col-md-8 col-sm-6 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Hover rows <small>Try hovering over the rows</small></h2>
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
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Cedula/pasaporte</th>
                    <th>Phone</th>
                    <th>Contact Phone</th>
                    <th>email</th>
                    <th>Birthday</th>
                    <th>gender</th>
                    <th>Register Date</th>
                    <th>occupation</th>
                    <th>Nationality</th>
                    <th>Adress</th>
                    <th>city</th>
                    <th>country</th>
                </tr>
                </thead>
                <tbody>
                <#list patientList as patient>
                <tr>
                    <td><a href="/patient/${patient.jascId}">${patient.patientFirstName} ${patient.patientLastName}</a></td>
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