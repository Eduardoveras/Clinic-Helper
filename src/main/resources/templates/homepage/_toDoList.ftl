<!-- Start to do list -->
<div class="col-md-4 col-sm-5 col-xs-12">

    <div class="x_panel">

        <div class="x_title">
            <h2>To Do List
                <small>Sample tasks</small>
            </h2>
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

            <div class="">
                <ul class="to_do">
                <#list todoList as todo>
                    <li>
                        <p><h5> ${todo.title}</h5><input type="checkbox" class="flat"> ${todo.type}
                        <br>${todo.description}</p>
                    </li>
                <#else>
                    <li>
                        <p>
                        <h3>You have no items to do</h3></p>
                    </li>
                </#list>


                </ul>
            </div>

            <div class="container">
                <h4>Create task</h4>
                <!-- Trigger the modal with a button -->
                <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">Create
                </button>

                <!-- Modal -->
                <div class="modal fade" id="myModal" role="dialog">

                    <div class="modal-dialog modal-lg">


                        <!-- Modal content-->

                        <div class=" x_panel modal-content">

                            <!--<div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <h4 class="modal-title">Create task</h4>
                            </div>-->
                            <div class="modal-body">
                                <div class="x_title">
                                    <ul class="nav navbar-left panel_toolbox">
                                        <li><h4>Add new item</h4>
                                        </li>
                                    </ul>
                                    <ul class="nav navbar-right panel_toolbox">
                                        <li><button type="button" class="close" data-dismiss="modal">&times;</button>
                                        </li>
                                    </ul>

                                    <div class="clearfix"></div>

                                </div>

                                <div class="x_content">
                                    <br/>
                                    <form action="/newTask" METHOD="POST"
                                          class="form-horizontal form-label-left input_mask">

                                        <div class="col-md-30 col-sm-30 col-xs-26 form-group has-feedback">
                                            <input type="text" class="form-control has-feedback-left" id="title"
                                                   placeholder="title">
                                            <span class="fa fa-user form-control-feedback left"
                                                  aria-hidden="title"></span>
                                        </div>

                                        <div class="col-md-30 col-sm-30 col-xs-26 form-group has-feedback">
                                            Description
                                            <textarea id="description" required="required" placeholder="Description"
                                                      class="form-control" name="description"
                                                      data-parsley-trigger="keyup" data-parsley-minlength="4"
                                                      data-parsley-maxlength="20"
                                                      data-parsley-validation-threshold="10">

                                                        </textarea>
                                        </div>

                                        <div class="col-md-30 col-sm-30 col-xs-26 form-group has-feedback">
                                            <input type="text" class="form-control has-feedback-left" id="clinicId"
                                                   placeholder="clinicId">
                                            <span class="fa fa-user form-control-feedback left"
                                                  aria-hidden="clinicId"></span>
                                        </div>

                                        <div class="form-group">
                                            <label for="sel1">Select type:</label>
                                            <select class="form-control" id="type">
                                                <option>REMINDER</option>
                                                <option>URGENT</option>
                                            </select>
                                        </div>
                                    </form>
                                    <button type="sumbit" class="btn btn-success" data-dismiss="modal">sumbit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End to do list -->