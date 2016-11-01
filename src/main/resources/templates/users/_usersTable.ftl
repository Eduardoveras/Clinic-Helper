<div class="col-md-8 col-sm-6 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>Registered Users</h2>
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
                    <th>Name</th>
                    <th>Email</th>
                    <th>Role</th>
                    <th>Birth Date</th>
                </tr>
                </thead>
                <tbody>
                <#list userList as user>
                <tr>
                    <td>${user.userId}</td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>${user.role}</td>
                    <td>${user.birthDate}</td>
                </tr>
                <#else>
                <tr>
                    <th scope="row">NO USERS AVAILABLE</th>

                </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</div>