<#include "/header.ftl">

<body>
<div class="loader"></div>

<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">

<#include "/navbar.ftl">
<#include "/sidebar.ftl">


    <main class="mdl-layout__content mdl-color--grey-100">

        <table>
            <tr>
                <th>JascID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Last Name</th>
                <th>Cedula/pasaporte</th>
                <th>Phone</th>
                <th>Contact Phone</th>
                <th>email</th>
                <th>Birthday</th>
                <th>gender</th>
                <th>Register Date</th>
                <th>occupation</th>
                <th>Nationality</th>
                <th>Adress</th>
                <th>city</th>
                <th>country</th>


            </tr>

        <#list patientList as patient>
            <tr>
                <td>${patient.jascId}</td>
                <td>${patient.patientFirstName}</td>
                <td>${patient.patientLastName}</td>
                <td>${patient.patientIdCard}</td>
                <td>${patient.patientTelephoneNumber}</td>
                <td>${patient.patientContactTelephoneNumber}</td>
                <td>${patient.patientEmail}</td>
                <td>${patient.patientBirthDate}</td>
                <td>${patient.patientGender}</td>
                <td>${patient.patientRegisteredDate}</td>
                <td>${patient.occupation}</td>
                <td>${patient.patientNationality}</td>
                <td>${patient.patientAddress}</td>
                <td>${patient.patientCity}</td>
                <td>${patient.patientCountry}</td>
            </tr>
        <#else>
        <h1>No hay pacientes</h1>
        </#list>
        </table>

    </main>
</div>


</body>