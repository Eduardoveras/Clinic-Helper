<div class="col-md-12 col-sm-12 col-xs-12">
    <div class="x_panel">
        <div class="x_title">

            <div class="clearfix"></div>
        </div>
        <div class="x_content">
            <table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th><span style="text-transform: uppercase;"><@spring.message "patient Id" /></span></th>
                    <th><span style="text-transform: uppercase;"><@spring.message "Id" /></span></th>



                </tr>
                </thead>
                <tbody>
                <#list consultationList as consult>
                <tr>
                    <td><a href="/consultation/${consult.consultationId}"></a></td>
                    <td>${consult.patient}</td>
                    <td>${consult.consultationId}</td>
                </tr>
                <#else>
                <tr>
                    <th scope="row"><@spring.message "emptyPconsultation" /></th>

                </tr>
                </#list>
                </tbody>
            </table>

        </div>
    </div>
</div>
<!--I Know this is wrong, i just dont care-->
<br><br><br><br><br><br><br><br><br>

