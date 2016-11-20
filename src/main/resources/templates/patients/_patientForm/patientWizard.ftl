<!-- page content -->
            <div class="col-md-12 col-sm-12 col-xs-12">
                <div class="x_panel">
                    <div class="x_title">
                        <h2><@spring.message "formFrag1" /> ${user.clinic.clinicPrefix} <@spring.message "formFrag2" /></h2>
                        <div class="clearfix"></div>
                    </div>
                    <div class="x_content">
                        <!-- Smart Wizard -->
                        <div id="wizard" class="form_wizard wizard_horizontal">
                            <ul class="wizard_steps">
                                <li>
                                    <a href="#step-1">
                                        <span class="step_no">1</span>
                                        <span class="step_descr">
                                              <@spring.message "step" /> 1<br />
                                              <small><@spring.message "basic" /></small>
                                          </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#step-2">
                                        <span class="step_no">2</span>
                                        <span class="step_descr">
                                              <@spring.message "step" /> 2<br />
                                              <small><@spring.message "medInfo" /></small>
                                          </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#step-3">
                                        <span class="step_no">3</span>
                                        <span class="step_descr">
                                              <@spring.message "step" /> 3<br />
                                              <small><@spring.message "cInfo" /></small>
                                          </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#step-4">
                                        <span class="step_no">4</span>
                                        <span class="step_descr">
                                              <@spring.message "step" /> 4<br />
                                              <small><@spring.message "emergency" /></small>
                                          </span>
                                    </a>
                                </li>
                                <li>
                                    <a href="#step-5">
                                        <span class="step_no">5</span>
                                        <span class="step_descr">
                                              <@spring.message "step" /> 5<br />
                                              <small><@spring.message "insurance" /></small>
                                          </span>
                                    </a>
                                </li>
                            </ul>
                            <#include "/patients/_patientForm/_step1.ftl">
                            <#include "/patients/_patientForm/_step2.ftl">
                            <#include "/patients/_patientForm/_step3.ftl">
                            <#include "/patients/_patientForm/_step4.ftl">
                            <#include "/patients/_patientForm/_step5.ftl">
                        </div>
                        <!-- End SmartWizard Content -->
                    </div>
                </div>
            </div>