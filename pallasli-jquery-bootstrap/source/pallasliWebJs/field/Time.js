Pallasli.field.Time =  function(cfg){
	this.tmpl = '<div class="row form-group">'
		+ '<div class="col-xs-5 col-md-3">'
		+ '<label for="{0}">{1}</label>'
		+ '</div><div class="col-xs-7 col-md-9">'
		+ '<input name="{4}" type="time" '
		+ 'class="form-control" id="{0}" placeholder="{2}"  {5}>'
		+ '<label class="tipinfo"></label></div></div>';
this.dataOrder = [ "id", "fieldLabel", "emptyText", "cls", "name",
		 "otherParam" ]; 
	Pallasli.field.Field.prototype.initialize.call(this,cfg); 
}; 
Pallasli.alias["time"]=Pallasli.field.Time;
$.extend( Pallasli.field.Time.prototype, Pallasli.field.Field.prototype, {
	initComponent : function(cfg) {
	if (!cfg.emptyText)
		cfg.emptyText = "";
	var otherParam = "";
	if (cfg.required)
		otherParam += " required"; 
	if (cfg.minlength)
		otherParam += " minlength=" + cfg.minlength;
	if (cfg.maxlength)
		otherParam += " maxlength=" + cfg.maxlength;
	cfg.otherParam = otherParam;
	
}
});