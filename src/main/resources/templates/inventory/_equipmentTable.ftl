<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#theModal">New Equipment</button>
<table id="datatable-responsive" class="table table-hover table-striped table-bordered dt-responsive nowrap" cellspacing="0" width="100%">
    <thead>
    <tr>
        <th>${user.clinic.clinicPrefix} ID</th>
        <th>NAME</th>
        <th>USE</th>
        <th>DESCRIPTION</th>
        <th>QUANTITY</th>
    </tr>
    </thead>
    <tbody>
    <#list equipmentList as equipment>
    <tr>
        <td>${equipment.equipmentId}</td>
        <td>${equipment.equipmentName}</td>
        <td>${equipment.equipmentUse}</td>
        <td>${equipment.equipmentDescription}</td>
        <td>${equipment.equipmentInStock} unit<#if equipment.equipmentInStock gt 1>s</#if></td>
    </tr>
    <#else>
    <tr>
        <th scope="row">No Equipments Registered</th>

    </tr>
    </#list>
    </tbody>
</table>