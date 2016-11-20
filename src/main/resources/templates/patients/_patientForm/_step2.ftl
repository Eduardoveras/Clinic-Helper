<div id="step-2">
    <form class="form-horizontal form-label-left">

        <div class="form-group">
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="nationality"> <@spring.message "height" />
                <span class="required">*</span>
            </label>

            <div class="input-group number-spinner">
                                    <span class="input-group-btn data-dwn">
                                        <button class="btn btn-default btn-info" data-dir="dwn">
                                            <span class="glyphicon glyphicon-minus">
                                            </span>
                                        </button>
                                    </span>
                <input type="text" id="height" class="form-control text-center" min="0" max="7.0">
                <span class="input-group-btn data-up">
					                    <button class="btn btn-default btn-info" data-dir="up"><span
                                                class="glyphicon glyphicon-plus"></span></button>
				                    </span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-6 col-sm-6 col-xs-12" for="weight"> <@spring.message "weight" />
                <span class="required">*</span>
            </label>

            <div class="input-group number-spinner">
				<span class="input-group-btn data-dwn">
					<button class="btn btn-default btn-info" data-dir="dwn"><span
                            class="glyphicon glyphicon-minus"></span></button>
				</span>
                <input type="text" id="weight" name="weight" class="form-control text-center" value="1" min="-10" max="40">
                <span class="input-group-btn data-up">
					<button class="btn btn-default btn-info" data-dir="up"><span
                            class="glyphicon glyphicon-plus"></span></button>
				</span>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12" for="bloodType"><@spring.message "bType" /> <span class="required">*</span></label>
            <div class="col-md-9 col-sm-9 col-xs-12">
                <select id="bloodType" class="form-control">
                    <option>A+</option>
                    <option>A-</option>
                    <option>O+</option>
                    <option>O-</option>
                    <option>AB+</option>
                    <option>AB-</option>
                </select>
            </div>
        </div>

        <div class="control-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"><@spring.message "allergies" /></label>
            <div id="tags_1_tagsinput" class="tagsinput" style="width: auto; min-height: 100px; height: 100px;">
                                <span class="tag"><span>social&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                <span class="tag"><span>adverts&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                <span class="tag"><span>sales&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                <div id="allergies"><input id="allergies" value="" data-default="add a tag"
                                           style="color: rgb(102, 102, 102); width: 72px;">
                </div>
                <div class="tags_clear"></div>
            </div>
        </div>


        <div class="control-group">
            <label class="control-label col-md-3 col-sm-3 col-xs-12"><@spring.message "conditions" /></label>
            <div id="tags_2_tagsinput" class="tagsinput" style="width: auto; min-height: 100px; height: 100px;">
                                <span class="tag"><span>social&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                <span class="tag"><span>adverts&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                <span class="tag"><span>sales&nbsp;&nbsp;</span><a href="#" title="Removing tag">x</a>
                                </span>
                <div id="conditions1">
                    <input id="conditions" value="" data-default="add a tag"
                           style="color: rgb(102, 102, 102); width: 72px;">
                </div>
                <div class="tags_clear"></div>
            </div>
        </div>


    </form>
</div>