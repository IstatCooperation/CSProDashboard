
/* global ctx */

$(function () {
    setMenuActive('report-populationResidence');
    populate(ctx + '/rest/report/populationTypeOfResidence', 'populationlist');
    populate(ctx + '/rest/report/populationTypeOfResidenceMale', 'populationlistMale');
    populate(ctx + '/rest/report/populationTypeOfResidenceFemale', 'populationlistFemale');
});
var columnDefs = [{'targets': [0], 'visible': false, 'searchable': false}];
function populate(url, target) {
    $.getJSON(url, function (dataSet) {
        var table = $('#' + target).DataTable({
            data: dataSet,
            columnDefs: columnDefs,
            responsive: true,
            lengthChange: false,
            buttons: ['csv', 'excel', 'pdf']
        });
        table.buttons().container().appendTo('#' + target + '_wrapper .col-sm-6:eq(0)');
    });
}

