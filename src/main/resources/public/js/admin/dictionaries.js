
$(function () {
    setMenuActive("cspro2sql-dictionaries");
    $.getJSON(ctx + "/rest/admin/list/errors", function (json) {
        var data = [];
        for (var i in json) {
            var a = [];
            for (var j in json[i]) {
                a.push(json[i][j]);
            }
            data.push(a);
        }
        var name = [];
        name.push('Error ID');
        name.push('Dictionary ID');
        name.push('Error');
        name.push('Date');
        name.push('Questionnaire');
        populate(data, name);
    });
});

function populate(dataSet, name) {
    var columnsSet = [];
    for (var i in name) {
        columnsSet.push({title: name[i]});
    }
    var table = $('#errorslist').DataTable({
        data: dataSet,
        columns: columnsSet,
        responsive: true,
        lengthChange: false,
        pageLength: 10,
        order: [[3, "desc"]],
        buttons: ['csv', 'excel', 'pdf']
    });
    table.buttons().container().appendTo('#householdlist_wrapper .col-sm-6:eq(0)');
}
