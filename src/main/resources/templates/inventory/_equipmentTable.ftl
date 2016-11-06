<table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Cedula/pasaporte</th>
        <th>Phone</th>
    </tr>
    </thead>
    <tbody>
    <#list equipmentList as equipment>
    <tr>
        <td>test</td>
        <td>test</td>
        <td>test</td>
    </tr>
    <#else>
    <tr>
        <th scope="row">No Equipments Registered</th>

    </tr>
    </#list>
    </tbody>
</table>