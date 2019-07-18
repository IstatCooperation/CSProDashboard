$(function () {
    setMenuActive("cspro2sql-metadata");
    renderTable("table-variable", [4,5]);
    renderTable("table-unit", [3,4]);
    renderTable("table-concept");
    showPanel('variable');
    $(".card").show();//to prevent flickering
});

function showPanel(idPanel){
    hidePanels();
    $("#header-" + idPanel +" span").addClass("header-strong");
    $("#table-" + idPanel + "_wrapper").show();
}

function hidePanels(){
    $("#table-variable_wrapper").hide();
    $("#table-unit_wrapper").hide();
    $("#table-concept_wrapper").hide();
    $("#header-variable span").removeClass("header-strong");
    $("#header-unit span").removeClass("header-strong");
    $("#header-concept span").removeClass("header-strong");
}