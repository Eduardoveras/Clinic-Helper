<body>
<form >
    <fieldset>
        <h2>Registro de citas:</h2>

        Cedula del paciente:<br>
        <input type="text" name="patientId" id="patientId" ><br>
        Fecha:<br>
        <input type="text" name="appointmentDate" id=appointmentDate" ><br>
        Hora:<br>
        <input type="text" name="appointmentTime" id=appointmentTime"><br>
        Desripcion de la cita:<br>
        <input type="text" name="appointmentDesc" id=appointmentDescription" ><br><br>
        <input type="submit" value="Submit">
        </fieldset>

 </form>
        </body>

<table>
    <tr>
        <th>All appointments</th>
    </tr>
    <tr>
        <th>jascId</th> <th>appointment date & time </th> <th>appointment Description</th>
    </tr>
<#list appointmentList?sort_by("jascId") as appointment>
    <tr>
        <td>${appointment.getJascId()}</td>
        <td>${appointment.getAppointmentDate()} ${appointment.getAppointmentTime()}</td>
        <td>${appointment. getAppointmentDescription()}</td>
    </tr>
</#list>
</table>