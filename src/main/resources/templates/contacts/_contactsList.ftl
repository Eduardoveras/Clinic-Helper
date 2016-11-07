<div class="col-md-12">
    <div class="x_panel">
        <div class="x_content">
            <div class="row">
                <div class="clearfix"></div>


            <#list contactList as contact>

                <div class="col-md-4 col-sm-4 col-xs-12 profile_details">
                    <div class="well profile_view">
                        <div class="col-sm-12">
                            <h4 class="brief"><i>${user.clinic.clinicPrefix} Contact</i></h4>
                            <div class="left col-xs-7">
                                <h2>${contact.getFullName()}</h2>
                                <p><strong>Email: </strong>${contact.getEmail()}</p>
                                <p><strong>Has an Account: </strong><#if contact.hasAccount>YES<#else>NO</#if></p>
                            </div>
                            <div class="right col-xs-5 text-center">
                                <img src="images/img.jpg" alt="" class="img-circle img-responsive">
                            </div>
                        </div>
                        <div class="col-xs-12 bottom text-center">
                            <div class="col-xs-12 col-sm-6 emphasis">
                                <button type="button" class="btn btn-success btn-xs"><i class="fa fa-user">
                                </i> <i class="fa fa-comments-o">Message</i></button>
                                <button type="button" class="btn btn-primary btn-xs">
                                    <i class="fa fa-user"> </i> View Profile
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            <#else>
                <p><h4>You have don't have more appointments to show</h4></p>
            </#list>
                
            </div>
        </div>
    </div>
</div>