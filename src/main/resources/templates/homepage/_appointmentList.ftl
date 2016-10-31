<div class="col-md-3 col-sm-12 col-xs-12">
    <div>
        <div class="x_title">
            <h2>Today's appointments</h2>
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
        <ul class="list-unstyled appointments_list scroll-view">
            <#list todays_appointments as appointment>
            <li class="media event">
                <a class="pull-left border-aero profile_thumb">
                    <i class="fa fa-user aero"></i>
                </a>
                <div class="media-body">
                    <a class="title" href="#">${appointment.patient.getFullName()}</a>
                    <p>${appointment.appointmentDescription} </p>
                    <p> <small>${appointment.appointmentTime}</small>
                    </p>
                </div>
            </li>
                <#else>
                <li>
                    <p><h4>You have don't have more appointments to show</h4></p>
                </li>
            </#list>


        </ul>
    </div>
</div>


