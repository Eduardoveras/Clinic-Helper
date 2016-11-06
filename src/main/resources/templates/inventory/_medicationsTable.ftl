<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#theModal">New Medication</button>
<table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Cedula/pasaporte</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <#list medicationList as medication>
    <tr>
        <td>test</td>
        <td>test</td>
        <td>test</td>
    </tr>
    <#else>
    <tr>
        <th scope="row">No Medication Registered</th>

    </tr>
    </#list>
    </tbody>
</table>