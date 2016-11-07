<div class="col-md-8 col-sm-6 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Registered Appointments</h2>
            <ul class="nav navbar-right panel_toolbox">
                <li><a class="collapse-link"><i class="fa fa-chevron-up"></i></a>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i
                            class="fa fa-wrench"></i></a>
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
            <table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap"
                   cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Appointment Date</th>
                    <th>Patient</th>
                    <th>Type</th>
                    <th>Action</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
                </thead>
                <tbody>
                <#list appointmentList as appointment>
                <tr>
                    <td>${appointment.getAppointmentTime()}</td>
                    <td><a href="/patient/${appointment.patient.patientId}">${appointment.patient.patientFullName}</a>
                    </td>
                    <td>${appointment.getAppointmentType()}</td>
                    <td>
                        <div class="btn-group">
                            <button data-toggle="dropdown" class="btn btn-default dropdown-toggle" type="button">Options <span class="caret"></span></button>
                            <ul class="dropdown-menu">
                                <li><a href="#" data-toggle="modal" data-target="#appointmentModal">Re-Eschedule</a>
                                    <input type="hidden" form="reScheduleForm" name="id" value="${appointment.getAppointmentId()}">
                                </li>
                                <li><a href="#">Cancel</a>
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td>${appointment.appointmentDescription}</td>
                    <td>${appointment.appointmentStatus}</td>
                </tr>
                <#else>
                <tr>
                    <th scope="row">NO APPOINTMENTS AVAIBLE</th>

                </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</div>