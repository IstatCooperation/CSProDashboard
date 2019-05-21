
function populate(url, tableId) {
    var clean;
    if (!dashboardInfo.listing && !dashboardInfo.expected) {
        clean = function (a) {
            a.length = a.length - 5;
        }
    } else if (!dashboardInfo.listing) {
        clean = function (a) {
            a.splice(a.length - 1, 1);
            a.splice(a.length - 2, 1);
            a.splice(a.length - 3, 1);
        };
    } else if (!dashboardInfo.expected) {
        clean = function (a) {
            a.splice(a.length - 2, 2);
            a.splice(a.length - 2, 1);
        };
    } else {
        clean = function () {};
    }
    $.getJSON(url, function (json) {
        var data = json.splice(1);
        for (var i in data) {
            data[i] = data[i].splitted;
            clean(data[i]);
        }
        var columnsSet = [];
        for (var i in json[0].splitted) {
            columnsSet.push({title: json[0].splitted[i]});
        }
        columnsSet[columnsSet.length - 6] = {title: 'Field Work', render: $.fn.dataTable.render.number(',', '.', 0), 'className': 'numeric'};
        columnsSet[columnsSet.length - 5] = {title: 'Freshlist', render: $.fn.dataTable.render.number(',', '.', 0), 'className': 'numeric'};
        columnsSet[columnsSet.length - 4] = {title: 'Expected', render: $.fn.dataTable.render.number(',', '.', 0), 'className': 'numeric'};
        columnsSet[columnsSet.length - 3] = {title: 'Field/List', render: $.fn.dataTable.render.number(',', '.', 1), 'className': 'numeric'};
        columnsSet[columnsSet.length - 2] = {title: 'Field/Exp', render: $.fn.dataTable.render.number(',', '.', 1), 'className': 'numeric'};
        columnsSet[columnsSet.length - 1] = {title: 'List/Exp', render: $.fn.dataTable.render.number(',', '.', 1), 'className': 'numeric'};
        clean(columnsSet);
        __populate(data, columnsSet, tableId);
    });
}

function __populate(dataSet, columnsSet, tableId) {
    var tFoot = $(document.createElement('tfoot'));
    var footTr = $(document.createElement('tr'));
    $('#' + tableId).append(tFoot.append(footTr));
    var filters = [];
    for (var i in columnsSet) {
        if (columnsSet[i].className !== 'numeric') {
            filters.push(i);
        }
        footTr.append($(document.createElement('th')));
    }
    var table = $('#' + tableId).DataTable({
        data: dataSet,
        columns: columnsSet,
        responsive: true,
        lengthChange: false,
        pageLength: 10,
        order: [[0, "asc"]],
        buttons: ['csv', 'excel', 'pdf'],
        initComplete: function () {
            this.api().columns(filters).every(function () {
                var column = this;
                var select = $('<select><option value=""></option></select>')
                        .appendTo($(column.footer()).empty())
                        .on('change', function () {
                            var val = $.fn.dataTable.util.escapeRegex($(this).val());
                            column.search(val ? '^' + val + '$' : '', true, false).draw();
                        });
                column.data().unique().sort().each(function (d, j) {
                    select.append('<option value="' + d + '">' + d + '</option>');
                });
            });
        }
    });
    table.buttons().container().appendTo('#' + tableId + '_wrapper .col-sm-6:eq(0)');
}

