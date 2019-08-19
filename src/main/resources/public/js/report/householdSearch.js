$(document).ready(function () {
    setMenuActive("report-search-household");
});

function searchData(){
    if ( typeof(table) != "undefined" && $.fn.dataTable.isDataTable( '#householdlist' ) ) {
        table.destroy();
    }
    populate(ctx + "/rest/report/household/search/" + getCodeString(), 'householdlist', false);
}

    

