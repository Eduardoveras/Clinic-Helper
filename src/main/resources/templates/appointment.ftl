<body>
<form >
    <fieldset>
        <h2>Registro de citas:</h2>

        Cedula del paciente:<br>
        <input type="text" name="patientId" ><br>
        Fecha:<br>
        <input type="text" name="appointmentDate" ><br>
        Hora:<br>
        <input type="text" name="appointmentTime" ><br>
        Desripcion de la cita:<br>
        <input type="text" name="appointmentDesc" ><br><br>
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
        <th>${appointment.getJascId()}</th>
        <th>${appointment.getAppointmentDate()} ${appointment.getAppointmentTime()}</th>
        <th>${appointment. getAppointmentDescription()}</th>
    </tr>
</#list>
</table>