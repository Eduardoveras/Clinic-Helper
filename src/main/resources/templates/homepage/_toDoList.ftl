<!-- Start to do list -->
<div class="col-md-5 col-sm-5 col-xs-12">
    <div class="x_panel">
        <div class="x_title">
            <h2>To Do List <small>Sample tasks</small></h2>
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

            <div class="">
                <ul class="to_do">
                <#list todoList as todo>
                    <li style="background: ${todo.getColorHtml()};">
                        <p><h5> ${todo.title}</h5><input type="checkbox" class="flat"> ${todo.type} ${todo.description}</p>
                    </li>
                <#else>
                    <li>
                        <p><h3>You have no items to do</h3></p>
                    </li>
                </#list>


                </ul>
            </div>
        </div>
    </div>
</div>
<!-- End to do list -->