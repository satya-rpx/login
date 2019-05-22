/*
 * Use App.getDependency for Dependency Injection
 * eg: var DialogService = App.getDependency('DialogService');
 */

/* perform any action on widgets/variables within this block */
Page.onReady = function() {
    /*
     * variables can be accessed through 'Page.Variables' property here
     * e.g. to get dataSet in a staticVariable named 'loggedInUser' use following script
     * Page.Variables.loggedInUser.getData()
     *
     * widgets can be accessed through 'Page.Widgets' property here
     * e.g. to get value of text widget named 'username' use following script
     * 'Page.Widgets.username.datavalue'
     */
};

Page.slider2Change = function($event, widget, newVal, oldVal) {
    var amount = Page.Widgets.slider2.datavalue;
    var interest_rate = (Page.Widgets.slider4.datavalue) / 1200;
    var years = (Page.Widgets.slider2_1.datavalue) * 12;
    var emi = ((amount * interest_rate * (Math.pow(1 + interest_rate, years))) / (Math.pow(1 + interest_rate, years) - 1)).toFixed(2);
    emi = emi.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    Page.Widgets.text5.datavalue = emi;
};
Page.slider2_1Change = function($event, widget, newVal, oldVal) {
    var amount = Page.Widgets.slider2.datavalue;
    var interest_rate = (Page.Widgets.slider4.datavalue) / 1200;
    var years = (Page.Widgets.slider2_1.datavalue) * 12;
    var emi = ((amount * interest_rate * (Math.pow(1 + interest_rate, years))) / (Math.pow(1 + interest_rate, years) - 1)).toFixed(2);
    emi = emi.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    Page.Widgets.text5.datavalue = emi;
};
Page.slider4Change = function($event, widget, newVal, oldVal) {
    var amount = Page.Widgets.slider2.datavalue;
    var interest_rate = (Page.Widgets.slider4.datavalue) / 1200;
    var years = (Page.Widgets.slider2_1.datavalue) * 12;
    var emi = ((amount * interest_rate * (Math.pow(1 + interest_rate, years))) / (Math.pow(1 + interest_rate, years) - 1)).toFixed(2);
    emi = emi.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    Page.Widgets.text5.datavalue = emi;
};


Page.slider11Change = function($event, widget, newVal, oldVal) {
    var amount = Page.Widgets.slider11.datavalue;
    var years = (Page.Widgets.slider22.datavalue) * 12;
    var age = Page.Widgets.slider33.datavalue;
    var loanamount = ((amount * years) / 12).toFixed(2);
    Page.Widgets.text44.datavalue = loanamount;
};

Page.slider22Change = function($event, widget, newVal, oldVal) {
    var amount = Page.Widgets.slider11.datavalue;
    var years = (Page.Widgets.slider22.datavalue) * 12;
    var age = Page.Widgets.slider33.datavalue;
    var loanamount = ((amount * years) / 12).toFixed(2);
    Page.Widgets.text44.datavalue = loanamount;
};

Page.slider33Change = function($event, widget, newVal, oldVal) {
    var amount = Page.Widgets.slider11.datavalue;
    var years = (Page.Widgets.slider22.datavalue) * 12;
    var age = Page.Widgets.slider33.datavalue;
    var loanamount = ((amount * years) / 12).toFixed(2);
    Page.Widgets.text44.datavalue = loanamount;
};
