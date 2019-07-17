$(function () {
    renderTable("table-progress", [3]);
    showPanel('progress');
});

function showPanel(type){
    hidePanels();
    $(".cls_" + type).show();
    $("#header-" + type + " span").addClass("header-strong");
}

function hidePanels(){
    $(".cls_progress").hide();
    $(".cls_analysis").hide();
    $(".cls_gis").hide();
    $("#header-progress span").removeClass("header-strong");
    $("#header-analysis span").removeClass("header-strong");
    $("#header-gis span").removeClass("header-strong");
}