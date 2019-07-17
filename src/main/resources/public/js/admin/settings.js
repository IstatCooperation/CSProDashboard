$(function () {
    renderTable("table-progress");
    renderTable("table-analysis");
    renderTable("table-gis");
    showPanel('progress');
    
});

function showPanel(type){
    hidePanels();
    $("#table-" + type + "_wrapper").show();
    $("#header-" + type + " span").addClass("header-strong");
}

function hidePanels(){
    $("#table-progress_wrapper").hide();
    $("#table-analysis_wrapper").hide();
    $("#table-gis_wrapper").hide();
    $("#header-progress span").removeClass("header-strong");
    $("#header-analysis span").removeClass("header-strong");
    $("#header-gis span").removeClass("header-strong");
    $('#table-progress tr > *:nth-child(1)').hide();
    $('#table-analysis tr > *:nth-child(1)').hide();
    $('#table-gis tr > *:nth-child(1)').hide();
}

function editReport(id, name, tableName, visible){
    $("#report-id").val(id);
    $("#report-name").val(name);
    $("#report-table").val(tableName);
    $("#report-visible").val(visible);
    if(tableName === ""){
        $("#report-table").parent().parent().parent().hide();
    }
    $('#edit-report-modal').modal('show');
}