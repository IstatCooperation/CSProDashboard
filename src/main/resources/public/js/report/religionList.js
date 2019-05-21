var _ctx = $("meta[name='ctx']").attr("content");
var arrLabel = [];
var arrData = [];
var arrColor = [];
var arrLabelData = [];

$(document).ready(function () {
    setMenuActive("report-religion");
    $.getJSON(_ctx + "/rest/report/religion", function (json) {
        var startFrom = 55;
        var individualsTotal = 0;
        $.each(json, function (i, obj) {
            arrLabel.push(obj[1]);
            arrData.push(obj[2]);
            individualsTotal = individualsTotal + parseInt(obj[2]);
            arrColor.push(color[i + startFrom ][2]);
            arrLabelData.push([]);
            arrLabelData[i][0] = obj[1];
            arrLabelData[i][1] = parseInt(obj[2]);
            arrLabelData[i][2] = 0;
        });
        for (var i in arrLabelData) {
            var individuals = 0;
            individuals = parseInt(arrLabelData[i][1]);
            arrLabelData[i][2] = 100 * individuals / individualsTotal;
        }
        $('.religion-fluid').animate(
                {queue: false, duration: 500}
        ).fadeIn('clip', '', 500, callBackShow);
        renderTable();
        renderDoughnut();
    });
});

function renderTable() {
    var table = $("#religionlist").DataTable({
        dom: "<'row'<'col-sm-6'B><'col-sm-6'f>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-5'i><'col-sm-7'p>>",
        responsive: true,
        pageLength: 20,
        bPaginate: true,
        buttons: [{
                extend: 'csvHtml5',
                filename: 'religonList',
                title: 'religionList',
                exportOptions: {columns: [0, 1, 2]}
            }, {
                extend: 'excelHtml5',
                filename: 'religionList',
                title: 'religionList',
                exportOptions: {columns: [0, 1, 2]}
            }, {
                extend: 'pdfHtml5',
                filename: 'religionList',
                title: 'religionList',
                exportOptions: {columns: [0, 1, 2]}
            }],
        data: arrLabelData,
        "columns": [
            {'title': 'Religion'},
            {'title': 'Individuals', render: $.fn.dataTable.render.number(',', '.', 0), 'className': 'numeric'},
            {'title': 'Percentage (%)', render: $.fn.dataTable.render.number(',', '.', 3), 'className': 'numeric'}
        ],
        "order": [[1, "desc"]]
    });
}

function renderDoughnut() {
    var configDoughnut = {
        type: 'doughnut',
        data: {
            datasets: [{
                    data: arrData,
                    backgroundColor: arrColor
                }],
            labels: arrLabel
        },
        options: {
            responsive: true,
            legend: {
                position: 'right'
            },
            animation: {
                animateScale: true,
                animateRotate: true
            }
        }
    };
    var myChartDoughnut = document.getElementById("doughnut-chart-area").getContext("2d");
    var myDoughnut = new Chart(myChartDoughnut, configDoughnut);
}

function callBackHide() {
    setTimeout(function () {
        $("#center").fadeOut();
    }, 1000);
}

function callBackShow() {
    setTimeout(function () {
        $("#center").fadeIn();
    }, 1000);
}
