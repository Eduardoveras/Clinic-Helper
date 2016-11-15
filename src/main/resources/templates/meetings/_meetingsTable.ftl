<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>
                <!--<if !isAdmin>--><a href="/new_meeting" role="button" class="btn btn-primary"><@spring.message "newMeeting" /></a><!--<else><strong>Only non-ADMIN accounts can organize meetings</strong></if>-->
            </h2>
            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><@spring.message "idFrag1" />${user.clinic.clinicPrefix} <@spring.message "idFrag2" /></th>
                    <th><span style="text-transform: uppercase;"><@spring.message "title" /></span></th>
                    <th><span style="text-transform: uppercase;"><@spring.message "objectives" /></span></th>
                    <th><span style="text-transform: uppercase;"><@spring.message "time" /></span></th>
                    <th><span style="text-transform: uppercase;"><@spring.message "place" /></span</th>
                    <th><span style="text-transform: uppercase;"><@spring.message "attendees" /></span></th>
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
                    <th scope="row"><@spring.message "emptyMeeting" /></th>

                </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</div>
<!--I Know this is wrong, i just dont care-->
<br><br><br><br><br><br><br><br><br>

