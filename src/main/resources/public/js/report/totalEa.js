$(function () {
    setMenuActive("report-total-ea");
    
    //manage toggle icon
    $('.toggler').on('click', function () {
        $(this).toggleClass('fa-rotate-180 on');
        $("#row-widget").toggle();
        $("#row-chart").toggle();
    });
        
    $.getJSON(ctx + "/rest/report/total", function (report) {
        $('#eaFieldwork').text(format(report[0][1]));
        $('#eaFreshlist').text(format(report[0][2]));
        $('#eaExpected').text(format(report[0][3]));
        if ($('.fieldworkFreshlist').length > 0)
            $('.fieldworkFreshlist').ClassyLoader({
                percentage: 100. * report[0][1] / report[0][2],
                speed: 20,
                diameter: 70,
                fontSize: '40px',
                roundedLine: true,
                fontColor: '#34788c',
                lineColor: '#34788c',
                remainingLineColor: 'rgba(200,200,200,0.4)',
                lineWidth: 10
            });
        if ($('.fieldworkExpected').length > 0)
            $('.fieldworkExpected').ClassyLoader({
                percentage: 100. * report[0][1] / report[0][3],
                speed: 20,
                diameter: 70,
                fontSize: '40px',
                roundedLine: true,
                fontColor: '#34788c',
                lineColor: '#34788c',
                remainingLineColor: 'rgba(200,200,200,0.4)',
                lineWidth: 10
            });
        if ($('.freshlistExpected').length > 0)
            $('.freshlistExpected').ClassyLoader({
                percentage: 100. * report[0][2] / report[0][3],
                speed: 20,
                diameter: 70,
                fontSize: '40px',
                roundedLine: true,
                fontColor: '#34788c',
                lineColor: '#34788c',
                remainingLineColor: 'rgba(200,200,200,0.4)',
                lineWidth: 10
            });
    });
    drawLine();
    drawBar();   
});


function drawLine() {
    var globalSet = [];

    var dataSet = {
        x: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5'],
        y: [100, 250, 700, 800, 870],
        name: 'EA',
        mode: 'lines+markers',
        connectgaps: true,
        type: 'scatter'
    };

    globalSet = [dataSet];

    var layout = {
        title: 'Total EAs that sent data',
        titlefont: {
            family: 'Courier New, monospace',
            size: 18,
            color: '#5f5f5f'
        },
        showlegend: true,
        xaxis: {
            //tickformat: '%Y/%m/%d',
            title: {
                text: 'Enumeration days',
                font: {
                    family: 'Courier New, monospace',
                    size: 18,
                    color: '#5f5f5f'
                }
            }
        },
        yaxis: {
            title: {
                text: 'Total EA',
                font: {
                    family: 'Courier New, monospace',
                    size: 18,
                    color: '#5f5f5f'
                }
            }
        }
    }
    Plotly.newPlot('line-chart-area', globalSet, layout, globalOptions);
}

function drawBar() {

    var dataSet = {
        x: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5'],
        y: [100, 150, 400, 800, 850],
        name: 'EA',
        type: 'bar',
        hoverinfo: 'skip',
        marker: {
            color: '#2980B9',
            width: 1,
            opacity: 0.9
        }
    };

    var globalSet = [dataSet];

    var layout = {
        title: 'Daily EAs that sent data',
        titlefont: {
            family: 'Courier New, monospace',
            size: 18,
            color: '#5f5f5f'
        },
        showlegend: true,
        xaxis: {
            //tickformat: '%Y/%m/%d',
            title: {
                text: 'Enumeration days',
                font: {
                    family: 'Courier New, monospace',
                    size: 18,
                    color: '#5f5f5f'
                }
            }
        },
        yaxis: {
            title: {
                text: 'Daily EA',
                font: {
                    family: 'Courier New, monospace',
                    size: 18,
                    color: '#5f5f5f'
                }
            }
        }
    };

    Plotly.newPlot('bar-chart-area', globalSet, layout, globalOptions);

}