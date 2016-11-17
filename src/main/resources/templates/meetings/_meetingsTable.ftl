<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>
                   <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#meeting">New Equipment</button>
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
                    <th>${user.clinic.clinicPrefix} Id</th>
                    <th>Title</th>
                    <th>Objectives</th>
                    <th>Time</th>
                    <th>Place</th>
                    <th>Attendees</th>
                </tr>
                </thead>
                <tbody>
                <#list meetingsList as meeting>
                <tr>
                    <td>${meeting.getMeetingId()}</td>
                    <td>${meeting.getMeetingTitle()}</td>
                    <td>${meeting.getMeetingObjective()}</td>
                    <td>${meeting.getMeetingTime()}</td>
                    <td>${meeting.getMeetingPlace()}</td>
                    <td>
                        <ul>
                            <#list meeting.getAttendees() as member>
                                <li style="display: inline-block;">
                                    <span class="fa fa-user"></span> ${member.getFullName()} -- ${member.email}</li>
                            </#list>
                        </ul>
                    </td>
                </tr>
                <#else>
                <tr>
                    <th scope="row">NO MEETINGS AVAILABLE</th>

                </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</div>
<!--I Know this is wrong, i just dont care-->
<br><br><br><br><br><br><br><br><br>

