var _ctx = $("meta[name='ctx']").attr("content");

var removebtns = ['lasso2d', 'hoverClosestCartesian', 'hoverCompareCartesian', 'toggleSpikelines', 'select2d', 'pan2d', 'zoom2d', 'zoomIn2d', 'zoomOut2d'];
var globalOptions = {scrollZoom: true, staticPlot: false, displaylogo: false, responsive: true, modeBarButtonsToRemove: removebtns};

$(document).ready(function () {
    //drawGraph('scatter', []);
    var data = {x: [], y: []};
    data.x = ['2019-08-07 15:07:29', '2019-08-07 17:07:29', '2019-08-07 21:07:29', '2019-08-08 06:07:29', '2019-08-08 11:07:29', '2019-08-08 15:07:29'];
    data.y = [100, 120, 160, 250, 350, 500];
    drawscatter(data);
});


function drawscatter(data) {
    var globalBox = [];

    var groupBox = {
        x: data.x,
        y: data.y,
        name: 'Region data',
        mode: 'lines+markers',
        connectgaps: true,
        type: 'scatter'
    };

    globalBox.push(groupBox);

    var layout = {
        title: 'Field data at region level',
        titlefont: {
            family: 'Courier New, monospace',
            size: 18,
            color: '#5f5f5f'
        },
        showlegend: true,
        xaxis: {
            //tickformat: '%Y/%m/%d',
            title: {
                text: 'Survey days',
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
    Plotly.newPlot('chart-area', globalBox, layout, globalOptions);
}

function drawBar(data) {
    var globalBox = [];
    var x;
    $.each(data.xaxis, function (key, value) {
        x = value;
    });
    $.each(data.yaxis, function (key, value) {
        var groupBox = {
            x: x,
            y: value,
            name: key,
            type: 'bar',
            hoverinfo: 'skip'

        };
        globalBox.push(groupBox);
    });
    var layout = {
        //title: graphType.toUpperCase(),
        showlegend: true
    };
    Plotly.newPlot('chart-area', globalBox, layout, globalOptions);

}

function drawGraph(graphType, data) {

    var globalBox = [];
    var loadingdata = false;

    switch (graphType) {
        case 'box':
            $.each(data.yaxis, function (key, value) {
                var groupBox = {
                    y: value,
                    name: key,
                    type: graphType,
                    hoverinfo: 'skip'
                };
                globalBox.push(groupBox);
                loadingdata = true;
            });
            var layout = {
                //title: graphType.toUpperCase(),
                showlegend: true
            };
            break;
        case 'scatter':
            var groupBox = {
                x: ['2019-08-07 15:07:29', '2019-08-07 17:07:29', '2019-08-07 21:07:29', '2019-08-08 06:07:29', '2019-08-08 11:07:29', '2019-08-08 15:07:29'],
                y: [100, 120, 160, 250, 350, 500],
                name: 'Region data',
                mode: 'lines+markers',
                connectgaps: true,
                type: graphType
            };
            globalBox.push(groupBox);
            loadingdata = true;
            var layout = {
                title: 'Field data at region level',
                titlefont: {
                    family: 'Courier New, monospace',
                    size: 18,
                    color: '#7f7f7f'
                },
                showlegend: true,
                xaxis: {
                    //tickformat: '%Y/%m/%d',
                    title: {
                        text: 'Survey days',
                        font: {
                            family: 'Courier New, monospace',
                            size: 18,
                            color: '#7f7f7f'
                        }
                    }
                },
                yaxis: {
                    title: {
                        text: 'Total household',
                        font: {
                            family: 'Courier New, monospace',
                            size: 18,
                            color: '#7f7f7f'
                        }
                    }
                }
            };
            break;
        case 'bar':
            var x;
            $.each(data.xaxis, function (key, value) {
                x = value;
            });
            $.each(data.yaxis, function (key, value) {
                var groupBox = {
                    x: x,
                    y: value,
                    name: key,
                    type: graphType,
                    hoverinfo: 'skip'

                };
                globalBox.push(groupBox);
                loadingdata = true;
            });
            var layout = {
                //title: graphType.toUpperCase(),
                showlegend: true
            };
            break;
    }


    if (loadingdata === true) {
        Plotly.newPlot('chart-area', globalBox, layout, globalOptions);
    } else {
        alert("Ops! Something went wrong...");
    }

}