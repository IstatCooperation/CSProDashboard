$(function () {

    setMenuActive("report-total-household");
    
    drawLine();
    drawBar();   
});



function drawLine() {
    var globalSet = [];

    var dataSet = {
        x: ['Day 1', 'Day 2', 'Day 3', 'Day 4', 'Day 5'],
        y: [100, 250, 700, 800, 870],
        name: 'Household',
        mode: 'lines+markers',
        connectgaps: true,
        type: 'scatter'
    };

    globalSet = [dataSet];

    var layout = {
        title: 'Total households',
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
                text: 'Total household',
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
        name: 'Household',
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
        title: 'Daily households',
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
                text: 'Daily household',
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