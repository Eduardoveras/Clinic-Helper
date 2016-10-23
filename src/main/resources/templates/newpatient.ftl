

<#include "/header.ftl">

<body>
<div class="loader"></div>
<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">

<#include "/navbar.ftl">
<#include "/sidebar.ftl">


    <main class="mdl-layout__content mdl-color--grey-100">


        <!--LOGIN BOX-->
        <div class="mdl-grid demo-content">
            <div class="mdl-cell mdl-cell--4-col">
                <div class="demo-card-wide mdl-card mdl-shadow--2dp">
                    <form action="" METHOD="POST">
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

                                <label class="mdl-textfield__label"  for="mail" >Correo electronico</label>
                                <input class="mdl-textfield__input"  type="email" name="mail" id="mail"><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="telephone" >Telefono</label>
                                <input class="mdl-textfield__input"  type="number" name="telephone" id="telephone"><br>

                            </div>
                        </div>
                        <div class="mdl-card__supporting-text">

                            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">

                                <label class="mdl-textfield__label"  for="contactTelephone" >Numero de telefono de contacto</label>
                                <input class="mdl-textfield__input"  type="number" name="contactTelephone" id="contactTelephone"><br>


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

                                <label class="mdl-textfield__label"  for="dateofbirth" >Fecha de nacimiento</label>
                                <input class="mdl-textfield__input"  type="date" name="dateofbirth" id="dateofbirth"><br>


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

                                <label class="mdl-textfield__label"  for="telephone" >Telefono</label>
                                <input class="mdl-textfield__input"  type="number" name="telephone" id="telephone"><br>

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


    </main>
</div>


</body>