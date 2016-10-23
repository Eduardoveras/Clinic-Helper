<#include "/header.ftl">

<body>
<div class="loader"></div>

<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">

<#include "/navbar.ftl">
<#include "/sidebar.ftl">


    <main class="mdl-layout__content mdl-color--grey-100">

        <div class="mdl-grid demo-content">
            <div class="mdl-cell mdl-cell--4-col">
                <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                    <form action="/newPatient" METHOD="POST" enctype="multipart/form-data">
                        <div class="mdl-card__title">
                            <h2 class="mdl-card__title-text">REgistro de pacientes</h2>
                        </div>
                        <div class="mdl-card__supporting-text">
                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label" for="fisrtname">Nombre</label>
                                <input class="mdl-textfield__input"  type="text" name="firstName" id="firstname" ><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">
                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="lastname" >Apellido</label>
                                <input class="mdl-textfield__input"  type="text" name="lastName" id="lastname"><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">
                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">


                                <label class="mdl-textfield__label"  for="idCard" >Cedula</label>
                                <input class="mdl-textfield__input" type="text" name="idCard" id="idCard" ><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="email" >Correo electronico</label>
                                <input class="mdl-textfield__input"  type="email" name="email" id="email"><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="telephoneNumber" >Telefono</label>
                                <input class="mdl-textfield__input"  type="number" name="telephoneNumber" id="telephoneNumber"><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="contactTelephoneNumber" >Numero de telefono de contacto</label>
                                <input class="mdl-textfield__input"  type="number" name="contactTelephoneNumber" id="contactTelephoneNumber"><br>


                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="address" >Direccion</label>
                                <input class="mdl-textfield__input"  type="text" name="address" id="address"><br>


                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="occupation" >Ocupacion</label>
                                <input class="mdl-textfield__input"  type="text" name="occupation" id="occupation"><br>


                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="dateOfBirth" >Fecha de nacimiento</label>
                                <input class="mdl-textfield__input"  type="date" name="dateOfBirth" id="dateOfBirth" onfocus="placeholder = ''" onblur="placeholder "><br>


                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                sexo<br>
                                <input type="radio" name="gender" id="gender" value="male">Masculino<br>
                                <input type="radio" name="gender" id="gender" value="Femenine"> Femenino<br>
                                <input type="radio" name="gender" id="gender" value="other"> Otro <br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                Nacionalidad:
                                <select id="countries" name="countries">
                                    <option disabled selected value> -- Seleccione un pais -- </option>
                                    <option id="RD">Republica Dominicana</option>
                                    <option id="China">China</option>
                                    <option id="Venezuela">Venezuela</option>
                                </select>

                                <label class="mdl-textfield__label"  for="cities" >Ciudad</label>
                                <input class="mdl-textfield__input"  type="text" name="cities" id="cities"><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="nationality" >Ocupacion</label>
                                <input class="mdl-textfield__input"  type="text" name="nationality" id="nationality"><br>


                            </div>
                        </div>
                        <div class="mdl-card__actions mdl-card--border">
                            <input class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--accent" type="submit" value="Register">
                        </div>

                    </form>
                </div>
            </div>
        </div>
        <!--END LOGIN BOX-->

        <table class="mdl-data-table mdl-js-data-table">
            <thead>
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
            </thead>
            <tbody>
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
            </tbody>
        </table>

    </main>
</div>


</body>