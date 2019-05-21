var _ctx = $("meta[name='ctx']").attr("content");
var arrLabel = [];
var arrDataMale = [];
var arrDataFemale = [];
var arrColor = [];

$(document).ready(function () {
    setMenuActive("report-sexdistribution");
    var jqxhr = $.getJSON(_ctx + "/rest/report/sexByAgeGroup", function (json) {
        $.each(json, function (i, obj) {
            arrLabel.push(obj[1]);
            arrDataMale.push(obj[2]);
            arrDataFemale.push(obj[3]);
        });

        $('.sexdistribution-fluid').animate(
          {queue: false, duration: 500}
        ).fadeIn('clip', '', 500, callBackShow);
        var configBar = {
            type: 'bar',
            data: {
                labels: arrLabel,
                datasets: [
                    {
                        data: arrDataMale,
                        backgroundColor: '#f53131',
                        label: 'Male'
                    }, {
                        data: arrDataFemale,
                        backgroundColor: '#196889',
                        label: 'Female'
                    }],
            },
            options: {
                responsive: true,
                legend: {
                    position: 'top'
                },
                title: {
                    display: false,
                    text: 'SEX/DISTRIBUTION'
                },
                animation: {
                    animateScale: true,
                    animateRotate: true
                }
            }
        };

        var myChartBar = document.getElementById("bar-chart-area").getContext("2d");
        var myBar = new Chart(myChartBar, configBar);

    });

});

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

   